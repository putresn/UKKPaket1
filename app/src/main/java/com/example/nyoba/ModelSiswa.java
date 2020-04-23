package com.example.nyoba;

public class ModelSiswa {
    String nisn, nama, kelas, alamat, notelp, idsiswa;

    public ModelSiswa () {

    }

    public ModelSiswa(String nisn, String nama, String kelas, String alamat, String notelp, String idsiswa) {
        this.nisn = nisn;
        this.nama = nama;
        this.kelas = kelas;
        this.alamat = alamat;
        this.notelp = notelp;
        this.idsiswa = idsiswa;
    }

    public String getNisn() {
        return nisn;
    }

    public String setNisn(String nisn) {
        this.nisn = nisn;
        return nisn;
    }

    public String getNama() {
        return nama;
    }

    public String setNama(String nama) {
        this.nama = nama;
        return nama;
    }

    public String getKelas() {
        return kelas;
    }

    public String setKelas(String kelas) {
        this.kelas = kelas;
        return kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public String setAlamat(String alamat) {
        this.alamat = alamat;
        return alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public String setNotelp(String notelp) {
        this.notelp = notelp;
        return notelp;
    }

    public String getIdsiswa() {
        return idsiswa;
    }

    public void setIdsiswa(String idsiswa) {
        this.idsiswa = idsiswa;
    }
}

