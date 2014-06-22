package org.mytraffic.pub;

import org.craftercms.profile.api.Profile;

/**
 * Represents a My Traffic user.
 *
 * @author avasquez
 * @author mariobarque
 */
public class User {

    public static final String FIRST_NAME_ATTRIBUTE = "firstName";
    public static final String LAST_NAME_ATTRIBUTE = "lastName";
    public static final String PHONE_NUMBER_ATTRIBUTE = "phoneNumber";

    private String id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User() {
    }

    public User(Profile profile) {
        this.id = profile.getId().toString();
        this.username = profile.getUsername();
        this.password = profile.getPassword();
        this.email = profile.getEmail();
        this.firstName = (String) profile.getAttribute("firstName");
        this.lastName = (String) profile.getAttribute("lastName");
        this.phoneNumber = (String) profile.getAttribute("phoneNumber");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
