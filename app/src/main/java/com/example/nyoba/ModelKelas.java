package com.example.nyoba;

public class ModelKelas {
    String idkelas, kelas;

    public ModelKelas(){

    }

    public ModelKelas(String kelas, String idkelas) {
        this.idkelas = idkelas;
        this.kelas = kelas;

    }

    public String getKelas() {
        return kelas;
    }

    public String setKelas(String kelas) {
        this.kelas = kelas;
        return kelas;
    }

    public String getIdkelas() {
        return idkelas;
    }

    public void setIdkelas(String idkelas) {
        this.idkelas = idkelas;
    }
}

