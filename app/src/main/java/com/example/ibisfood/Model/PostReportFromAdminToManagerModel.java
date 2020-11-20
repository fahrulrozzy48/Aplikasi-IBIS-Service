package com.example.ibisfood.Model;

import com.google.firebase.database.ServerValue;

public class PostReportFromAdminToManagerModel {

    private String uAdminEmail;
    private String adminPenanggungJawab;
    private String adminKategoriSatu;
    private String adminInputKategoriSatu;
    private String textSelesaiKategoriSatu;
    private String textBelumSelesaiKategoriSatu;
    private String adminInputSelesaiKategoriSatu;
    private String adminInputBelumSelesaiKategoriSatu;
    private String adminInputDeskripsiKategoriSatu;
    private String adminKategoriDua;
    private String adminInputKategoriDua;
    private String textSelesaiKategoriDua;
    private String textBelumSelesaiKategoriDua;
    private String adminInputSelesaiKategoriDua;
    private String adminInputBelumSelesaiKategoriDua;
    private String adminInputDeskripsiKategoriDua;
    private String adminKategoriTiga;
    private String adminInputKategoriTiga;
    private String textSelesaiKategoriTiga;
    private String textBelumSelesaiKategoriTiga;
    private String adminInputSelesaiKategoriTiga;
    private String adminInputBelumSelesaiKategoriTiga;
    private String adminInputDeskripsiKategoriTiga;
    private String adminKategoriEmpat;
    private String adminInputKategoriEmpat;
    private String textSelesaiKategoriEmpat;
    private String textBelumSelesaiKategoriEmpat;
    private String adminInputSelesaiKategoriEmpat;
    private String adminInputBelumSelesaiKategoriEmpat;
    private String adminInputDeskripsiKategoriEmpat;
    private String adminKategoriLima;
    private String adminInputKategoriLima;
    private String textSelesaiKategoriLima;
    private String textBelumSelesaiKategoriLima;
    private String adminInputSelesaiKategoriLima;
    private String adminInputBelumSelesaiKategoriLima;
    private String adminInputDeskripsiKategoriLima;
    private String adminKategoriEnam;
    private String adminInputKategoriEnam;
    private String textSelesaiKategoriEnam;
    private String textBelumSelesaiKategoriEnam;
    private String adminInputSelesaiKategoriEnam;
    private String adminInputBelumSelesaiKategoriEnam;
    private String adminInputDeskripsiKategoriEnam;
    private String postAdminKey;
    private  long adminAwalTime;
    private  long adminAkhirTime;
    private  Object adminPostTime;

    public PostReportFromAdminToManagerModel() {
    }


//
//
//    public PostReportFromAdminToManagerModel(String uAdminEmail, String adminPenanggungJawab, String adminKategoriSatu, String adminInputKategoriSatu, String adminInputSelesaiKategoriSatu, String adminInputBelumSelesaiKategoriSatu, String adminInputDeskripsiKategoriSatu, String adminKategoriDua, String adminInputKategoriDua, String adminInputSelesaiKategoriDua, String adminInputBelumSelesaiKategoriDua, String adminInputDeskripsiKategoriDua, String adminKategoriTiga, String adminInputKategoriTiga, String adminInputSelesaiKategoriTiga, String adminInputBelumSelesaiKategoriTiga, String adminInputDeskripsiKategoriTiga, String adminKategoriEmpat, String adminInputKategoriEmpat, String adminInputSelesaiKategoriEmpat, String adminInputBelumSelesaiKategoriEmpat, String adminInputDeskripsiKategoriEmpat, String adminKategoriLima, String adminInputKategoriLima, String adminInputSelesaiKategoriLima, String adminInputBelumSelesaiKategoriLima, String adminInputDeskripsiKategoriLima, String adminKategoriEnam, String adminInputKategoriEnam, String adminInputSelesaiKategoriEnam, String adminInputBelumSelesaiKategoriEnam, String adminInputDeskripsiKategoriEnam, long adminAwalTime, long adminAkhirTime) {
//        this.uAdminEmail = uAdminEmail;
//        this.adminPenanggungJawab = adminPenanggungJawab;
//        this.adminKategoriSatu = adminKategoriSatu;
//        this.adminInputKategoriSatu = adminInputKategoriSatu;
//        this.adminInputSelesaiKategoriSatu = adminInputSelesaiKategoriSatu;
//        this.adminInputBelumSelesaiKategoriSatu = adminInputBelumSelesaiKategoriSatu;
//        this.adminInputDeskripsiKategoriSatu = adminInputDeskripsiKategoriSatu;
//        this.adminKategoriDua = adminKategoriDua;
//        this.adminInputKategoriDua = adminInputKategoriDua;
//        this.adminInputSelesaiKategoriDua = adminInputSelesaiKategoriDua;
//        this.adminInputBelumSelesaiKategoriDua = adminInputBelumSelesaiKategoriDua;
//        this.adminInputDeskripsiKategoriDua = adminInputDeskripsiKategoriDua;
//        this.adminKategoriTiga = adminKategoriTiga;
//        this.adminInputKategoriTiga = adminInputKategoriTiga;
//        this.adminInputSelesaiKategoriTiga = adminInputSelesaiKategoriTiga;
//        this.adminInputBelumSelesaiKategoriTiga = adminInputBelumSelesaiKategoriTiga;
//        this.adminInputDeskripsiKategoriTiga = adminInputDeskripsiKategoriTiga;
//        this.adminKategoriEmpat = adminKategoriEmpat;
//        this.adminInputKategoriEmpat = adminInputKategoriEmpat;
//        this.adminInputSelesaiKategoriEmpat = adminInputSelesaiKategoriEmpat;
//        this.adminInputBelumSelesaiKategoriEmpat = adminInputBelumSelesaiKategoriEmpat;
//        this.adminInputDeskripsiKategoriEmpat = adminInputDeskripsiKategoriEmpat;
//        this.adminKategoriLima = adminKategoriLima;
//        this.adminInputKategoriLima = adminInputKategoriLima;
//        this.adminInputSelesaiKategoriLima = adminInputSelesaiKategoriLima;
//        this.adminInputBelumSelesaiKategoriLima = adminInputBelumSelesaiKategoriLima;
//        this.adminInputDeskripsiKategoriLima = adminInputDeskripsiKategoriLima;
//        this.adminKategoriEnam = adminKategoriEnam;
//        this.adminInputKategoriEnam = adminInputKategoriEnam;
//        this.adminInputSelesaiKategoriEnam = adminInputSelesaiKategoriEnam;
//        this.adminInputBelumSelesaiKategoriEnam = adminInputBelumSelesaiKategoriEnam;
//        this.adminInputDeskripsiKategoriEnam = adminInputDeskripsiKategoriEnam;
//        this.adminAwalTime = adminAwalTime;
//        this.adminAkhirTime = adminAkhirTime;
//        this.adminPostTime = ServerValue.TIMESTAMP;
//    }

    public PostReportFromAdminToManagerModel(String uAdminEmail, String adminPenanggungJawab, String adminKategoriSatu, String adminInputKategoriSatu, String textSelesaiKategoriSatu, String textBelumSelesaiKategoriSatu, String adminInputSelesaiKategoriSatu, String adminInputBelumSelesaiKategoriSatu, String adminInputDeskripsiKategoriSatu, String adminKategoriDua, String adminInputKategoriDua, String textSelesaiKategoriDua, String textBelumSelesaiKategoriDua, String adminInputSelesaiKategoriDua, String adminInputBelumSelesaiKategoriDua, String adminInputDeskripsiKategoriDua, String adminKategoriTiga, String adminInputKategoriTiga, String textSelesaiKategoriTiga, String textBelumSelesaiKategoriTiga, String adminInputSelesaiKategoriTiga, String adminInputBelumSelesaiKategoriTiga, String adminInputDeskripsiKategoriTiga, String adminKategoriEmpat, String adminInputKategoriEmpat, String textSelesaiKategoriEmpat, String textBelumSelesaiKategoriEmpat, String adminInputSelesaiKategoriEmpat, String adminInputBelumSelesaiKategoriEmpat, String adminInputDeskripsiKategoriEmpat, String adminKategoriLima, String adminInputKategoriLima, String textSelesaiKategoriLima, String textBelumSelesaiKategoriLima, String adminInputSelesaiKategoriLima, String adminInputBelumSelesaiKategoriLima, String adminInputDeskripsiKategoriLima, String adminKategoriEnam, String adminInputKategoriEnam, String textSelesaiKategoriEnam, String textBelumSelesaiKategoriEnam, String adminInputSelesaiKategoriEnam, String adminInputBelumSelesaiKategoriEnam, String adminInputDeskripsiKategoriEnam, long adminAwalTime, long adminAkhirTime) {
        this.uAdminEmail = uAdminEmail;
        this.adminPenanggungJawab = adminPenanggungJawab;
        this.adminKategoriSatu = adminKategoriSatu;
        this.adminInputKategoriSatu = adminInputKategoriSatu;
        this.textSelesaiKategoriSatu = textSelesaiKategoriSatu;
        this.textBelumSelesaiKategoriSatu = textBelumSelesaiKategoriSatu;
        this.adminInputSelesaiKategoriSatu = adminInputSelesaiKategoriSatu;
        this.adminInputBelumSelesaiKategoriSatu = adminInputBelumSelesaiKategoriSatu;
        this.adminInputDeskripsiKategoriSatu = adminInputDeskripsiKategoriSatu;
        this.adminKategoriDua = adminKategoriDua;
        this.adminInputKategoriDua = adminInputKategoriDua;
        this.textSelesaiKategoriDua = textSelesaiKategoriDua;
        this.textBelumSelesaiKategoriDua = textBelumSelesaiKategoriDua;
        this.adminInputSelesaiKategoriDua = adminInputSelesaiKategoriDua;
        this.adminInputBelumSelesaiKategoriDua = adminInputBelumSelesaiKategoriDua;
        this.adminInputDeskripsiKategoriDua = adminInputDeskripsiKategoriDua;
        this.adminKategoriTiga = adminKategoriTiga;
        this.adminInputKategoriTiga = adminInputKategoriTiga;
        this.textSelesaiKategoriTiga = textSelesaiKategoriTiga;
        this.textBelumSelesaiKategoriTiga = textBelumSelesaiKategoriTiga;
        this.adminInputSelesaiKategoriTiga = adminInputSelesaiKategoriTiga;
        this.adminInputBelumSelesaiKategoriTiga = adminInputBelumSelesaiKategoriTiga;
        this.adminInputDeskripsiKategoriTiga = adminInputDeskripsiKategoriTiga;
        this.adminKategoriEmpat = adminKategoriEmpat;
        this.adminInputKategoriEmpat = adminInputKategoriEmpat;
        this.textSelesaiKategoriEmpat = textSelesaiKategoriEmpat;
        this.textBelumSelesaiKategoriEmpat = textBelumSelesaiKategoriEmpat;
        this.adminInputSelesaiKategoriEmpat = adminInputSelesaiKategoriEmpat;
        this.adminInputBelumSelesaiKategoriEmpat = adminInputBelumSelesaiKategoriEmpat;
        this.adminInputDeskripsiKategoriEmpat = adminInputDeskripsiKategoriEmpat;
        this.adminKategoriLima = adminKategoriLima;
        this.adminInputKategoriLima = adminInputKategoriLima;
        this.textSelesaiKategoriLima = textSelesaiKategoriLima;
        this.textBelumSelesaiKategoriLima = textBelumSelesaiKategoriLima;
        this.adminInputSelesaiKategoriLima = adminInputSelesaiKategoriLima;
        this.adminInputBelumSelesaiKategoriLima = adminInputBelumSelesaiKategoriLima;
        this.adminInputDeskripsiKategoriLima = adminInputDeskripsiKategoriLima;
        this.adminKategoriEnam = adminKategoriEnam;
        this.adminInputKategoriEnam = adminInputKategoriEnam;
        this.textSelesaiKategoriEnam = textSelesaiKategoriEnam;
        this.textBelumSelesaiKategoriEnam = textBelumSelesaiKategoriEnam;
        this.adminInputSelesaiKategoriEnam = adminInputSelesaiKategoriEnam;
        this.adminInputBelumSelesaiKategoriEnam = adminInputBelumSelesaiKategoriEnam;
        this.adminInputDeskripsiKategoriEnam = adminInputDeskripsiKategoriEnam;
        this.adminAwalTime = adminAwalTime;
        this.adminAkhirTime = adminAkhirTime;
        this.adminPostTime = ServerValue.TIMESTAMP;
    }

//
//    public String getuAdminEmail() {
//        return uAdminEmail;
//    }
//
//    public void setuAdminEmail(String uAdminEmail) {
//        this.uAdminEmail = uAdminEmail;
//    }
//
//    public String getAdminPenanggungJawab() {
//        return adminPenanggungJawab;
//    }
//
//    public void setAdminPenanggungJawab(String adminPenanggungJawab) {
//        this.adminPenanggungJawab = adminPenanggungJawab;
//    }
//
//    public String getAdminKategoriSatu() {
//        return adminKategoriSatu;
//    }
//
//    public void setAdminKategoriSatu(String adminKategoriSatu) {
//        this.adminKategoriSatu = adminKategoriSatu;
//    }
//
//    public String getAdminInputKategoriSatu() {
//        return adminInputKategoriSatu;
//    }
//
//    public void setAdminInputKategoriSatu(String adminInputKategoriSatu) {
//        this.adminInputKategoriSatu = adminInputKategoriSatu;
//    }
//
//    public String getAdminInputSelesaiKategoriSatu() {
//        return adminInputSelesaiKategoriSatu;
//    }
//
//    public void setAdminInputSelesaiKategoriSatu(String adminInputSelesaiKategoriSatu) {
//        this.adminInputSelesaiKategoriSatu = adminInputSelesaiKategoriSatu;
//    }
//
//    public String getAdminInputBelumSelesaiKategoriSatu() {
//        return adminInputBelumSelesaiKategoriSatu;
//    }
//
//    public void setAdminInputBelumSelesaiKategoriSatu(String adminInputBelumSelesaiKategoriSatu) {
//        this.adminInputBelumSelesaiKategoriSatu = adminInputBelumSelesaiKategoriSatu;
//    }
//
//    public String getAdminInputDeskripsiKategoriSatu() {
//        return adminInputDeskripsiKategoriSatu;
//    }
//
//    public void setAdminInputDeskripsiKategoriSatu(String adminInputDeskripsiKategoriSatu) {
//        this.adminInputDeskripsiKategoriSatu = adminInputDeskripsiKategoriSatu;
//    }
//
//    public String getAdminKategoriDua() {
//        return adminKategoriDua;
//    }
//
//    public void setAdminKategoriDua(String adminKategoriDua) {
//        this.adminKategoriDua = adminKategoriDua;
//    }
//
//    public String getAdminInputKategoriDua() {
//        return adminInputKategoriDua;
//    }
//
//    public void setAdminInputKategoriDua(String adminInputKategoriDua) {
//        this.adminInputKategoriDua = adminInputKategoriDua;
//    }
//
//    public String getAdminInputSelesaiKategoriDua() {
//        return adminInputSelesaiKategoriDua;
//    }
//
//    public void setAdminInputSelesaiKategoriDua(String adminInputSelesaiKategoriDua) {
//        this.adminInputSelesaiKategoriDua = adminInputSelesaiKategoriDua;
//    }
//
//    public String getAdminInputBelumSelesaiKategoriDua() {
//        return adminInputBelumSelesaiKategoriDua;
//    }
//
//    public void setAdminInputBelumSelesaiKategoriDua(String adminInputBelumSelesaiKategoriDua) {
//        this.adminInputBelumSelesaiKategoriDua = adminInputBelumSelesaiKategoriDua;
//    }
//
//    public String getAdminInputDeskripsiKategoriDua() {
//        return adminInputDeskripsiKategoriDua;
//    }
//
//    public void setAdminInputDeskripsiKategoriDua(String adminInputDeskripsiKategoriDua) {
//        this.adminInputDeskripsiKategoriDua = adminInputDeskripsiKategoriDua;
//    }
//
//    public String getAdminKategoriTiga() {
//        return adminKategoriTiga;
//    }
//
//    public void setAdminKategoriTiga(String adminKategoriTiga) {
//        this.adminKategoriTiga = adminKategoriTiga;
//    }
//
//    public String getAdminInputKategoriTiga() {
//        return adminInputKategoriTiga;
//    }
//
//    public void setAdminInputKategoriTiga(String adminInputKategoriTiga) {
//        this.adminInputKategoriTiga = adminInputKategoriTiga;
//    }
//
//    public String getAdminInputSelesaiKategoriTiga() {
//        return adminInputSelesaiKategoriTiga;
//    }
//
//    public void setAdminInputSelesaiKategoriTiga(String adminInputSelesaiKategoriTiga) {
//        this.adminInputSelesaiKategoriTiga = adminInputSelesaiKategoriTiga;
//    }
//
//    public String getAdminInputBelumSelesaiKategoriTiga() {
//        return adminInputBelumSelesaiKategoriTiga;
//    }
//
//    public void setAdminInputBelumSelesaiKategoriTiga(String adminInputBelumSelesaiKategoriTiga) {
//        this.adminInputBelumSelesaiKategoriTiga = adminInputBelumSelesaiKategoriTiga;
//    }
//
//    public String getAdminInputDeskripsiKategoriTiga() {
//        return adminInputDeskripsiKategoriTiga;
//    }
//
//    public void setAdminInputDeskripsiKategoriTiga(String adminInputDeskripsiKategoriTiga) {
//        this.adminInputDeskripsiKategoriTiga = adminInputDeskripsiKategoriTiga;
//    }
//
//    public String getAdminKategoriEmpat() {
//        return adminKategoriEmpat;
//    }
//
//    public void setAdminKategoriEmpat(String adminKategoriEmpat) {
//        this.adminKategoriEmpat = adminKategoriEmpat;
//    }
//
//    public String getAdminInputKategoriEmpat() {
//        return adminInputKategoriEmpat;
//    }
//
//    public void setAdminInputKategoriEmpat(String adminInputKategoriEmpat) {
//        this.adminInputKategoriEmpat = adminInputKategoriEmpat;
//    }
//
//    public String getAdminInputSelesaiKategoriEmpat() {
//        return adminInputSelesaiKategoriEmpat;
//    }
//
//    public void setAdminInputSelesaiKategoriEmpat(String adminInputSelesaiKategoriEmpat) {
//        this.adminInputSelesaiKategoriEmpat = adminInputSelesaiKategoriEmpat;
//    }
//
//    public String getAdminInputBelumSelesaiKategoriEmpat() {
//        return adminInputBelumSelesaiKategoriEmpat;
//    }
//
//    public void setAdminInputBelumSelesaiKategoriEmpat(String adminInputBelumSelesaiKategoriEmpat) {
//        this.adminInputBelumSelesaiKategoriEmpat = adminInputBelumSelesaiKategoriEmpat;
//    }
//
//    public String getAdminInputDeskripsiKategoriEmpat() {
//        return adminInputDeskripsiKategoriEmpat;
//    }
//
//    public void setAdminInputDeskripsiKategoriEmpat(String adminInputDeskripsiKategoriEmpat) {
//        this.adminInputDeskripsiKategoriEmpat = adminInputDeskripsiKategoriEmpat;
//    }
//
//    public String getAdminKategoriLima() {
//        return adminKategoriLima;
//    }
//
//    public void setAdminKategoriLima(String adminKategoriLima) {
//        this.adminKategoriLima = adminKategoriLima;
//    }
//
//    public String getAdminInputKategoriLima() {
//        return adminInputKategoriLima;
//    }
//
//    public void setAdminInputKategoriLima(String adminInputKategoriLima) {
//        this.adminInputKategoriLima = adminInputKategoriLima;
//    }
//
//    public String getAdminInputSelesaiKategoriLima() {
//        return adminInputSelesaiKategoriLima;
//    }
//
//    public void setAdminInputSelesaiKategoriLima(String adminInputSelesaiKategoriLima) {
//        this.adminInputSelesaiKategoriLima = adminInputSelesaiKategoriLima;
//    }
//
//    public String getAdminInputBelumSelesaiKategoriLima() {
//        return adminInputBelumSelesaiKategoriLima;
//    }
//
//    public void setAdminInputBelumSelesaiKategoriLima(String adminInputBelumSelesaiKategoriLima) {
//        this.adminInputBelumSelesaiKategoriLima = adminInputBelumSelesaiKategoriLima;
//    }
//
//    public String getAdminInputDeskripsiKategoriLima() {
//        return adminInputDeskripsiKategoriLima;
//    }
//
//    public void setAdminInputDeskripsiKategoriLima(String adminInputDeskripsiKategoriLima) {
//        this.adminInputDeskripsiKategoriLima = adminInputDeskripsiKategoriLima;
//    }
//
//    public String getAdminKategoriEnam() {
//        return adminKategoriEnam;
//    }
//
//    public void setAdminKategoriEnam(String adminKategoriEnam) {
//        this.adminKategoriEnam = adminKategoriEnam;
//    }
//
//    public String getAdminInputKategoriEnam() {
//        return adminInputKategoriEnam;
//    }
//
//    public void setAdminInputKategoriEnam(String adminInputKategoriEnam) {
//        this.adminInputKategoriEnam = adminInputKategoriEnam;
//    }
//
//    public String getAdminInputSelesaiKategoriEnam() {
//        return adminInputSelesaiKategoriEnam;
//    }
//
//    public void setAdminInputSelesaiKategoriEnam(String adminInputSelesaiKategoriEnam) {
//        this.adminInputSelesaiKategoriEnam = adminInputSelesaiKategoriEnam;
//    }
//
//    public String getAdminInputBelumSelesaiKategoriEnam() {
//        return adminInputBelumSelesaiKategoriEnam;
//    }
//
//    public void setAdminInputBelumSelesaiKategoriEnam(String adminInputBelumSelesaiKategoriEnam) {
//        this.adminInputBelumSelesaiKategoriEnam = adminInputBelumSelesaiKategoriEnam;
//    }
//
//    public String getAdminInputDeskripsiKategoriEnam() {
//        return adminInputDeskripsiKategoriEnam;
//    }
//
//    public void setAdminInputDeskripsiKategoriEnam(String adminInputDeskripsiKategoriEnam) {
//        this.adminInputDeskripsiKategoriEnam = adminInputDeskripsiKategoriEnam;
//    }
//
//    public String getPostAdminKey() {
//        return postAdminKey;
//    }
//
//    public void setPostAdminKey(String postAdminKey) {
//        this.postAdminKey = postAdminKey;
//    }
//
//    public long getAdminAwalTime() {
//        return adminAwalTime;
//    }
//
//    public void setAdminAwalTime(long adminAwalTime) {
//        this.adminAwalTime = adminAwalTime;
//    }
//
//    public long getAdminAkhirTime() {
//        return adminAkhirTime;
//    }
//
//    public void setAdminAkhirTime(long adminAkhirTime) {
//        this.adminAkhirTime = adminAkhirTime;
//    }
//
//    public Object getAdminPostTime() {
//        return adminPostTime;
//    }
//
//    public void setAdminPostTime(Object adminPostTime) {
//        this.adminPostTime = adminPostTime;
//    }


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

    public void setAdminKategoriSatu(String adminKategoriSatu) {
        this.adminKategoriSatu = adminKategoriSatu;
    }

    public String getAdminInputKategoriSatu() {
        return adminInputKategoriSatu;
    }

    public void setAdminInputKategoriSatu(String adminInputKategoriSatu) {
        this.adminInputKategoriSatu = adminInputKategoriSatu;
    }

    public String getTextSelesaiKategoriSatu() {
        return textSelesaiKategoriSatu;
    }

    public void setTextSelesaiKategoriSatu(String textSelesaiKategoriSatu) {
        this.textSelesaiKategoriSatu = textSelesaiKategoriSatu;
    }

    public String getTextBelumSelesaiKategoriSatu() {
        return textBelumSelesaiKategoriSatu;
    }

    public void setTextBelumSelesaiKategoriSatu(String textBelumSelesaiKategoriSatu) {
        this.textBelumSelesaiKategoriSatu = textBelumSelesaiKategoriSatu;
    }

    public String getAdminInputSelesaiKategoriSatu() {
        return adminInputSelesaiKategoriSatu;
    }

    public void setAdminInputSelesaiKategoriSatu(String adminInputSelesaiKategoriSatu) {
        this.adminInputSelesaiKategoriSatu = adminInputSelesaiKategoriSatu;
    }

    public String getAdminInputBelumSelesaiKategoriSatu() {
        return adminInputBelumSelesaiKategoriSatu;
    }

    public void setAdminInputBelumSelesaiKategoriSatu(String adminInputBelumSelesaiKategoriSatu) {
        this.adminInputBelumSelesaiKategoriSatu = adminInputBelumSelesaiKategoriSatu;
    }

    public String getAdminInputDeskripsiKategoriSatu() {
        return adminInputDeskripsiKategoriSatu;
    }

    public void setAdminInputDeskripsiKategoriSatu(String adminInputDeskripsiKategoriSatu) {
        this.adminInputDeskripsiKategoriSatu = adminInputDeskripsiKategoriSatu;
    }

    public String getAdminKategoriDua() {
        return adminKategoriDua;
    }

    public void setAdminKategoriDua(String adminKategoriDua) {
        this.adminKategoriDua = adminKategoriDua;
    }

    public String getAdminInputKategoriDua() {
        return adminInputKategoriDua;
    }

    public void setAdminInputKategoriDua(String adminInputKategoriDua) {
        this.adminInputKategoriDua = adminInputKategoriDua;
    }

    public String getTextSelesaiKategoriDua() {
        return textSelesaiKategoriDua;
    }

    public void setTextSelesaiKategoriDua(String textSelesaiKategoriDua) {
        this.textSelesaiKategoriDua = textSelesaiKategoriDua;
    }

    public String getTextBelumSelesaiKategoriDua() {
        return textBelumSelesaiKategoriDua;
    }

    public void setTextBelumSelesaiKategoriDua(String textBelumSelesaiKategoriDua) {
        this.textBelumSelesaiKategoriDua = textBelumSelesaiKategoriDua;
    }

    public String getAdminInputSelesaiKategoriDua() {
        return adminInputSelesaiKategoriDua;
    }

    public void setAdminInputSelesaiKategoriDua(String adminInputSelesaiKategoriDua) {
        this.adminInputSelesaiKategoriDua = adminInputSelesaiKategoriDua;
    }

    public String getAdminInputBelumSelesaiKategoriDua() {
        return adminInputBelumSelesaiKategoriDua;
    }

    public void setAdminInputBelumSelesaiKategoriDua(String adminInputBelumSelesaiKategoriDua) {
        this.adminInputBelumSelesaiKategoriDua = adminInputBelumSelesaiKategoriDua;
    }

    public String getAdminInputDeskripsiKategoriDua() {
        return adminInputDeskripsiKategoriDua;
    }

    public void setAdminInputDeskripsiKategoriDua(String adminInputDeskripsiKategoriDua) {
        this.adminInputDeskripsiKategoriDua = adminInputDeskripsiKategoriDua;
    }

    public String getAdminKategoriTiga() {
        return adminKategoriTiga;
    }

    public void setAdminKategoriTiga(String adminKategoriTiga) {
        this.adminKategoriTiga = adminKategoriTiga;
    }

    public String getAdminInputKategoriTiga() {
        return adminInputKategoriTiga;
    }

    public void setAdminInputKategoriTiga(String adminInputKategoriTiga) {
        this.adminInputKategoriTiga = adminInputKategoriTiga;
    }

    public String getTextSelesaiKategoriTiga() {
        return textSelesaiKategoriTiga;
    }

    public void setTextSelesaiKategoriTiga(String textSelesaiKategoriTiga) {
        this.textSelesaiKategoriTiga = textSelesaiKategoriTiga;
    }

    public String getTextBelumSelesaiKategoriTiga() {
        return textBelumSelesaiKategoriTiga;
    }

    public void setTextBelumSelesaiKategoriTiga(String textBelumSelesaiKategoriTiga) {
        this.textBelumSelesaiKategoriTiga = textBelumSelesaiKategoriTiga;
    }

    public String getAdminInputSelesaiKategoriTiga() {
        return adminInputSelesaiKategoriTiga;
    }

    public void setAdminInputSelesaiKategoriTiga(String adminInputSelesaiKategoriTiga) {
        this.adminInputSelesaiKategoriTiga = adminInputSelesaiKategoriTiga;
    }

    public String getAdminInputBelumSelesaiKategoriTiga() {
        return adminInputBelumSelesaiKategoriTiga;
    }

    public void setAdminInputBelumSelesaiKategoriTiga(String adminInputBelumSelesaiKategoriTiga) {
        this.adminInputBelumSelesaiKategoriTiga = adminInputBelumSelesaiKategoriTiga;
    }

    public String getAdminInputDeskripsiKategoriTiga() {
        return adminInputDeskripsiKategoriTiga;
    }

    public void setAdminInputDeskripsiKategoriTiga(String adminInputDeskripsiKategoriTiga) {
        this.adminInputDeskripsiKategoriTiga = adminInputDeskripsiKategoriTiga;
    }

    public String getAdminKategoriEmpat() {
        return adminKategoriEmpat;
    }

    public void setAdminKategoriEmpat(String adminKategoriEmpat) {
        this.adminKategoriEmpat = adminKategoriEmpat;
    }

    public String getAdminInputKategoriEmpat() {
        return adminInputKategoriEmpat;
    }

    public void setAdminInputKategoriEmpat(String adminInputKategoriEmpat) {
        this.adminInputKategoriEmpat = adminInputKategoriEmpat;
    }

    public String getTextSelesaiKategoriEmpat() {
        return textSelesaiKategoriEmpat;
    }

    public void setTextSelesaiKategoriEmpat(String textSelesaiKategoriEmpat) {
        this.textSelesaiKategoriEmpat = textSelesaiKategoriEmpat;
    }

    public String getTextBelumSelesaiKategoriEmpat() {
        return textBelumSelesaiKategoriEmpat;
    }

    public void setTextBelumSelesaiKategoriEmpat(String textBelumSelesaiKategoriEmpat) {
        this.textBelumSelesaiKategoriEmpat = textBelumSelesaiKategoriEmpat;
    }

    public String getAdminInputSelesaiKategoriEmpat() {
        return adminInputSelesaiKategoriEmpat;
    }

    public void setAdminInputSelesaiKategoriEmpat(String adminInputSelesaiKategoriEmpat) {
        this.adminInputSelesaiKategoriEmpat = adminInputSelesaiKategoriEmpat;
    }

    public String getAdminInputBelumSelesaiKategoriEmpat() {
        return adminInputBelumSelesaiKategoriEmpat;
    }

    public void setAdminInputBelumSelesaiKategoriEmpat(String adminInputBelumSelesaiKategoriEmpat) {
        this.adminInputBelumSelesaiKategoriEmpat = adminInputBelumSelesaiKategoriEmpat;
    }

    public String getAdminInputDeskripsiKategoriEmpat() {
        return adminInputDeskripsiKategoriEmpat;
    }

    public void setAdminInputDeskripsiKategoriEmpat(String adminInputDeskripsiKategoriEmpat) {
        this.adminInputDeskripsiKategoriEmpat = adminInputDeskripsiKategoriEmpat;
    }

    public String getAdminKategoriLima() {
        return adminKategoriLima;
    }

    public void setAdminKategoriLima(String adminKategoriLima) {
        this.adminKategoriLima = adminKategoriLima;
    }

    public String getAdminInputKategoriLima() {
        return adminInputKategoriLima;
    }

    public void setAdminInputKategoriLima(String adminInputKategoriLima) {
        this.adminInputKategoriLima = adminInputKategoriLima;
    }

    public String getTextSelesaiKategoriLima() {
        return textSelesaiKategoriLima;
    }

    public void setTextSelesaiKategoriLima(String textSelesaiKategoriLima) {
        this.textSelesaiKategoriLima = textSelesaiKategoriLima;
    }

    public String getTextBelumSelesaiKategoriLima() {
        return textBelumSelesaiKategoriLima;
    }

    public void setTextBelumSelesaiKategoriLima(String textBelumSelesaiKategoriLima) {
        this.textBelumSelesaiKategoriLima = textBelumSelesaiKategoriLima;
    }

    public String getAdminInputSelesaiKategoriLima() {
        return adminInputSelesaiKategoriLima;
    }

    public void setAdminInputSelesaiKategoriLima(String adminInputSelesaiKategoriLima) {
        this.adminInputSelesaiKategoriLima = adminInputSelesaiKategoriLima;
    }

    public String getAdminInputBelumSelesaiKategoriLima() {
        return adminInputBelumSelesaiKategoriLima;
    }

    public void setAdminInputBelumSelesaiKategoriLima(String adminInputBelumSelesaiKategoriLima) {
        this.adminInputBelumSelesaiKategoriLima = adminInputBelumSelesaiKategoriLima;
    }

    public String getAdminInputDeskripsiKategoriLima() {
        return adminInputDeskripsiKategoriLima;
    }

    public void setAdminInputDeskripsiKategoriLima(String adminInputDeskripsiKategoriLima) {
        this.adminInputDeskripsiKategoriLima = adminInputDeskripsiKategoriLima;
    }

    public String getAdminKategoriEnam() {
        return adminKategoriEnam;
    }

    public void setAdminKategoriEnam(String adminKategoriEnam) {
        this.adminKategoriEnam = adminKategoriEnam;
    }

    public String getAdminInputKategoriEnam() {
        return adminInputKategoriEnam;
    }

    public void setAdminInputKategoriEnam(String adminInputKategoriEnam) {
        this.adminInputKategoriEnam = adminInputKategoriEnam;
    }

    public String getTextSelesaiKategoriEnam() {
        return textSelesaiKategoriEnam;
    }

    public void setTextSelesaiKategoriEnam(String textSelesaiKategoriEnam) {
        this.textSelesaiKategoriEnam = textSelesaiKategoriEnam;
    }

    public String getTextBelumSelesaiKategoriEnam() {
        return textBelumSelesaiKategoriEnam;
    }

    public void setTextBelumSelesaiKategoriEnam(String textBelumSelesaiKategoriEnam) {
        this.textBelumSelesaiKategoriEnam = textBelumSelesaiKategoriEnam;
    }

    public String getAdminInputSelesaiKategoriEnam() {
        return adminInputSelesaiKategoriEnam;
    }

    public void setAdminInputSelesaiKategoriEnam(String adminInputSelesaiKategoriEnam) {
        this.adminInputSelesaiKategoriEnam = adminInputSelesaiKategoriEnam;
    }

    public String getAdminInputBelumSelesaiKategoriEnam() {
        return adminInputBelumSelesaiKategoriEnam;
    }

    public void setAdminInputBelumSelesaiKategoriEnam(String adminInputBelumSelesaiKategoriEnam) {
        this.adminInputBelumSelesaiKategoriEnam = adminInputBelumSelesaiKategoriEnam;
    }

    public String getAdminInputDeskripsiKategoriEnam() {
        return adminInputDeskripsiKategoriEnam;
    }

    public void setAdminInputDeskripsiKategoriEnam(String adminInputDeskripsiKategoriEnam) {
        this.adminInputDeskripsiKategoriEnam = adminInputDeskripsiKategoriEnam;
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
