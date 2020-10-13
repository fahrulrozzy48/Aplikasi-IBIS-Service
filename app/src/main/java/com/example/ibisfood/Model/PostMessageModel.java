package com.example.ibisfood.Model;

public class PostMessageModel {

    private String uEmail;
    private String pTitle;
    private String pKategori;
    private String pDescription;
    private String pImage;
    private String pNoRoom;
    private String postKey;
    private  long pTime;

    //staff masuk
    private String staffEmailJob;
    private String staffImageJob;
    private String staffDescriptionJob;
    private String staffStatusJob;
//    private long staffTimeJob;

//    private  Object pTime;


    public PostMessageModel() {
    }

    public PostMessageModel(String uEmail, String pTitle, String pKategori, String pDescription, String pImage, String pNoRoom, long pTime, String staffEmailJob, String staffImageJob, String staffDescriptionJob, String staffStatusJob) {
        this.uEmail = uEmail;
        this.pTitle = pTitle;
        this.pKategori = pKategori;
        this.pDescription = pDescription;
        this.pImage = pImage;
        this.pNoRoom = pNoRoom;
        this.pTime = pTime;
        this.staffEmailJob = staffEmailJob;
        this.staffImageJob = staffImageJob;
        this.staffDescriptionJob = staffDescriptionJob;
        this.staffStatusJob = staffStatusJob;
//        this.staffTimeJob = staffTimeJob;
    }

    //    public PostMessageModel(String uEmail, String pTitle, String pKategori, String pDescription, String pImage, String pNoRoom, long pTime) {
//        this.uEmail = uEmail;
//        this.pTitle = pTitle;
//        this.pKategori = pKategori;
//        this.pDescription = pDescription;
//        this.pImage = pImage;
//        this.pNoRoom = pNoRoom;
//        this.pTime = pTime;
//    }


    //    public PostMessageModel(String uEmail, String pTitle, String pDescription, String pImage, String pNoRoom) {
//        this.uEmail = uEmail;
//        this.pTitle = pTitle;
//        this.pDescription = pDescription;
//        this.pImage = pImage;
//        this.pNoRoom = pNoRoom;
//        this.pTime = ServerValue.TIMESTAMP;
//    }


    //ini yang di pake
//    public PostMessageModel(String uEmail, String pTitle, String pDescription, String pImage, String pNoRoom, long pTime) {
//        this.uEmail = uEmail;
//        this.pTitle = pTitle;
//        this.pDescription = pDescription;
//        this.pImage = pImage;
//        this.pNoRoom = pNoRoom;
//        this.pTime = pTime;
//    }



    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpKategori() {
        return pKategori;
    }

    public String setpKategori(String pKategori) {
        this.pKategori = pKategori;
        return pKategori;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpNoRoom() {
        return pNoRoom;
    }

    public void setpNoRoom(String pNoRoom) {
        this.pNoRoom = pNoRoom;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public long getpTime() {
        return pTime;
    }

    public void setpTime(long pTime) {
        this.pTime = pTime;
    }

//        public Object getpTime() {
//        return pTime;
//    }
//
//    public void setpTime(Object pTime) {
//        this.pTime = pTime;
//    }
//

//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }


    //staff

    public String getStaffEmailJob() {
        return staffEmailJob;
    }

    public void setStaffEmailJob(String staffEmailJob) {
        this.staffEmailJob = staffEmailJob;
    }

    public String getStaffImageJob() {
        return staffImageJob;
    }

    public void setStaffImageJob(String staffImageJob) {
        this.staffImageJob = staffImageJob;
    }

    public String getStaffDescriptionJob() {
        return staffDescriptionJob;
    }

    public void setStaffDescriptionJob(String staffDescriptionJob) {
        this.staffDescriptionJob = staffDescriptionJob;
    }

    public String getStaffStatusJob() {
        return staffStatusJob;
    }

    public void setStaffStatusJob(String staffStatusJob) {
        this.staffStatusJob = staffStatusJob;
    }
//
//    public long getStaffTimeJob() {
//        return staffTimeJob;
//    }
//
//    public void setStaffTimeJob(long staffTimeJob) {
//        this.staffTimeJob = staffTimeJob;
//    }
}
