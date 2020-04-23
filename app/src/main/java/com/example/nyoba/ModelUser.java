package com.example.nyoba;

public class ModelUser {
    String id_petugas, level, nama_petugas, username, password;

    public ModelUser () {

    }

    public ModelUser(String id_petugas, String level, String nama_petugas, String username, String password) {
        this.id_petugas = id_petugas;
        this.level = level;
        this.nama_petugas = nama_petugas;
        this.username = username;
        this.password = password;
    }

    public String getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(String id_petugas) {
        this.id_petugas = id_petugas;
    }

    public String getLevel() {
        return level;
    }

    public String setLevel(String level) {
        this.level = level;
        return level;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public String setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
        return nama_petugas;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}


