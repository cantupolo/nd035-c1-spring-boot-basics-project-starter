package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;

/**
 * File uploaded by a user.
 */
public class File {

    /**
     * File's ID.
     */
    private Integer id;

    /**
     * User's ID.
     */
    private Integer userId;

    /**
     * File's name.
     */
    private String fileName;

    /**
     * File's content type.
     */
    private String contentType;

    /**
     * File's size.
     */
    private String fileSize;

    /**
     * File's content.
     */
    private byte[] fileData;

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #userId}
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId {@link #userId}
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return {@link #fileName}
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName {@link #fileName}
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return {@link #contentType}
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType {@link #contentType}
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return {@link #fileSize}
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize {@link #fileSize}
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return {@link #fileData}
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * @param fileData {@link #fileData}
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
