package org.mvn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

    private String UploadDir;

    public String getUploadDir() {
        return UploadDir;
    }

    public void setUploadDir(String uploadDir) {
        UploadDir = uploadDir;
    }
}
