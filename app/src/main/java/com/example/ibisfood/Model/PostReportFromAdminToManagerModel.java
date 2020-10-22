package com.example.ibisfood.Model;

import com.google.firebase.database.ServerValue;

public class PostReportFromAdminToManagerModel {

    private String uAdminEmail;
    private String adminPenanggungJawab;
    private String adminKategoriSatu;
    private String adminInputKategoriSatu;
    private String adminKategoriDua;
    private String adminInputKategoriDua;
    private String adminKategoriTiga;
    private String adminInputKategoriTiga;
    private String adminKategoriEmpat;
    private String adminInputKategoriEmpat;
    private String adminKategoriLima;
    private String adminInputKategoriLima;
    private String postAdminKey;
    private  long adminAwalTime;
    private  long adminAkhirTime;
    private  Object adminPostTime;

    public PostReportFromAdminToManagerModel() {
    }

    public PostReportFromAdminToManagerModel(String uAdminEmail, String adminPenanggungJawab, String adminKategoriSatu, String adminInputKategoriSatu, String adminKategoriDua, String adminInputKategoriDua, String adminKategoriTiga, String adminInputKategoriTiga, String adminKategoriEmpat, String adminInputKategoriEmpat, String adminKategoriLima, String adminInputKategoriLima, long adminAwalTime, long adminAkhirTime) {
        this.uAdminEmail = uAdminEmail;
        this.adminPenanggungJawab = adminPenanggungJawab;
        this.adminKategoriSatu = adminKategoriSatu;
        this.adminInputKategoriSatu = adminInputKategoriSatu;
        this.adminKategoriDua = adminKategoriDua;
        this.adminInputKategoriDua = adminInputKategoriDua;
        this.adminKategoriTiga = adminKategoriTiga;
        this.adminInputKategoriTiga = adminInputKategoriTiga;
        this.adminKategoriEmpat = adminKategoriEmpat;
        this.adminInputKategoriEmpat = adminInputKategoriEmpat;
        this.adminKategoriLima = adminKategoriLima;
        this.adminInputKategoriLima = adminInputKategoriLima;
        this.adminAwalTime = adminAwalTime;
        this.adminAkhirTime = adminAkhirTime;
        this.adminPostTime = ServerValue.TIMESTAMP;;
    }

    public String getuAdminEmail() {
        return uAdminEmail;
    }

    public void setuAdminEmail(String uAdminEmail) {
        this.uAdminEmail = uAdminEmail;
    }

    public String getAdminPenanggungJawab() {
        return adminPenanggungJawab;
    }

    public void setAdminPenanggungJawab(String adminPenanggungJawab) {
        this.adminPenanggungJawab = adminPenanggungJawab;
    }



    public String getAdminKategoriSatu() {
        return adminKategoriSatu;
    }

    public String setAdminKategoriSatu(String adminKategoriSatu) {
        this.adminKategoriSatu = adminKategoriSatu;
        return adminKategoriSatu;
    }

    public String getAdminInputKategoriSatu() {
        return adminInputKategoriSatu;
    }

    public void setAdminInputKategoriSatu(String adminInputKategoriSatu) {
        this.adminInputKategoriSatu = adminInputKategoriSatu;
    }



    public String getAdminKategoriDua() {
        return adminKategoriDua;
    }

    public String setAdminKategoriDua(String adminKategoriDua) {
        this.adminKategoriDua = adminKategoriDua;
        return adminKategoriDua;
    }

    public String getAdminInputKategoriDua() {
        return adminInputKategoriDua;
    }

    public void setAdminInputKategoriDua(String adminInputKategoriDua) {
        this.adminInputKategoriDua = adminInputKategoriDua;
    }



    public String getAdminKategoriTiga() {
        return adminKategoriTiga;
    }

    public String setAdminKategoriTiga(String adminKategoriTiga) {
        this.adminKategoriTiga = adminKategoriTiga;
        return adminKategoriTiga;
    }

    public String getAdminInputKategoriTiga() {
        return adminInputKategoriTiga;
    }

    public void setAdminInputKategoriTiga(String adminInputKategoriTiga) {
        this.adminInputKategoriTiga = adminInputKategoriTiga;
    }



    public String getAdminKategoriEmpat() {
        return adminKategoriEmpat;
    }

    public String setAdminKategoriEmpat(String adminKategoriEmpat) {
        this.adminKategoriEmpat = adminKategoriEmpat;
        return adminKategoriEmpat;
    }

    public String getAdminInputKategoriEmpat() {
        return adminInputKategoriEmpat;
    }

    public void setAdminInputKategoriEmpat(String adminInputKategoriEmpat) {
        this.adminInputKategoriEmpat = adminInputKategoriEmpat;
    }




    public String getAdminKategoriLima() {
        return adminKategoriLima;
    }

    public String setAdminKategoriLima(String adminKategoriLima) {
        this.adminKategoriLima = adminKategoriLima;
        return adminKategoriLima;
    }

    public String getAdminInputKategoriLima() {
        return adminInputKategoriLima;
    }

    public void setAdminInputKategoriLima(String adminInputKategoriLima) {
        this.adminInputKategoriLima = adminInputKategoriLima;
    }

    public String getPostAdminKey() {
        return postAdminKey;
    }

    public void setPostAdminKey(String postAdminKey) {
        this.postAdminKey = postAdminKey;
    }

    public long getAdminAwalTime() {
        return adminAwalTime;
    }

    public void setAdminAwalTime(long adminAwalTime) {
        this.adminAwalTime = adminAwalTime;
    }

    public long getAdminAkhirTime() {
        return adminAkhirTime;
    }

    public void setAdminAkhirTime(long adminAkhirTime) {
        this.adminAkhirTime = adminAkhirTime;
    }

    public Object getAdminPostTime() {
        return adminPostTime;
    }

    public void setAdminPostTime(Object adminPostTime) {
        this.adminPostTime = adminPostTime;
    }
}
