package com.yusril.asrama.activity.hal_tambah_mhs;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelMahasiswa implements Parcelable {
    private int id;
    private String nim;
    private String nama;
    private Double absenRutin,absenNonRutin,pelanggaran,catatan;
    public ModelMahasiswa(int id, String nim, String nama, Double absenRutin, Double absenNonRutin, Double pelanggaran, Double catatan) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.absenRutin = absenRutin;
        this.absenNonRutin = absenNonRutin;
        this.pelanggaran = pelanggaran;
        this.catatan = catatan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getAbsenRutin() {
        return absenRutin;
    }

    public void setAbsenRutin(Double absenRutin) {
        this.absenRutin = absenRutin;
    }

    public Double getAbsenNonRutin() {
        return absenNonRutin;
    }

    public void setAbsenNonRutin(Double absenNonRutin) {
        this.absenNonRutin = absenNonRutin;
    }

    public Double getPelanggaran() {
        return pelanggaran;
    }

    public void setPelanggaran(Double pelanggaran) {
        this.pelanggaran = pelanggaran;
    }

    public Double getCatatan() {
        return catatan;
    }

    public void setCatatan(Double catatan) {
        this.catatan = catatan;
    }


    public ModelMahasiswa() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nim);
        dest.writeString(this.nama);
        dest.writeValue(this.absenRutin);
        dest.writeValue(this.absenNonRutin);
        dest.writeValue(this.pelanggaran);
        dest.writeValue(this.catatan);
    }

    protected ModelMahasiswa(Parcel in) {
        this.id = in.readInt();
        this.nim = in.readString();
        this.nama = in.readString();
        this.absenRutin = (Double) in.readValue(Double.class.getClassLoader());
        this.absenNonRutin = (Double) in.readValue(Double.class.getClassLoader());
        this.pelanggaran = (Double) in.readValue(Double.class.getClassLoader());
        this.catatan = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<ModelMahasiswa> CREATOR = new Parcelable.Creator<ModelMahasiswa>() {
        @Override
        public ModelMahasiswa createFromParcel(Parcel source) {
            return new ModelMahasiswa(source);
        }

        @Override
        public ModelMahasiswa[] newArray(int size) {
            return new ModelMahasiswa[size];
        }
    };
}
