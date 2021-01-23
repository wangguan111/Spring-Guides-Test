package com.gilbert.test.uploadingfiles.storage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileSystemStorageServiceTests {

	private StorageProperties properties = new StorageProperties();
	private FileSystemStorageService service;

	@BeforeEach
	public void init() {
		properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
		service = new FileSystemStorageService(properties);
		service.deleteAll();
		service.init();
	}

	@Test
	public void loadNonExistent() {
		assertThat(service.load("foo.txt")).doesNotExist();
	}

	@Test
	public void saveAndLoad() {
		service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hello, World".getBytes()));
		assertThat(service.load("foo.txt")).exists();
	}

	@Test
	public void saveAndLoadEmptyFile() {
		assertThrows(StorageFileNotFoundException.class, () -> {
			service.store(new MockMultipartFile("foo", "foo.txt",
					MediaType.TEXT_PLAIN_VALUE, new byte[0]));
		});
	}

	@Test
	public void saveRelativePathNotPermitted() {
		assertThrows(StorageFileNotFoundException.class, () -> {
			service.store(new MockMultipartFile("foo", "../foo.txt",
					MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
		});
	}

	@Test
	public void saveAbsolutePathNotPermitted() {
		assertThrows(StorageFileNotFoundException.class, () -> {
			service.store(new MockMultipartFile("foo", "/etc/passwd",
					MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
		});
	}

	@Test
	@EnabledOnOs({OS.LINUX})
	public void saveAbsolutePathInFilenamePermitted() {
		//Unix file systems (e.g. ext4) allows backslash '\' in file names.
		String fileName="\\etc\\passwd";
		service.store(new MockMultipartFile(fileName, fileName,
				MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
		assertTrue(Files.exists(
				Paths.get(properties.getLocation()).resolve(Paths.get(fileName))));
	}
}
