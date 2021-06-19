package com.gilbert.test.uploadingfiles.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gilbertwang
 */
@ConfigurationProperties("storage")
@Data
public class StorageProperties {

	private String location;
}
