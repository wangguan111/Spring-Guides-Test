package com.gilbert.test.uploadingfiles.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gilbertwang
 */
@Service
@Slf4j
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				String msg = "Failed to store empty file.";
				log.error(msg);
				throw new StorageFileNotFoundException(msg);
			}
			Path destinationFile = this.rootLocation.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				String msg = "Cannot store file outside current directory.";
				log.error(msg);
				throw new StorageFileNotFoundException(msg);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			String msg = "Failed to store file.";
			log.error(msg);
			throw new StorageFileNotFoundException(msg);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			String msg = "Failed to read stored files.";
			log.error(msg);
			throw new StorageFileNotFoundException(msg);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				String msg = "Could not read file: " + filename;
				log.error(msg);
				throw new StorageFileNotFoundException(msg);

			}
		}
		catch (MalformedURLException e) {
			String msg = "Could not read file: " + filename;
			log.error(msg);
			throw new StorageFileNotFoundException(msg);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			String msg = "Could not initialize storage";
			log.error(msg);
			throw new StorageFileNotFoundException(msg);
		}
	}
}
