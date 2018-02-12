package com.example.dedra.bukukerjamandor.model;

import java.io.Serializable;

/**
 * Created by AW on 2/13/2018.
 */

public class Material implements Serializable {
    private String kode_material, nama_material, unit;


    public Material(){

    }

    public Material(String kode_material, String nama_material, String unit) {
        this.kode_material = kode_material;
        this.nama_material = nama_material;
        this.unit = unit;
    }

    public String getKode_material() {
        return kode_material;
    }

    public void setKode_material(String kode_material) {
        this.kode_material = kode_material;
    }

    public String getNama_material() {
        return nama_material;
    }

    public void setNama_material(String nama_material) {
        this.nama_material = nama_material;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
