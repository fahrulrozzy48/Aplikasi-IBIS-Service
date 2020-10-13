package com.example.ibisfood.Model;

public class PostRequestPermission {

    private String adminRequestEmail;
    private String adminRequestSubjek;
    private String adminRequestDesc;
//    private String adminRequestImage;
    private String postRequestKey;
    private long timeSendRequest;

    private String managerAnswerStatus;
//    private String managerAnswerStatusTolak;
    private String managerAnswerEmail;

    public PostRequestPermission() {
    }

    public PostRequestPermission(String adminRequestEmail, String adminRequestSubjek, String adminRequestDesc, long timeSendRequest, String managerAnswerStatus, String managerAnswerEmail) {
        this.adminRequestEmail = adminRequestEmail;
        this.adminRequestSubjek = adminRequestSubjek;
        this.adminRequestDesc = adminRequestDesc;
        this.timeSendRequest = timeSendRequest;
        this.managerAnswerStatus = managerAnswerStatus;
        this.managerAnswerEmail = managerAnswerEmail;
    }

//    public PostRequestPermission(String adminRequestEmail, String adminRequestSubjek, String adminRequestDesc, long timeSendRequest, String managerAnswerStatus, String managerAnswerEmail) {
//        this.adminRequestEmail = adminRequestEmail;
//        this.adminRequestSubjek = adminRequestSubjek;
//        this.adminRequestDesc = adminRequestDesc;
//        this.timeSendRequest = timeSendRequest;
//        this.managerAnswerStatus = managerAnswerStatus;
//        this.managerAnswerEmail = managerAnswerEmail;
//    }


    public String getAdminRequestEmail() {
        return adminRequestEmail;
    }

    public void setAdminRequestEmail(String adminRequestEmail) {
        this.adminRequestEmail = adminRequestEmail;
    }

    public String getAdminRequestSubjek() {
        return adminRequestSubjek;
    }

    public void setAdminRequestSubjek(String adminRequestSubjek) {
        this.adminRequestSubjek = adminRequestSubjek;
    }

    public String getAdminRequestDesc() {
        return adminRequestDesc;
    }

    public void setAdminRequestDesc(String adminRequestDesc) {
        this.adminRequestDesc = adminRequestDesc;
    }

//    public String getAdminRequestImage() {
//        return adminRequestImage;
//    }
//
//    public void setAdminRequestImage(String adminRequestImage) {
//        this.adminRequestImage = adminRequestImage;
//    }

    public String getPostRequestKey() {
        return postRequestKey;
    }

    public void setPostRequestKey(String postRequestKey) {
        this.postRequestKey = postRequestKey;
    }

    public long getTimeSendRequest() {
        return timeSendRequest;
    }

    public void setTimeSendRequest(long timeSendRequest) {
        this.timeSendRequest = timeSendRequest;
    }

//    public String getManagerAnswerStatus() {
//        return managerAnswerStatus;
//    }
//
//    public void setManagerAnswerStatus(String managerAnswerStatus) {
//        this.managerAnswerStatus = managerAnswerStatus;
//    }

    public String getManagerAnswerStatus() {
        return managerAnswerStatus;
    }

    public String setManagerAnswerStatus(String managerAnswerStatus) {
        this.managerAnswerStatus = managerAnswerStatus;
        return managerAnswerStatus;
    }


//    public String getManagerAnswerStatusTerima() {
//        return managerAnswerStatusTerima;
//    }
//
//    public void setManagerAnswerStatusTerima(String managerAnswerStatusTerima) {
//        this.managerAnswerStatusTerima = managerAnswerStatusTerima;
//    }
//
//    public String getManagerAnswerStatusTolak() {
//        return managerAnswerStatusTolak;
//    }
//
//    public void setManagerAnswerStatusTolak(String managerAnswerStatusTolak) {
//        this.managerAnswerStatusTolak = managerAnswerStatusTolak;
//    }

    public String getManagerAnswerEmail() {
        return managerAnswerEmail;
    }

    public void setManagerAnswerEmail(String managerAnswerEmail) {
        this.managerAnswerEmail = managerAnswerEmail;
    }
}
