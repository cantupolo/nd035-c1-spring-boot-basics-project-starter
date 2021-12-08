package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * User's credential to access a specific site.
 */
public class Credential {

    /**
     * Credential's ID.
     */
    private Integer id;

    /**
     * User's ID.
     */
    private Integer userId;

    /**
     * URL of the site related to this credential.
     */
    private String url;

    /**
     * Credential's username, used to access the site.
     */
    private String userName;

    /**
     * Key to decrypt the password.
     */
    private String key;

    /**
     * Encrypted password.
     */
    private String password;

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
     * @return {@link #url}
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url {@link #url}
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return {@link #userName}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName {@link #userName}
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return {@link #key}
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key {@link #key}
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
