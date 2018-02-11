package com.example.dedra.bukukerjamandor.model;

/**
 * Created by Dedra on 10/10/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AktivitasModel implements Parcelable{

    @SerializedName("id_aktivitas")
    private String idAktivitas;
    @SerializedName("nama_aktivitas")
    private String namaAktivitas;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public AktivitasModel(String idAktivitas, String namaAktivitas, String createdAt, String updatedAt) {
        this.idAktivitas = idAktivitas;
        this.namaAktivitas = namaAktivitas;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getIdAktivitas() {
        return idAktivitas;
    }

    public void setIdAktivitas(String idAktivitas) {
        this.idAktivitas = idAktivitas;
    }

    public String getNamaAktivitas() { return namaAktivitas;}

    public void setNamaAktivitas (String namaAktivitas) { this.namaAktivitas = namaAktivitas;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idAktivitas);
        dest.writeString(this.namaAktivitas);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    protected AktivitasModel(Parcel in) {
        this.idAktivitas = in.readString();
        this.namaAktivitas = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<AktivitasModel> CREATOR = new Creator<AktivitasModel>() {
        @Override
        public AktivitasModel createFromParcel(Parcel source) {
            return new AktivitasModel(source);
        }

        @Override
        public AktivitasModel[] newArray(int size) {
            return new AktivitasModel[size];
        }
    };
}

