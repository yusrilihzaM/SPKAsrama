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
import android.widget.Toast;

import com.yusril.asrama.Database.DataHelper;
import com.yusril.asrama.Database.DatabaseHelper;
import com.yusril.asrama.R;

public class DetailMahasiswaActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_data";
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DataHelper dataHelper;
    private Cursor cursor;
    private int id;
    private String nama,nim;
    private Double absenRutin,absenNonRutin,pelanggaran,catatan;
    private EditText
            edtnim
            ,edtnamamhs
            ,edtabsenrutinmhs
            ,edtabsenrutinonnmhs
            ,edtpelanggaranmhs
            ,edtcatatanmhs;
    private ModelMahasiswa modelMahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);
        getSupportActionBar().setTitle("Data Mahasiswa");
        databaseHelper=new DatabaseHelper(this);
        dataHelper=new DataHelper(DetailMahasiswaActivity.this);

        edtnim=findViewById(R.id.edt_nin_mhs);
        edtnamamhs=findViewById(R.id.edt_nama_mhs);
        edtabsenrutinmhs=findViewById(R.id.edt_absen_rutin);
        edtabsenrutinonnmhs=findViewById(R.id.edt_absen_non_rutin);
        edtpelanggaranmhs=findViewById(R.id.edt_pelanggaran);
        edtcatatanmhs=findViewById(R.id.edt_catatan);

        modelMahasiswa=getIntent().getParcelableExtra(EXTRA_DATA);

        id=modelMahasiswa.getId();
        nim=modelMahasiswa.getNim();
        nama=modelMahasiswa.getNama();
        absenRutin=modelMahasiswa.getAbsenRutin();
        absenNonRutin=modelMahasiswa.getAbsenNonRutin();
        pelanggaran=modelMahasiswa.getPelanggaran();
        catatan=modelMahasiswa.getCatatan();

        edtnim.setText(nim);
        edtnamamhs.setText(nama);
        edtabsenrutinmhs.setText(absenRutin+"");
        edtabsenrutinonnmhs.setText(absenNonRutin+"");
        edtpelanggaranmhs.setText(pelanggaran+"");
        edtcatatanmhs.setText(catatan+"");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_mhs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dataHelper.open();
        switch (item.getItemId()) {
            case R.id.action_save:
                String nim=String.valueOf(edtnim.getText());
                String nama=String.valueOf(edtnamamhs.getText());
                absenRutin= Double.parseDouble(edtabsenrutinmhs.getText()+"");
                absenNonRutin=Double.parseDouble(edtabsenrutinonnmhs.getText()+"");
                pelanggaran=Double.parseDouble(edtpelanggaranmhs.getText()+"");
                catatan=Double.parseDouble(edtcatatanmhs.getText()+"");
                dataHelper.updateMhs(id,nim,nama,absenRutin,absenNonRutin,pelanggaran,catatan);
                Toast.makeText(getApplicationContext(),"Data Mahasiswa Telah di Diupdate",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailMahasiswaActivity.this, List_mahasiswa.class));
//                finish();
                return true;
            case R.id.action_delete:
                Toast.makeText(getApplicationContext(),"Data Mahasiswa Telah di Hapus",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailMahasiswaActivity.this, List_mahasiswa.class));
                dataHelper.deleteMhs(id+"");
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
