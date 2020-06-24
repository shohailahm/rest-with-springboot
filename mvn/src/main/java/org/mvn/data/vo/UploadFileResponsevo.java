package org.mvn.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponsevo  implements  Serializable {
    private static final long serialVersionUID=1L;



    private String fileName;

    private String fileDownloadUri;

    private String fileType;

    private long size;

    public UploadFileResponsevo() {
    }

    public UploadFileResponsevo(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUri;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUri= fileDownloadUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadFileResponsevo that = (UploadFileResponsevo) o;
        return size == that.size &&
                fileName.equals(that.fileName) &&
                fileDownloadUri.equals(that.fileDownloadUri) &&
                fileType.equals(that.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileDownloadUri, fileType, size);
    }
}
