package com.yusril.asrama.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yusril.asrama.Database.DataHelper;
import com.yusril.asrama.Database.DatabaseContract;
import com.yusril.asrama.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;

import static com.yusril.asrama.Database.DatabaseContract.kriteria.TABLE_NAME_KRITERIA;

import static com.yusril.asrama.Database.DatabaseContract.tabel_value.KOLOM_VALUE;
import static com.yusril.asrama.Database.DatabaseContract.tabel_value.TABLE_NAME_VALUE;

public class ModelSPK {
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DataHelper dataHelper;
    private Cursor cursor;

    //Data
    public ArrayList<Integer> id=new ArrayList<>();
    public ArrayList<String> nim=new ArrayList<>();
    public  ArrayList<String> nama=new ArrayList<>();
    public ArrayList<Double> absenrutinne =new ArrayList<>();
    public ArrayList<Double> absennonrutin=new ArrayList<>();
    public ArrayList<Double> pelanggaran=new ArrayList<>();
    public ArrayList<Double> catatan=new ArrayList<>();
    public ArrayList<Double> Normalisasiabsenrutinne =new ArrayList<>();
    public ArrayList<Double> Normalisasiabsennonrutin=new ArrayList<>();
    public ArrayList<Double> Normalisasipelanggaran=new ArrayList<>();
    public ArrayList<Double> Normalisasicatatan=new ArrayList<>();
    public ArrayList<Double> Pembobotanabsenrutinne =new ArrayList<>();
    public ArrayList<Double> Pembobotanabsennonrutin=new ArrayList<>();
    public ArrayList<Double> Pembobotanpelanggaran=new ArrayList<>();
    public ArrayList<Double> Pembobotancatatan=new ArrayList<>();
    public ArrayList<Double> JarakPositif=new ArrayList<>();
    public ArrayList<Double> JarakNegatf=new ArrayList<>();
    public ArrayList<Double> Value=new ArrayList<>();
    public ArrayList<Integer> id_value=new ArrayList<>();
    public  ArrayList<String> nama_value=new ArrayList<>();
    public ArrayList<Double> nilai_value=new ArrayList<>();
    public  ArrayList<String> klasifikasi=new ArrayList<>();
    public  ArrayList<String> klasifikasiWarna=new ArrayList<>();
    public Double xrutin=0.0;
    public Double xnonrutin=0.0;
    public Double xpel=0.0;
    public Double xcat=0.0;

    public Double krutin=0.0;
    public Double knonrutin=0.0;
    public Double kpel=0.0;
    public Double kcat=0.0;
    public Double Maxrutin=0.0;
    public Double Maxnonrutin=0.0;
    public Double Maxpel=0.0;
    public Double Maxcat=0.0;
    public Double Minrutin=0.0;
    public Double Minnonrutin=0.0;
    public Double Minpel=0.0;
    public Double Mincat=0.0;
    public String Sklasifikasi, warna;
    //Nilai X
    public double Xabsenrutin, Xabsennonrutin, Xpelangaran, Xcatatan;
    public Cursor cekdata(Context  context){
        databaseHelper=new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA);
        cursor=sqLiteDatabase.rawQuery(query,null);

        return cursor;
    }
    public void addList(Context  context){
        databaseHelper=new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA);
        cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            id.add(cursor.getInt(0));
            nim.add(cursor.getString(1));
            nama.add(cursor.getString(2));
            absenrutinne.add(cursor.getDouble(3));
            absennonrutin.add(cursor.getDouble(4));
            pelanggaran.add(cursor.getDouble(5));
            catatan.add(cursor.getDouble(6));
        }
    }
    public void clearData(){
        id.clear();
        nim.clear();
        nama.clear();
        absenrutinne.clear();
        absennonrutin.clear();
        pelanggaran.clear();
        catatan.clear();
    }
    public void DataX(){
        for (int i=0;i<id.size();i++){
            xrutin=xrutin+Math.pow(absenrutinne.get(i),2);
            xnonrutin=xnonrutin+Math.pow(absennonrutin.get(i),2);
            xpel=xpel+Math.pow(pelanggaran.get(i),2);
            xcat=xcat+Math.pow(catatan.get(i),2);
        }
        Xabsenrutin=Math.sqrt(xrutin);
        Xabsennonrutin=Math.sqrt(xnonrutin);
        Xpelangaran=Math.sqrt(xpel);
        Xcatatan=Math.sqrt(xcat);
    }
    public void addNormalisasi(){
        for (int i=0;i<id.size();i++){
            Normalisasiabsenrutinne.add(absenrutinne.get(i)/Xabsenrutin);
            Normalisasiabsennonrutin.add(absennonrutin.get(i)/Xabsennonrutin);
            Normalisasipelanggaran.add(pelanggaran.get(i)/Xpelangaran);
            Normalisasicatatan.add(catatan.get(i)/Xcatatan);
        }
    }
    public void addkriteria(Context  context){
        databaseHelper=new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ TABLE_NAME_KRITERIA);
        cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            krutin=cursor.getDouble(1);
            knonrutin=cursor.getDouble(2);
            kpel=cursor.getDouble(3);
            kcat=cursor.getDouble(4);
        }
    }
    public void clearPembobotan(){
        Pembobotanabsenrutinne.clear();
        Pembobotanabsennonrutin.clear();
        Pembobotanpelanggaran.clear();
        Pembobotancatatan.clear();
    }
    public void addPembobotan(){
        for (int i=0;i<id.size();i++){
            Pembobotanabsenrutinne.add(Normalisasiabsenrutinne.get(i)*krutin);
            Pembobotanabsennonrutin.add(Normalisasiabsennonrutin.get(i)*knonrutin);
            Pembobotanpelanggaran.add(Normalisasipelanggaran.get(i)*kpel);
            Pembobotancatatan.add(Normalisasicatatan.get(i)*kcat);
        }
    }
    public void  addNilaiIdeal(){
        Maxrutin=Collections.max(Pembobotanabsenrutinne);
        Maxnonrutin=Collections.max(Pembobotanabsennonrutin);
        Maxpel=Collections.max(Pembobotanpelanggaran);
        Maxcat=Collections.max(Pembobotancatatan);
        Minrutin=Collections.min(Pembobotanabsenrutinne);
        Minnonrutin=Collections.min(Pembobotanabsennonrutin);
        Minpel=Collections.min(Pembobotanpelanggaran);
        Mincat=Collections.min(Pembobotancatatan);
    }
    public void cleardatanormalisai(){

        Normalisasiabsennonrutin.clear();
        Normalisasipelanggaran.clear();
        Normalisasicatatan.clear();
        Pembobotanabsenrutinne .clear();
        Pembobotanabsennonrutin.clear();
        Pembobotanpelanggaran.clear();
        Pembobotancatatan.clear();

    }
    public void clearDatajarak(){
        JarakPositif.clear();
        JarakNegatf.clear();
    }
    public void addJNilaiJarak(){
        for (int i=0;i<id.size();i++){
            JarakPositif.add(Math.sqrt(Math.pow((Maxrutin-Pembobotanabsenrutinne.get(i)),2)
                    +Math.pow((Maxnonrutin-Pembobotanabsennonrutin.get(i)),2)
                    +Math.pow((Maxpel-Pembobotanpelanggaran.get(i)),2)
                    +Math.pow((Maxcat-Pembobotancatatan.get(i)),2)
            ));
            JarakNegatf.add(Math.sqrt(Math.pow((Minrutin-Pembobotanabsenrutinne.get(i)),2)
                    +Math.pow((Minnonrutin-Pembobotanabsennonrutin.get(i)),2)
                    +Math.pow((Minpel-Pembobotanpelanggaran.get(i)),2)
                    +Math.pow((Mincat-Pembobotancatatan.get(i)),2)
            ));
        }

    }
    public void clearValue(){
        Value.clear();
    }
    public void addValue(Context context){
        for (int i=0;i<id.size();i++){
            Value.add(JarakNegatf.get(i)/(JarakPositif.get(i)+JarakNegatf.get(i)));
        }
    }
    public void addValueList(Context  context){
        databaseHelper=new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ TABLE_NAME_VALUE+" ORDER BY "+KOLOM_VALUE+" DESC");
        cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            id_value.add(cursor.getInt(0));
            nama_value.add(cursor.getString(1));
            nilai_value.add(cursor.getDouble(2));

        }
    }

    public void addklasifikasi(){
       for (int i=0;i<id.size();i++){
            if(nilai_value.get(i)>0.75){
                Sklasifikasi="Hijau";
                warna="#06b11e";
                klasifikasi.add(Sklasifikasi);
                klasifikasiWarna.add(warna);
            }else if(nilai_value.get(i)>0.30){
                Sklasifikasi="Kuning";
                warna="#e5e74d";
                klasifikasi.add(Sklasifikasi);
                klasifikasiWarna.add(warna);
            }else{
                Sklasifikasi="Merah";
                warna="#ff0024";
                klasifikasi.add(Sklasifikasi);
                klasifikasiWarna.add(warna);
            }
        }
    }
    public void clearKlasifikasi(){
        klasifikasi.clear();
        klasifikasiWarna.clear();
    }
}
