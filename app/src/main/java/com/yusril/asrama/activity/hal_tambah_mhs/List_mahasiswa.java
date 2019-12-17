package com.yusril.asrama.activity.hal_tambah_mhs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yusril.asrama.Database.DataHelper;
import com.yusril.asrama.Database.DatabaseContract;
import com.yusril.asrama.Database.DatabaseHelper;
import com.yusril.asrama.R;
import com.yusril.asrama.activity.hal_kriteria.KriteriaActivity;

import java.util.ArrayList;
import java.util.List;

import static com.yusril.asrama.Database.DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA;

public class List_mahasiswa extends AppCompatActivity {
    FloatingActionButton add_mhs;
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DataHelper dataHelper;
    private Cursor cursor;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<ModelMahasiswa> mahasiswaList=new ArrayList<>();
    private int id_mhs;
     private String nim_mhs
            ,nama_mhs;
     private double absenrutin_mhs
            ,absennonrutin_mhs
            ,pelanggaran_mhs
            ,catatan_mhs;
//    private ArrayList id_mhs
//            ,nim_mhs
//            ,nama_mhs
//            ,absenrutin_mhs
//            ,absennonrutin_mhs
//            ,pelanggaran_mhs
//            ,catatan_mhs;
    private AdapterListMahasiswa adapter;
    private TextView textView;
    String nim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);
        getSupportActionBar().setTitle("Daftar Mahasiswa");
        databaseHelper=new DatabaseHelper(this);
        dataHelper=new DataHelper(List_mahasiswa.this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();

        add_mhs=findViewById(R.id.add_mhs);
        add_mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List_mahasiswa.this, TambahMahasiswa.class));
            }
        });
        getalldata();
        recyclerView = findViewById(R.id.rv_list_mhs);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new AdapterListMahasiswa(getApplicationContext());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
        adapter.setdata(mahasiswaList);
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        mahasiswaList.clear();
        getalldata();
        adapter.setdata(mahasiswaList);
        recyclerView.setAdapter(adapter);
    }



    private void getalldata(){
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ DatabaseContract.mahasiswa.TABLE_NAME_MAHASISWA);
        cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            id_mhs=cursor.getInt(0);
            nim_mhs=cursor.getString(1);
            nama_mhs=cursor.getString(2);
            absenrutin_mhs=cursor.getDouble(3);
            absennonrutin_mhs=cursor.getDouble(4);
            pelanggaran_mhs=cursor.getDouble(5);
            catatan_mhs=cursor.getDouble(6);
            ModelMahasiswa modelMahasiswa=new ModelMahasiswa(id_mhs,nim_mhs,nama_mhs,absenrutin_mhs,absennonrutin_mhs,pelanggaran_mhs,catatan_mhs);
            mahasiswaList.add(modelMahasiswa);
        }
    }
}
