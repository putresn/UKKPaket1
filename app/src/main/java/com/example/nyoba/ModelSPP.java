package com.example.nyoba;

public class ModelSPP {
    String tahun, nominal, idspp;

    public  ModelSPP (){

    }

    public ModelSPP(String tahun, String nominal, String idspp) {
        this.tahun = tahun;
        this.nominal = nominal;
        this.idspp = idspp;
    }

    public String getTahun() {
        return tahun;
    }

    public String setTahun(String tahun) {
        this.tahun = tahun;
        return tahun;
    }

    public String getNominal() {
        return nominal;
    }

    public String setNominal(String nominal) {
        this.nominal = nominal;
        return nominal;
    }

    public String getIdspp() {
        return idspp;
    }

    public void setIdspp(String idspp) {
        this.idspp = idspp;
    }
}