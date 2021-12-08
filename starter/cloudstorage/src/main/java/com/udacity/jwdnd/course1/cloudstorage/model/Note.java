package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Note created by a user.
 */
public class Note {

    /**
     * Note's ID.
     */
    private Integer id;

    /**
     * User's ID.
     */
    private Integer userId;

    /**
     * Note's title.
     */
    private String title;

    /**
     * Note's description.
     */
    private String description;

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
     * @return {@link #title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title {@link #title}
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description {@link #description}
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
