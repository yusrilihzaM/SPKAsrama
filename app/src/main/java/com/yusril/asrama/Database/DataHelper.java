package com.yusril.asrama.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.yusril.asrama.activity.hal_tambah_mhs.ModelMahasiswa;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.yusril.asrama.Database.DatabaseContract.kriteria.ID_KRITERIA;
import static com.yusril.asrama.Database.DatabaseContract.kriteria.TABLE_NAME_KRITERIA;
import static com.yusril.asrama.Database.DatabaseContract.kriteria.KOLOM_KRITERIA_ABSEN_RUTIN;
import static com.yusril.asrama.Database.DatabaseContract.kriteria.KOLOM_KRITERIA_ABSEN_NON_RUTIN;
import static com.yusril.asrama.Database.DatabaseContract.kriteria.KOLOM_KRITERIA_PELANGGARAN;
import static com.yusril.asrama.Database.DatabaseContract.kriteria.KOLOM_KRITERIA_CATATAN;

import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.ID_MAHASISWA;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.KOLOM_MHS_NIM;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.KOLOM_MHS_NAMA;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.KOLOM_MHS_ABSEN_RUTIN;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.KOLOM_MHS_ABSEN_NON_RUTIN;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.KOLOM_MHS_PELANGGARAN;
import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.KOLOM_MHS_CATATAN;
import static com.yusril.asrama.Database.DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_ABSEN_NON_RUTIN;
import static com.yusril.asrama.Database.DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_ABSEN_RUTIN;
import static com.yusril.asrama.Database.DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_CATATAN;
import static com.yusril.asrama.Database.DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_NAMA;
import static com.yusril.asrama.Database.DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_PELANGGARAN;
import static com.yusril.asrama.Database.DatabaseContract.tabel_normalisasi.TABLE_NAME_NORMALISASI;
import static com.yusril.asrama.Database.DatabaseContract.tabel_value.KOLOM_VALUE;
import static com.yusril.asrama.Database.DatabaseContract.tabel_value.KOLOM_VALUE_NAMA;
import static com.yusril.asrama.Database.DatabaseContract.tabel_value.TABLE_NAME_VALUE;

public class DataHelper {
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DatabaseContract databaseContract;
    private static final String DATABASE_TABLE_KRITERIA=TABLE_NAME_KRITERIA;
    private static final String DATABASE_TABLE_MAHASISWA=TABLE_NAME_MAHASISWA;

    public DataHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public void open() throws SQLException {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }
    public long insertMhs(String nim, String nama, double absen_rutin, double absen_non_rutin, double pelanggaran, double catatan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLOM_MHS_NIM,nim);
        contentValues.put(KOLOM_MHS_NAMA,nama);
        contentValues.put(KOLOM_MHS_ABSEN_RUTIN,absen_rutin);
        contentValues.put(KOLOM_MHS_ABSEN_NON_RUTIN,absen_non_rutin);
        contentValues.put(KOLOM_MHS_PELANGGARAN,pelanggaran);
        contentValues.put(KOLOM_MHS_CATATAN,catatan);
        return sqLiteDatabase.insert(DATABASE_TABLE_MAHASISWA, null, contentValues);
    }

    public long insertNormalisasi(String nama,double absen_rutin, double absen_non_rutin, double pelanggaran, double catatan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLOM_NORMALISASI_NAMA,nama);
        contentValues.put(KOLOM_NORMALISASI_ABSEN_RUTIN,absen_rutin);
        contentValues.put(KOLOM_NORMALISASI_ABSEN_NON_RUTIN,absen_non_rutin);
        contentValues.put(KOLOM_NORMALISASI_PELANGGARAN,pelanggaran);
        contentValues.put(KOLOM_NORMALISASI_CATATAN,catatan);
        return sqLiteDatabase.insert(DATABASE_TABLE_KRITERIA, null, contentValues);
    }
    public long insertKriteria(double absen_rutin, double absen_non_rutin, double pelanggaran, double catatan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLOM_KRITERIA_ABSEN_RUTIN,absen_rutin);
        contentValues.put(KOLOM_KRITERIA_ABSEN_NON_RUTIN,absen_non_rutin);
        contentValues.put(KOLOM_KRITERIA_PELANGGARAN,pelanggaran);
        contentValues.put(KOLOM_KRITERIA_CATATAN,catatan);
        return sqLiteDatabase.insert(DATABASE_TABLE_KRITERIA, null, contentValues);
    }
    public long insertValue(String nama, double value){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLOM_VALUE_NAMA,nama);
        contentValues.put(KOLOM_VALUE,value);
        return sqLiteDatabase.insert(TABLE_NAME_VALUE, null, contentValues);
    }
    public long updateKriteria(int id, double absen_rutin, double absen_non_rutin, double pelanggaran, double catatan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_KRITERIA,id);
        contentValues.put(KOLOM_KRITERIA_ABSEN_RUTIN,absen_rutin);
        contentValues.put(KOLOM_KRITERIA_ABSEN_NON_RUTIN,absen_non_rutin);
        contentValues.put(KOLOM_KRITERIA_PELANGGARAN,pelanggaran);
        contentValues.put(KOLOM_KRITERIA_CATATAN,catatan);
        return sqLiteDatabase.update(DATABASE_TABLE_KRITERIA, contentValues,ID_KRITERIA+"=="+ id,null);
    }
    public long updateMhs(int id,String nim, String nama, double absen_rutin, double absen_non_rutin, double pelanggaran, double catatan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_MAHASISWA,id);
        contentValues.put(KOLOM_MHS_NIM,nim);
        contentValues.put(KOLOM_MHS_NAMA,nama);
        contentValues.put(KOLOM_MHS_ABSEN_RUTIN,absen_rutin);
        contentValues.put(KOLOM_MHS_ABSEN_NON_RUTIN,absen_non_rutin);
        contentValues.put(KOLOM_MHS_PELANGGARAN,pelanggaran);
        contentValues.put(KOLOM_MHS_CATATAN,catatan);
        return sqLiteDatabase.update(DATABASE_TABLE_MAHASISWA, contentValues,ID_MAHASISWA+"=="+id,null);
    }
    public int deleteMhs(String id) {
        return sqLiteDatabase.delete(TABLE_NAME_MAHASISWA, ID_MAHASISWA+"=="+id, null);
    }
    public int deleteKritera(int id) {
        return sqLiteDatabase.delete(TABLE_NAME_KRITERIA, ID_KRITERIA + " =='" + id, null);
    }
    public int deleteNormalisasi() {
        return sqLiteDatabase.delete(TABLE_NAME_NORMALISASI, null, null);
    }
    public int deleteValue() {
        return sqLiteDatabase.delete(TABLE_NAME_VALUE, null, null);
    }
    public Cursor getDataKirteria(){
        String query=String.format("SELECT * FROM "+ DatabaseContract.kriteria.TABLE_NAME_KRITERIA);
        Cursor res = sqLiteDatabase.rawQuery(query,null);
        return res;
    }
    public Cursor getDatamahasiswa(){
        String query=String.format("SELECT * FROM "+ TABLE_NAME_MAHASISWA);
        Cursor res = sqLiteDatabase.rawQuery(query,null);
        return res;
    }
    public ArrayList<ModelMahasiswa> getAllMhs() {
        ArrayList<ModelMahasiswa> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_MAHASISWA, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ModelMahasiswa mhs;
        if (cursor.getCount() > 0) {
            do {
                mhs = new ModelMahasiswa();
                mhs.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_MAHASISWA)));
                mhs.setNim(cursor.getString(cursor.getColumnIndexOrThrow(KOLOM_MHS_NIM)));
                mhs.setNama(cursor.getString(cursor.getColumnIndexOrThrow(KOLOM_MHS_NAMA)));
                mhs.setAbsenRutin(cursor.getDouble(cursor.getColumnIndexOrThrow(KOLOM_MHS_ABSEN_RUTIN)));
                mhs.setAbsenNonRutin(cursor.getDouble(cursor.getColumnIndexOrThrow(KOLOM_MHS_ABSEN_NON_RUTIN)));
                mhs.setPelanggaran(cursor.getDouble(cursor.getColumnIndexOrThrow(KOLOM_MHS_PELANGGARAN)));
                mhs.setCatatan(cursor.getDouble(cursor.getColumnIndexOrThrow(KOLOM_MHS_CATATAN)));
                int id= cursor.getInt(0);
                String nim= cursor.getString(1);
                String nama= cursor.getString(2);
                Double absenRutin=cursor.getDouble(3);
                Double absenNonRutin=cursor.getDouble(4);
                Double pelanggaran=cursor.getDouble(5);
                Double catatan= cursor.getDouble(6);
                mhs = new ModelMahasiswa(id,nim,nama,absenRutin,absenNonRutin,pelanggaran,catatan);
                arrayList.add(mhs);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

}
