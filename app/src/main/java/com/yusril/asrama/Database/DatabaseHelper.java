package com.yusril.asrama.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static  final String DATABASE_NAME="asrama.db";

    private static final String SQL_CRATE_KRITERIA_TABLE=String.format(
            "CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL) "
            ,DatabaseContract.kriteria.TABLE_NAME_KRITERIA
            ,DatabaseContract.kriteria.ID_KRITERIA
            ,DatabaseContract.kriteria.KOLOM_KRITERIA_ABSEN_RUTIN
            ,DatabaseContract.kriteria.KOLOM_KRITERIA_ABSEN_NON_RUTIN
            ,DatabaseContract.kriteria.KOLOM_KRITERIA_PELANGGARAN
            ,DatabaseContract.kriteria.KOLOM_KRITERIA_CATATAN

    );
    private static final String SQL_CRATE_NORMALISASI_TABLE=String.format(
            "CREATE TABLE %s"
                    +"(%s TEXT NOT NULL,"
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL) "
            ,DatabaseContract.tabel_normalisasi.TABLE_NAME_NORMALISASI
            ,DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_NAMA
            ,DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_ABSEN_RUTIN
            ,DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_ABSEN_NON_RUTIN
            ,DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_PELANGGARAN
            ,DatabaseContract.tabel_normalisasi.KOLOM_NORMALISASI_CATATAN

    );
    private static final String SQL_CRATE_MAHASISWA_TABLE=String.format(
            "CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL, "
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL, "
                    +"%s REAL NOT NULL) "
            ,DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA
            ,DatabaseContract.mahasiswa.ID_MAHASISWA
            ,DatabaseContract.mahasiswa.KOLOM_MHS_NIM
            ,DatabaseContract.mahasiswa.KOLOM_MHS_NAMA
            ,DatabaseContract.mahasiswa.KOLOM_MHS_ABSEN_RUTIN
            ,DatabaseContract.mahasiswa.KOLOM_MHS_ABSEN_NON_RUTIN
            ,DatabaseContract.mahasiswa.KOLOM_MHS_PELANGGARAN
            ,DatabaseContract.mahasiswa.KOLOM_MHS_CATATAN

    );
    private static final String SQL_CRATE_VALUE_TABLE=String.format(
            "CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY,"
                    +"%s TEXT NOT NULL, "
                    +"%s REAL NOT NULL) "
            ,DatabaseContract.tabel_value.TABLE_NAME_VALUE
            ,DatabaseContract.tabel_value.ID_VALUE
            ,DatabaseContract.tabel_value.KOLOM_VALUE_NAMA
            ,DatabaseContract.tabel_value.KOLOM_VALUE

    );
    private  String SQL_DELETE_KRITERIA_TABLE=String.format("DROP TABLE IF EXIST"+ DatabaseContract.kriteria.TABLE_NAME_KRITERIA);
    private  String SQL_DELETE_MAHASISWA_TABLE=String.format("DROP TABLE IF EXIST"+ DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA)  ;
    private  String SQL_DELETE_NAME_NORMALISASI=String.format("DROP TABLE IF EXIST"+ DatabaseContract.tabel_normalisasi.TABLE_NAME_NORMALISASI)  ;
    private  String SQL_DELETE_NAME_value=String.format("DROP TABLE IF EXIST"+ DatabaseContract.tabel_value.TABLE_NAME_VALUE)  ;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CRATE_KRITERIA_TABLE);
        sqLiteDatabase.execSQL(SQL_CRATE_MAHASISWA_TABLE);
        sqLiteDatabase.execSQL(SQL_CRATE_NORMALISASI_TABLE);
        sqLiteDatabase.execSQL(SQL_CRATE_VALUE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_KRITERIA_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_MAHASISWA_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_NAME_NORMALISASI);
        sqLiteDatabase.execSQL(SQL_DELETE_NAME_value);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
