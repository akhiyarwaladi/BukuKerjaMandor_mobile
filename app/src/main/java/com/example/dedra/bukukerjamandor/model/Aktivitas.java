package com.example.dedra.bukukerjamandor.model;

/**
 * Created by AW on 2/14/2018.
 */

public class Aktivitas {
    private String kode_aktivitas, nama_aktivitas, kode_material;

    public Aktivitas(){

    }

    public Aktivitas(String kode_aktivitas, String nama_aktivitas, String kode_material) {
        this.kode_aktivitas = kode_aktivitas;
        this.nama_aktivitas = nama_aktivitas;
        this.kode_material = kode_material;
    }

    public String getKode_aktivitas() {
        return kode_aktivitas;
    }

    public void setKode_aktivitas(String kode_aktivitas) {
        this.kode_aktivitas = kode_aktivitas;
    }

    public String getNama_aktivitas() {
        return nama_aktivitas;
    }

    public void setNama_aktivitas(String nama_aktivitas) {
        this.nama_aktivitas = nama_aktivitas;
    }

    public String getKode_material() {
        return kode_material;
    }

    public void setKode_material(String kode_material) {
        this.kode_material = kode_material;
    }
}
