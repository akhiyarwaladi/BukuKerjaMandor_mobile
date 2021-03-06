package com.example.dedra.bukukerjamandor.model;

import java.io.Serializable;

/**
 * Created by AW on 2/13/2018.
 */

public class Pegawai implements Serializable {
    private String id_pegawai, nama_pegawai, panggilan_pegawai, jabatan, status, kode_mandoran;

    public Pegawai(){

    }
    public Pegawai(String id_pegawai, String nama_pegawai, String panggilan_pegawai, String jabatan, String status, String kode_mandoran) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
        this.panggilan_pegawai = panggilan_pegawai;
        this.jabatan = jabatan;
        this.status = status;
        this.kode_mandoran = kode_mandoran;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getPanggilan_pegawai() {
        return panggilan_pegawai;
    }

    public void setPanggilan_pegawai(String panggilan_pegawai) {
        this.panggilan_pegawai = panggilan_pegawai;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKode_mandoran() {return kode_mandoran;}

    public void setKode_mandoran(String kode_mandoran) {
        this.kode_mandoran = kode_mandoran;
    }
}
