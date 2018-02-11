package com.example.dedra.bukukerjamandor.model;

/**
 * Created by Dedra on 10/12/2017.
 */

public class BKM_material {
    char no_bkm = 10;
    int no_material = 2;
    int no_aktivitas = 2;
    char kode_material = 10;
    int kuantitas = 2;

    //constructors
    public BKM_material(){
    }

    public BKM_material(char kode_material, int kuantitas){
        this.kode_material = kode_material;
        this.kuantitas = kuantitas;
    }

    public BKM_material(char no_bkm, int no_aktivitas, int no_material, char kode_material, int kuantitas){
        this.no_bkm = no_bkm;
        this.no_aktivitas = no_aktivitas;
        this.no_material = no_material;
        this.kode_material = kode_material;
        this.kuantitas = kuantitas;
    }

    //setter
    public void setNo_bkm(char no_bkm){
        this.no_bkm = no_bkm;
    }

    public void setNo_aktivitas(int no_aktivitas){
        this.no_aktivitas = no_aktivitas;
    }

    public void setNo_material(int no_material){
        this.no_material = no_material;
    }

    public void setKode_material(char kode_material){
        this.kode_material = kode_material;
    }

    public void setKuantitas(int kuantitas){
        this.kuantitas = kuantitas;
    }

    //getters
    public char getNo_bkm(){
        return this.no_bkm;
    }

    public int getNo_aktivitas() {
        return no_aktivitas;
    }

    public int getNo_material() {
        return no_material;
    }

    public char getKode_material() {
        return kode_material;
    }

    public int getKuantitas() {
        return kuantitas;
    }
}
