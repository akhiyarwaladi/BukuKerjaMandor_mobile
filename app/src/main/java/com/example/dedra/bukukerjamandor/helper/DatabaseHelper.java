package com.example.dedra.bukukerjamandor.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dedra.bukukerjamandor.model.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Dedra on 10/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "bukukerjamandor.db";

    //Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_BKM = "bkm";
    private static final String TABLE_BKM_AKTIVITAS = "bkm_aktivitas";
    private static final String TABLE_BKM_MATERIAL = "bkm_material";
    private static final String TABLE_BKM_PEGAWAI = "bkm_pegawai";
    private static final String TABLE_RKH = "rkh";
    private static final String TABLE_RKH_AKTIVITAS = "rkh_aktivitas";
    private static final String TABLE_RKH_MATERIAL = "rkh_material";
    private static final String TABLE_RKH_PEGAWAI = "rkh_pegawai";
    private static final String TABLE_AKTIVITAS = "aktivitas";
    private static final String TABLE_MATERIAL = "material";
    private static final String TABLE_PEGAWAI = "pegawai";

    //User Table Columns names
    private static final String KEY_USER_ID_PEGAWAI = "id_pegawai";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_API_KEY = "api_key";
    private static final String KEY_USER_CREATED_AT = "created_at";

    //Aktivitas Table Columns names
    private static final String KEY_AKTIVITAS_KODE_AKTIVITAS = "kode_aktivitas";
    private static final String KEY_AKTIVITAS_NAMA_AKTIVITAS = "nama_aktivitas";

    //BKM Table Columns names
    private static final String KEY_BKM_NO_BKM = "no_bkm";
    private static final String KEY_BKM_TGL_BKM = "tgl_bkm";
    private static final String KEY_BKM_NO_RKH = "no_rkh";

    //BKM_AKTIVITAS Table Columns names
    private static final String KEY_BKM_AKT_NO_BKM = "no_bkm";
    private static final String KEY_BKM_AKT_NO_AKTIVITAS = "no_aktivitas";
    private static final String KEY_BKM_AKT_KODE_AKTIVITAS = "kode_aktivitas";
    private static final String KEY_BKM_AKT_SEKTOR_TANAM = "sektor_tanam";
    private static final String KEY_BKM_AKT_BLOK_TANAM = "blok_tanam";
    private static final String KEY_BKM_AKT_FOTO_AKTIVITAS = "foto_aktivitas";

    //BKM_MATERIAL Table Columns names
    private static final String KEY_BKM_MAT_NO_BKM = "no_bkm";
    private static final String KEY_BKM_MAT_NO_AKTIVITAS = "no_aktivitas";
    private static final String KEY_BKM_MAT_NO_MATERIAL = "no_material";
    private static final String KEY_BKM_MAT_KODE_MATERIAL = "kode_material";
    private static final String KEY_BKM_MAT_KUANTITAS = "kuantitas";

    //BKM_PEGAWAI Table Columns names
    private static final String KEY_BKM_PEG_NO_BKM = "no_bkm";
    private static final String KEY_BKM_PEG_NO_AKTIVITAS = "no_aktivitas";
    private static final String KEY_BKM_PEG_NO_PEGAWAI = "no_pegawai";
    private static final String KEY_BKM_PEG_ID_PEGAWAI = "id_pegawai";
    private static final String KEY_BKM_PEG_HASIL_KERJA_RIIL = "hasil_kerja_riil";

    //MATERIAL Table Columns names
    private static final String KEY_MATERIAL_KODE_MATERIAL = "kode_material";
    private static final String KEY_MATERIAL_NAMA_MATERIAL = "nama_material";
    private static final String KEY_MATERIAL_UNIT = "unit";

    //PEGAWAI Table Columns names
    private static final String KEY_PEGAWAI_ID_PEGAWAI = "id_pegawai";
    private static final String KEY_PEGAWAI_NAMA_PEGAWAI = "nama_pegawai";
    private static final String KEY_PEGAWAI_PANGGILAN_PEGAWAI = "panggilan_pegawai";
    private static final String KEY_PEGAWAI_JABATAN = "jabatan";
    private static final String KEY_PEGAWAI_STATUS = "status";
    private static final String KEY_PEGAWAI_KODE_MANDORAN = "kode_mandoran";

    //RKH Table Columns names
    private static final String KEY_RKH_NO_RKH = "no_rkh";
    private static final String KEY_RKH_TGL_KEGIATAN = "tgl_kegiatan";
    private static final String KEY_RKH_ID_PEGAWAI = "id_pegawai";

    //RKH_AKTIVITAS Columns names
    private static final String KEY_RKH_AKT_NO_RKH = "no_rkh";
    private static final String KEY_RKH_AKT_NO_AKTIVITAS = "no_aktivitas";
    private static final String KEY_RKH_AKT_KODE_AKTIVITAS = "kode_aktivitas";
    private static final String KEY_RKH_AKT_SEKTOR_TANAM = "sektor_tanam";
    private static final String KEY_RKH_AKT_BLOK_TANAM = "blok_tanam";

    //BKM_MATERIAL Table Columns names
    private static final String KEY_RKH_MAT_NO_RKH = "no_rkh";
    private static final String KEY_RKH_MAT_NO_AKTIVITAS = "no_aktivitas";
    //private static final String KEY_RKH_MAT_NO_MATERIAL = "no_material";
    private static final String KEY_RKH_MAT_KODE_MATERIAL = "kode_material";

    //BKM_PEGAWAI Table Columns names
    private static final String KEY_RKH_PEG_NO_RKH = "no_rkh";
    private static final String KEY_RKH_PEG_NO_AKTIVITAS = "no_aktivitas";
    //private static final String KEY_RKH_PEG_NO_PEGAWAI = "no_pegawai";
    private static final String KEY_RKH_PEG_ID_PEGAWAI = "id_pegawai";
    private static final String KEY_RKH_PEG_HASIL_KERJA_STD = "hasil_kerja_std";

    /*-------------------Table Create Statements-------------------*/

    //USER create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + KEY_USER_ID_PEGAWAI + " CHAR(10), "
            + KEY_USER_USERNAME + " VARCHAR(10) UNIQUE PRIMARY KEY, " + KEY_USER_API_KEY + " TEXT, "
            + KEY_USER_CREATED_AT + " TEXT" + ")";

    //AKTIVITAS create statement
    private static final String CREATE_TABLE_AKTIVITAS = "CREATE TABLE "
            + TABLE_AKTIVITAS + "(" + KEY_AKTIVITAS_KODE_AKTIVITAS
            + " CHAR(10) PRIMARY KEY, " + KEY_AKTIVITAS_NAMA_AKTIVITAS + " TEXT" + ")";

    //BKM create statement
    private static final String CREATE_TABLE_BKM = "CREATE TABLE " + TABLE_BKM + "("
            + KEY_BKM_NO_BKM+ " CHAR(10) PRIMARY KEY, " + KEY_BKM_TGL_BKM
            + " DATE, " + KEY_BKM_NO_RKH + " CHAR(10)" + ")";

    //BKM_AKTIVITAS create statement
    private static final String CREATE_TABLE_BKM_AKTIVITAS = "CREATE TABLE " + TABLE_BKM_AKTIVITAS
            + "(" + KEY_BKM_AKT_NO_BKM + " CHAR(10), "
            + KEY_BKM_AKT_NO_AKTIVITAS + " INTEGER(2) PRIMARY KEY, " + KEY_BKM_AKT_KODE_AKTIVITAS
            + " CHAR(10), " + KEY_BKM_AKT_SEKTOR_TANAM + " VARCHAR(3), " + KEY_BKM_AKT_BLOK_TANAM
            + " VARCHAR(3), " + KEY_BKM_AKT_FOTO_AKTIVITAS + " BLOB" + ")";

    //BKM_MATERIAL create statement
    private static final String CREATE_TABLE_BKM_MATERIAL = "CREATE TABLE " + TABLE_BKM_MATERIAL
            + "(" + KEY_BKM_MAT_NO_BKM + " CHAR(10), "
            + KEY_BKM_MAT_NO_AKTIVITAS + " INTEGER(2), " + KEY_BKM_MAT_NO_MATERIAL
            + " INTEGER(2) PRIMARY KEY, "
            + KEY_BKM_MAT_KODE_MATERIAL + " CHAR(10), " + KEY_BKM_MAT_KUANTITAS + " DECIMAL(3,2)"
            + ")";

    //BKM_PEGAWAI create statement
    private static final String CREATE_TABLE_BKM_PEGAWAI = "CREATE TABLE " + TABLE_BKM_PEGAWAI + "("
            + KEY_BKM_PEG_NO_BKM + " CHAR(10), "
            + KEY_BKM_PEG_NO_AKTIVITAS + " INTEGER(2), " + KEY_BKM_PEG_NO_PEGAWAI
            + " INTEGER(2) PRIMARY KEY, " + KEY_BKM_PEG_ID_PEGAWAI + " CHAR(10), "
            + KEY_BKM_PEG_HASIL_KERJA_RIIL + " DECIMAL(3,2)" + ")";

    //MATERIAL create statement
    private static final String CREATE_TABLE_MATERIAL = "CREATE TABLE " + TABLE_MATERIAL + "("
            + KEY_MATERIAL_KODE_MATERIAL + " CHAR(10) PRIMARY KEY, "
            + KEY_MATERIAL_NAMA_MATERIAL + " TEXT, " + KEY_MATERIAL_UNIT + " CHAR(2)" + ")";

    //PEGAWAI create statement
    private static final String CREATE_TABLE_PEGAWAI = "CREATE TABLE " + TABLE_PEGAWAI + "("
            + KEY_PEGAWAI_ID_PEGAWAI + " CHAR(10) PRIMARY KEY, "
            + KEY_PEGAWAI_NAMA_PEGAWAI + " TEXT, " + KEY_PEGAWAI_PANGGILAN_PEGAWAI + " TEXT, "
            + KEY_PEGAWAI_JABATAN + " TEXT, " + KEY_PEGAWAI_STATUS + " TEXT, "
            + KEY_PEGAWAI_KODE_MANDORAN + " CHAR(10)" + ")";

    //RKH create statement
    private static final String CREATE_TABLE_RKH = "CREATE TABLE " + TABLE_RKH + "("
            + KEY_RKH_NO_RKH + " CHAR(10) PRIMARY KEY, " + KEY_RKH_TGL_KEGIATAN + " DATE, "
            + KEY_RKH_ID_PEGAWAI + " CHAR(10)" + ")";

    //RKH_AKTIVITAS create statement
    private static final String CREATE_TABLE_RKH_AKTIVITAS = "CREATE TABLE " + TABLE_RKH_AKTIVITAS
            + "(" + KEY_RKH_AKT_NO_RKH + " CHAR(10), "
            + KEY_RKH_AKT_NO_AKTIVITAS + " INTEGER(2) PRIMARY KEY, " + KEY_RKH_AKT_KODE_AKTIVITAS
            + " CHAR(10), " + KEY_RKH_AKT_SEKTOR_TANAM + " VARCHAR(3), " + KEY_RKH_AKT_BLOK_TANAM
            + " VARCHAR(3)" + ")";

    //RKH_MATERIAL create statement
    private static final String CREATE_TABLE_RKH_MATERIAL = "CREATE TABLE " + TABLE_RKH_MATERIAL
            + "(" + KEY_RKH_MAT_NO_RKH + " CHAR(10), "
            + KEY_RKH_MAT_NO_AKTIVITAS + " INTEGER(2), " + KEY_RKH_MAT_KODE_MATERIAL
            + " CHAR(10) PRIMARY KEY" + ")";

    //RKH_PEGAWAI create statement
    private static final String CREATE_TABLE_RKH_PEGAWAI = "CREATE TABLE " + TABLE_RKH_PEGAWAI + "("
            + KEY_RKH_PEG_NO_RKH + " CHAR(10), "
            + KEY_RKH_PEG_NO_AKTIVITAS + " INTEGER(2), " + KEY_RKH_PEG_ID_PEGAWAI
            + " CHAR(10) PRIMARY KEY, " + KEY_RKH_PEG_HASIL_KERJA_STD + " DECIMAL(3,2)" + ")";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BKM);
        db.execSQL(CREATE_TABLE_BKM_AKTIVITAS);
        db.execSQL(CREATE_TABLE_BKM_MATERIAL);
        db.execSQL(CREATE_TABLE_BKM_PEGAWAI);
        db.execSQL(CREATE_TABLE_RKH);
        db.execSQL(CREATE_TABLE_RKH_AKTIVITAS);
        db.execSQL(CREATE_TABLE_RKH_MATERIAL);
        db.execSQL(CREATE_TABLE_RKH_PEGAWAI);
        db.execSQL(CREATE_TABLE_AKTIVITAS);
        db.execSQL(CREATE_TABLE_MATERIAL);
        db.execSQL(CREATE_TABLE_PEGAWAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BKM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BKM_AKTIVITAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BKM_MATERIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BKM_PEGAWAI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RKH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AKTIVITAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEGAWAI);

        //create new tables
        onCreate(db);
    }

    /**
     * Storing TABLE details in SQLite DB
     */

    public void addUser(String id_pegawai, String username, String api_key,
                        String created_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID_PEGAWAI, id_pegawai); //id_pegawai
        values.put(KEY_USER_USERNAME, username); //username
        values.put(KEY_USER_API_KEY, api_key); //api_key
        values.put(KEY_USER_CREATED_AT, created_at); //created_at

        //Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "Data User baru telah dimasukkan ke dalam row: " + id);
    }

    public void addAktivitas(String kode_aktivitas, String nama_aktivitas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AKTIVITAS_KODE_AKTIVITAS, kode_aktivitas); //kode_aktivitas
        values.put(KEY_AKTIVITAS_NAMA_AKTIVITAS, nama_aktivitas); //nama_aktivitas

        //Inserting Row
        long id = db.insert(TABLE_AKTIVITAS, null, values);
        db.close();

        Log.d(TAG, "Data Aktivitas telah dimasukkan ke dalam row: " + id);
    }

    public void addMaterial(String kode_material, String nama_material, String unit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MATERIAL_KODE_MATERIAL, kode_material); //kode_material
        values.put(KEY_MATERIAL_NAMA_MATERIAL, nama_material); //nama_material
        values.put(KEY_MATERIAL_UNIT, unit);

        //Inserting Row
        long id = db.insert(TABLE_MATERIAL, null, values);
        db.close();

        Log.d(TAG, "Data Material telah dimasukkan ke dalam row: " + id);
    }

    public void addPegawai(String id_pegawai, String nama_pegawai, String panggilan_pegawai,
                           String jabatan, String status, String kode_mandoran){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PEGAWAI_ID_PEGAWAI, id_pegawai); //id_pegawai
        values.put(KEY_PEGAWAI_NAMA_PEGAWAI, nama_pegawai); //nama_pegawai
        values.put(KEY_PEGAWAI_PANGGILAN_PEGAWAI, panggilan_pegawai); //panggilan_pegawai
        values.put(KEY_PEGAWAI_JABATAN, jabatan); //jabatan
        values.put(KEY_PEGAWAI_STATUS, status); //status
        values.put(KEY_PEGAWAI_KODE_MANDORAN, kode_mandoran); //kode_mandoran

        //Inserting Row
        long id = db.insert(TABLE_PEGAWAI, null, values);
        db.close();

        Log.d(TAG, "Data Pegawai telah dimasukkan ke dalam row: " + id);
    }

    public void addRKH(String no_rkh, String tgl_kegiatan, String id_pegawai){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RKH_NO_RKH, no_rkh); //no_rkh
        values.put(KEY_RKH_TGL_KEGIATAN, tgl_kegiatan); //tgl_kegiatan
        values.put(KEY_RKH_ID_PEGAWAI, id_pegawai); //id_pegawai

        //Inserting Row
        long id = db.insert(TABLE_RKH, null, values);
        db.close();

        Log.d(TAG, "Data RKH telah dimasukkan ke dalam row: " + id);
    }

    public void addRKHAktivitas(String no_rkh, String no_aktivitas, String kode_aktivitas,
        String sektor_tanam, String blok_tanam){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RKH_AKT_NO_RKH, no_rkh); //no_rkh
        values.put(KEY_RKH_AKT_NO_AKTIVITAS, no_aktivitas); //no_aktivitas
        values.put(KEY_RKH_AKT_KODE_AKTIVITAS, kode_aktivitas); //kode_aktivitas
        values.put(KEY_RKH_AKT_SEKTOR_TANAM, sektor_tanam); //sektor_tanam
        values.put(KEY_RKH_AKT_BLOK_TANAM, blok_tanam); //blok_tanam

        //Inserting Row
        long id = db.insert(TABLE_RKH_AKTIVITAS, null, values);
        db.close();

        Log.d(TAG, "Data RKH Aktivitas telah dimasukkan ke dalam row: " + id);
    }

    public void addRKHMaterial(String no_rkh, String no_aktivitas, String kode_material){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RKH_MAT_NO_RKH, no_rkh); //id_pegawai
        values.put(KEY_RKH_MAT_NO_AKTIVITAS, no_aktivitas); //nama_pegawai
        values.put(KEY_RKH_MAT_KODE_MATERIAL, kode_material); //panggilan_pegawai

        //Inserting Row
        long id = db.insert(TABLE_RKH_MATERIAL, null, values);
        db.close();

        Log.d(TAG, "Data RKH Material telah dimasukkan ke dalam row: " + id);
    }

    public void addRKHPegawai(String no_rkh, String no_aktivitas, String id_pegawai,
                              String hasil_kerja_standar){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RKH_PEG_NO_RKH, no_rkh); //id_pegawai
        values.put(KEY_RKH_PEG_NO_AKTIVITAS, no_aktivitas); //nama_pegawai
        values.put(KEY_RKH_PEG_ID_PEGAWAI, id_pegawai); //panggilan_pegawai
        values.put(KEY_RKH_PEG_HASIL_KERJA_STD, hasil_kerja_standar); //jabatan

        //Inserting Row
        long id = db.insert(TABLE_RKH_PEGAWAI, null, values);
        db.close();

        Log.d(TAG, "Data RKH Pegawai telah dimasukkan ke dalam row: " + id);
    }

    /**
     * Re-create database : Delete all tables and create them again
     * */

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

    public void deleteAktivitas() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_AKTIVITAS, null, null);
        db.close();

        Log.d(TAG, "Deleted all aktivitas from sqlite");
    }

    public void deleteMaterial() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_MATERIAL, null, null);
        db.close();

        Log.d(TAG, "Deleted all Material from sqlite");
    }

    public void deletePegawai() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_PEGAWAI, null, null);
        db.close();

        Log.d(TAG, "Deleted all Pegawai from sqlite");
    }

    public void deleteRKH() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_RKH, null, null);
        db.close();

        Log.d(TAG, "Deleted all RKH from sqlite");
    }

    public void deleteRKHAktivitas() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_RKH_AKTIVITAS, null, null);
        db.close();

        Log.d(TAG, "Deleted all RKH Aktivitas from sqlite");
    }

    public void deleteRKHMaterial() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_RKH_MATERIAL, null, null);
        db.close();

        Log.d(TAG, "Deleted all RKH Material from sqlite");
    }

    public void deleteRKHPegawai() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_RKH_PEGAWAI, null, null);
        db.close();

        Log.d(TAG, "Deleted all RKH Pegawai from sqlite");
    }

    /**
     * Getting all labels
     * returns list of labels
     * */

    public List<String> getAktivitas(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_AKTIVITAS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String kode_aktivitas = cursor.getString(0);
                String nama_aktivitas = cursor.getString(1);
                String aktivitas = kode_aktivitas + " - " + nama_aktivitas;

                labels.add(aktivitas);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public List<String> getSektorTanam(String kode_aktivitas){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT " + KEY_RKH_AKT_SEKTOR_TANAM + " FROM " + TABLE_RKH_AKTIVITAS
                + " WHERE " + KEY_RKH_AKT_KODE_AKTIVITAS + " = '" + kode_aktivitas + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public List<String> getBlokTanam(String kode_aktivitas){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT " + KEY_RKH_AKT_BLOK_TANAM + " FROM " + TABLE_RKH_AKTIVITAS
                                + " WHERE " + KEY_RKH_AKT_KODE_AKTIVITAS + " = '" + kode_aktivitas
                                + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public List<Material> getMaterial(){
        List<Material> allmaterial = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MATERIAL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Material material = new Material(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                allmaterial.add(material);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return allmaterial;
    }

    public List<String> getPegawai(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PEGAWAI;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String id_pegawai = cursor.getString(0);
                String nama_pegawai = cursor.getString(1);
                String panggilan_pegawai = cursor.getString(2);

                String pegawai = id_pegawai + " - " + nama_pegawai + " (" + panggilan_pegawai + ")";
                labels.add(pegawai);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public String getHasilSTD(String id_pegawai){

        // Select All Query
        String selectQuery = "SELECT " + KEY_RKH_PEG_HASIL_KERJA_STD +" FROM " + TABLE_RKH_PEGAWAI
                                + " WHERE " + KEY_RKH_ID_PEGAWAI + " = " + id_pegawai;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        cursor.moveToFirst();
        String label = cursor.getString(0);

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return label;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
