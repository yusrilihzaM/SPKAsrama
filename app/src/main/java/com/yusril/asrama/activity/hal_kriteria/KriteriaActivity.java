package com.yusril.asrama.activity.hal_kriteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yusril.asrama.Database.DataHelper;
import com.yusril.asrama.Database.DatabaseContract;
import com.yusril.asrama.Database.DatabaseHelper;
import com.yusril.asrama.R;
import com.yusril.asrama.activity.dashboard.Dashboard;

public class KriteriaActivity extends AppCompatActivity {
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DataHelper dataHelper;
    private TextView textview;
    private EditText edtAbsenRutin,edtAbsenNonRutin, edtPelanggaran,edtCatatan;
    private Double absenRutin,absenNonRutin,pelanggaran,catatan;
    private Button btn_save;
    private Cursor cursor;
    int cek_data;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Tambah Kriteria");
        setContentView(R.layout.activity_kriteria);
        edtAbsenRutin=findViewById(R.id.edt_absen_rutin);
        edtAbsenNonRutin=findViewById(R.id.edt_absen_non_rutin);
        edtPelanggaran=findViewById(R.id.edt_pelanggaran);
        edtCatatan=findViewById(R.id.edt_catatan);
        btn_save=findViewById(R.id.btn_save);
        String txt_save;
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ DatabaseContract.kriteria.TABLE_NAME_KRITERIA);
        cursor=sqLiteDatabase.rawQuery(query,null);
        cek_data=cursor.getCount();
        if(cek_data==0){
            txt_save="Simpan";
            btn_save.setText(txt_save);
        }else{
            txt_save="Update";
            btn_save.setText(txt_save);
        }

//        displayDatabaseInfo();
        dataHelper=new DataHelper(KriteriaActivity.this);
        dataHelper.open();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absenRutin= Double.parseDouble(edtAbsenRutin.getText()+"");
                absenNonRutin=Double.parseDouble(edtAbsenNonRutin.getText()+"");
                pelanggaran=Double.parseDouble(edtPelanggaran.getText()+"");
                catatan=Double.parseDouble(edtCatatan.getText()+"");

                if(cek_data==0){
                    dataHelper.insertKriteria(absenRutin,absenNonRutin,pelanggaran,catatan);
//                    startActivity(new Intent(KriteriaActivity.this, Dashboard.class));
                    Toast.makeText(getApplicationContext(),"Kriteria Telah di Simpan",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    dataHelper.updateKriteria(id,absenRutin,absenNonRutin,pelanggaran,catatan);
//                    startActivity(new Intent(KriteriaActivity.this,Dashboard.class));
                    Toast.makeText(getApplicationContext(),"Kriteria Telah di Update",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        getalldata();

    }
    private void displayDatabaseInfo(){
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ DatabaseContract.kriteria.TABLE_NAME_KRITERIA);
        cursor=sqLiteDatabase.rawQuery(query,null);
        try {
            TextView textView= (TextView) findViewById      (R.id.textView);
            textView.setText("Jumlah row di kriteria databae table "+cursor.getCount());
        }finally {
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        displayDatabaseInfo();
    }
    private void getalldata(){
        dataHelper.open();
        dataHelper.getDataKirteria();
        Cursor res=dataHelper.getDataKirteria();
        while (res.moveToNext()){
            edtAbsenRutin.setText(res.getString(1));
            edtAbsenNonRutin.setText(res.getString(2));
            edtPelanggaran.setText(res.getString(3));
            edtCatatan.setText(res.getString(4));
            id=res.getInt(0);
        }
    }
}
