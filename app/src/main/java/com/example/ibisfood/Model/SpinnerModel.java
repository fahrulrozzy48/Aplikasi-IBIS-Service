package com.example.ibisfood.Model;

public class SpinnerModel {
    String nameKategori;
    private String postKey;

    public SpinnerModel() {
    }

    public SpinnerModel(String nameKategori) {
        this.nameKategori = nameKategori;
    }

    public String getNameKategori() {
        return nameKategori;
    }

    public void setNameKategori(String nameKategori) {
        this.nameKategori = nameKategori;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }
}
