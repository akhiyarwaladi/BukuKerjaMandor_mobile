package com.example.dedra.bukukerjamandor.app;

/**
 * Created by Dedra on 2/11/2018.
 */

public class EndPoint {

    public static final String BASE_URL = "http://192.168.0.13:80/BukuKerjaMandor_server/v1";
    public static final String URL_AKTIVITAS = BASE_URL + "/getAktivitas";
    public static final String URL_MATERIAL = BASE_URL + "/getMaterial";
    public static final String URL_PEGAWAI = BASE_URL + "/getPegawai";
    public static final String URL_RKH_AKTIVITAS = BASE_URL + "/getRKHAktivitas";
    public static final String URL_LOGIN = BASE_URL + "/login";
    public static final String URL_REGISTER = BASE_URL + "/register";
    public static final String URL_PREDICTION = "http://192.168.43.98:33/predict";
}