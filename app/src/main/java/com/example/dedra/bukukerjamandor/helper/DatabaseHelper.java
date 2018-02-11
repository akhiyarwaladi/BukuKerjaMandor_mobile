package com.example.dedra.bukukerjamandor.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dedra on 10/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    //private static final String LOG = "DatabaseHelper";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "bukukerjamandor.db";

    //Table Names
    private static final String TABLE_BKM = "bkm";
    private static final String TABLE_BKM_AKTIVITAS = "bkm_aktivitas";
    private static final String TABLE_BKM_MATERIAL = "bkm_material";
    private static final String TABLE_BKM_PEGAWAI = "bkm_pegawai";
    private static final String TABLE_RKH = "rkh";
    private static final String TABLE_AKTIVITAS = "aktivitas";
    private static final String TABLE_MATERIAL = "material";
    private static final String TABLE_PEGAWAI = "pegawai";

    //Table Create Statements

    //BKM_harian create statement
    private static final String CREATE_TABLE_BKM = "CREATE TABLE "
            + TABLE_BKM + "(NO_BKM CHAR(10) PRIMARY KEY, TGL_BKM DATE, NO_AKTIVITAS INTEGER, "
            + "KODE_MANDORAN CHAR(10))";

    //BKM_aktivitas create statement
    private static final String CREATE_TABLE_BKM_AKTIVITAS = "CREATE TABLE "
            + TABLE_BKM_AKTIVITAS + "(NO_BKM CHAR(10), NO_AKTIVITAS INTEGER PRIMARY KEY, "
            + "KODE_AKTIVITAS INTEGER, SEKTOR_TANAM VARCHAR(3), BLOK_TANAM VARCHAR(3), "
            + "NO_MATERIAL INTEGER, NO_PEGAWAI INTEGER, PATH_IMAGE TEXT)";

    //BKM_material create statement
    private static final String CREATE_TABLE_BKM_MATERIAL = "CREATE TABLE "
            + TABLE_BKM_MATERIAL + "(NO_BKM CHAR(10), NO_AKTIVITAS INTEGER, "
            + "NO_MATERIAL INTEGER PRIMARY KEY, KODE_MATERIAL CHAR, KUANTITAS INTEGER)";

    //BKM_pegawai create statement
    private static final String CREATE_TABLE_BKM_PEGAWAI = "CREATE TABLE "
            + TABLE_BKM_PEGAWAI + "(NO_BKM CHAR(10), NO_AKTIVITAS INTEGER, "
            + "NO_PEGAWAI INTEGER PRIMARY KEY, KODE_PEGAWAI CHAR, HASIL_KERJA INTEGER)";

    private static final String CREATE_TABLE_RKH = "CREATE TABLE "
            + TABLE_RKH + "(NO_RKH CHAR(10) PRIMARY KEY, TGL_KEGIATAN DATE, "
            + "SEKTOR_TANAM VARCHAR(3), BLOK_TANAM VARCHAR(3), KODE_MANDORAN CHAR(10), "
            + "KODE_AKTIVITAS CHAR(10))";

    private static final String CREATE_TABLE_AKTIVITAS = "CREATE TABLE "
            + TABLE_AKTIVITAS + "(KODE_AKTIVITAS INTEGER(10) PRIMARY KEY, "
            + "NAMA_AKTIVITAS VARCHAR(50), KODE_MATERIAL CHAR(10))";

    private static final String CREATE_TABLE_MATERIAL = "CREATE TABLE "
            + TABLE_MATERIAL + "(KODE_MATERIAL CHAR(10) PRIMARY KEY, NAMA_MATERIAL VARCHAR(50),"
            + "UNIT CHAR(2))";

    private static final String CREATE_TABLE_PEGAWAI = "CREATE TABLE "
            + TABLE_PEGAWAI + "(ID_PEGAWAI CHAR(10) PRIMARY KEY, NAMA_PEGAWAI VARCHAR(25), "
            + "PANGGILAN_PEGAWAI VARCHAR(10), JABATAN BOOLEAN, "
            + "STATUS BOOLEAN, USERNAME VARCHAR(10))";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_BKM);
        db.execSQL(CREATE_TABLE_BKM_AKTIVITAS);
        db.execSQL(CREATE_TABLE_BKM_MATERIAL);
        db.execSQL(CREATE_TABLE_BKM_PEGAWAI);
        db.execSQL(CREATE_TABLE_RKH);
        db.execSQL(CREATE_TABLE_AKTIVITAS);
        db.execSQL(CREATE_TABLE_MATERIAL);
        db.execSQL(CREATE_TABLE_PEGAWAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //on upgrade drop older tables
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

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
