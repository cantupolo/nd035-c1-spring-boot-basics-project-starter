package com.udacity.jwdnd.course1.cloudstorage.model;

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
    private String name;

    /**
     * File's content type.
     */
    private String contentType;

    /**
     * File's size.
     */
    private String size;

    /**
     * File's content.
     */
    private byte[] blob;

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
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
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
     * @return {@link #size}
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size {@link #size}
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return {@link #blob}
     */
    public byte[] getBlob() {
        return blob;
    }

    /**
     * @param blob {@link #blob}
     */
    public void setBlob(byte[] blob) {
        this.blob = blob;
    }
}
