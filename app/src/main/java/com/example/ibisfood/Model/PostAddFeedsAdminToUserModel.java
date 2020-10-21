package com.example.ibisfood.Model;

import com.google.firebase.database.ServerValue;

public class PostAddFeedsAdminToUserModel {

    private String adminEmail;
    private String feedsImage;
    private String feedsDesciption;
    private String postKey;
    private Object timeStamp ;

    public PostAddFeedsAdminToUserModel() {
    }

    public PostAddFeedsAdminToUserModel(String adminEmail, String feedsImage, String feedsDesciption) {
        this.adminEmail = adminEmail;
        this.feedsImage = feedsImage;
        this.feedsDesciption = feedsDesciption;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getFeedsImage() {
        return feedsImage;
    }

    public void setFeedsImage(String feedsImage) {
        this.feedsImage = feedsImage;
    }

    public String getFeedsDesciption() {
        return feedsDesciption;
    }

    public void setFeedsDesciption(String feedsDesciption) {
        this.feedsDesciption = feedsDesciption;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
