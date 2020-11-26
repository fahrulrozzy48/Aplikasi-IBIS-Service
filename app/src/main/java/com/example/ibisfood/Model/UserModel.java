package com.example.ibisfood.Model;

public class UserModel {

    private String Email;
    private String UserLevel;
    private String Username;

    public UserModel() {
    }

    public UserModel(String email, String userLevel, String username) {
        Email = email;
        UserLevel = userLevel;
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(String userLevel) {
        UserLevel = userLevel;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
