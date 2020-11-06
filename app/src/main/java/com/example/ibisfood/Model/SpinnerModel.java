package com.example.ibisfood.Model;

public class SpinnerModel {
    private String nameKategori;
    private String key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
