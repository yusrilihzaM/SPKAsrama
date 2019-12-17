package com.yusril.asrama.activity.hal_tambah_mhs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yusril.asrama.Database.DataHelper;
import com.yusril.asrama.Database.DatabaseContract;
import com.yusril.asrama.Database.DatabaseHelper;
import com.yusril.asrama.R;
import com.yusril.asrama.activity.hal_kriteria.KriteriaActivity;

public class TambahMahasiswa extends AppCompatActivity {
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DataHelper dataHelper;
    private Cursor cursor;
    private Double absenRutin,absenNonRutin,pelanggaran,catatan;
    private EditText
            edtnim
            ,edtnamamhs
            ,edtabsenrutinmhs
            ,edtabsenrutinonnmhs
            ,edtpelanggaranmhs
            ,edtcatatanmhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);
        getSupportActionBar().setTitle("Tambah Mahasiswa");
        databaseHelper=new DatabaseHelper(this);
        dataHelper=new DataHelper(TambahMahasiswa.this);

        edtnim=findViewById(R.id.edt_nin_mhs);
        edtnamamhs=findViewById(R.id.edt_nama_mhs);
        edtabsenrutinmhs=findViewById(R.id.edt_absen_rutin);
        edtabsenrutinonnmhs=findViewById(R.id.edt_absen_non_rutin);
        edtpelanggaranmhs=findViewById(R.id.edt_pelanggaran);
        edtcatatanmhs=findViewById(R.id.edt_catatan);
    }
    private void displayDatabaseInfo(){
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ DatabaseContract.tabel_value.TABLE_NAME_VALUE);
        cursor=sqLiteDatabase.rawQuery(query,null);
        try {
            TextView textView= (TextView) findViewById      (R.id.textView);
            textView.setText("Jumlah row di VALUE databae table "+cursor.getCount());
        }finally {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_mhs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                dataHelper.open();
                String nim=String.valueOf(edtnim.getText());
                String nama=String.valueOf(edtnamamhs.getText());
                absenRutin= Double.parseDouble(edtabsenrutinmhs.getText()+"");
                absenNonRutin=Double.parseDouble(edtabsenrutinonnmhs.getText()+"");
                pelanggaran=Double.parseDouble(edtpelanggaranmhs.getText()+"");
                catatan=Double.parseDouble(edtcatatanmhs.getText()+"");
                dataHelper.insertMhs(nim,nama,absenRutin,absenNonRutin,pelanggaran,catatan);
                Toast.makeText(getApplicationContext(),"Data Mahasiswa Telah di Simpan",Toast.LENGTH_SHORT).show();
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
