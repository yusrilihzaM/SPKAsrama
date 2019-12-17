package com.yusril.asrama.activity.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yusril.asrama.R;
import com.yusril.asrama.activity.hal_hasil.HasilActivity;
import com.yusril.asrama.activity.hal_peraturan.PeraturanAsrama;
import com.yusril.asrama.activity.hal_tambah_mhs.List_mahasiswa;
import com.yusril.asrama.activity.hal_kriteria.KriteriaActivity;

public class Dashboard extends AppCompatActivity {
    CardView btn_add_kriteria,btn_add_mhs,btn_hasil,btn_peraturan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        btn_add_kriteria=findViewById(R.id.btn_add_kriteria);
        btn_add_mhs=findViewById(R.id.btn_add_mhs);
        btn_hasil=findViewById(R.id.hasil_perthitungan);
        btn_peraturan=findViewById(R.id.btn_peraturan);

        btn_add_kriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, KriteriaActivity.class));
            }
        });
        btn_add_mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, List_mahasiswa.class));
            }
        });
        btn_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, HasilActivity.class));
            }
        });
        btn_peraturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, PeraturanAsrama.class));
            }
        });
    }
}
