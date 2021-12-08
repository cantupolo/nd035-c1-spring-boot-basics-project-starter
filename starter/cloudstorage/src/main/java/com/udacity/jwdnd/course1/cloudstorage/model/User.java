package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * User info for SuperDuperDrive site.
 */
public class User {

    /**
     * User's ID.
     */
    private Integer id;

    /**
     * Username.
     */
    private String userName;

    /**
     * Salt to generate password's hash.
     */
    private String salt;

    /**
     * User's password (hashed).
     */
    private String password;

    /**
     * User's first name.
     */
    private String firstName;

    /**
     * User's last name.
     */
    private String lastName;

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
     * @return {@link #salt}
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt {@link #salt}
     */
    public void setSalt(String salt) {
        this.salt = salt;
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

    /**
     * @return {@link #firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName {@link #firstName}
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return {@link #lastName}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName {@link #lastName}
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
