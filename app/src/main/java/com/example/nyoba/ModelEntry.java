package com.example.nyoba;

public class ModelEntry {
    String identry, idsiswa, id_petugas, nama, tanggal, jumlah;

    public ModelEntry () {

    }

    public ModelEntry(String identry, String nama, String tanggal, String jumlah) {
        this.identry = identry;
        this.idsiswa = idsiswa;
        this.id_petugas = id_petugas;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
    }

    public String getIdsiswa() {
        return idsiswa;
    }

    public void setIdsiswa(String idsiswa) {
        this.idsiswa = idsiswa;
    }

    public String getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(String id_petugas) {
        this.id_petugas = id_petugas;
    }

    public String getIdentry() {
        return identry;
    }

    public void setIdentry(String identry) {
        this.identry = identry;
    }

    public String getNama() {
        return nama;
    }

    public String setNama(String nama) {
        this.nama = nama;
        return nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String setTanggal(String tanggal) {
        this.tanggal = tanggal;
        return tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String setJumlah(String jumlah) {
        this.jumlah = jumlah;
        return jumlah;
    }
}
