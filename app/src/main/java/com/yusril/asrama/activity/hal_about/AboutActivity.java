package com.yusril.asrama.activity.hal_about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.yusril.asrama.R;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ModelProfil> list;
    private String[] dataNama;
    private String[] dataNim;
    private String[] dataPeran;
    private String[] dataEmail;
    private TypedArray dataPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
//        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.rv_about);
        prepare();
        addItem();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AdapterAbout adapterAbout=new AdapterAbout(this);
        adapterAbout.setListHero(list);
        recyclerView.setAdapter(adapterAbout);

    }
    private void prepare() {
        dataNama = getResources().getStringArray(R.array.data_nama);
        dataNim = getResources().getStringArray(R.array.data_nim);
        dataPeran = getResources().getStringArray(R.array.data_peran);
        dataEmail = getResources().getStringArray(R.array.data_email);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem() {
        list = new ArrayList<>();
        for (int i = 0; i < dataNama.length; i++) {
            ModelProfil item = new ModelProfil();
            item.setPhoto(dataPhoto.getResourceId(i, -1));
            item.setNama(dataNama[i]);
            item.setEmail(dataEmail[i]);
            item.setNim(dataNim[i]);
            item.setPeran(dataPeran[i]);
            list.add(item);
        }
    }
}
