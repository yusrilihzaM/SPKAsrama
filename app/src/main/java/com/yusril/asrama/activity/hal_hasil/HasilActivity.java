package com.yusril.asrama.activity.hal_hasil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yusril.asrama.R;
import com.yusril.asrama.activity.ModelSPK;
import com.yusril.asrama.activity.hal_tambah_mhs.ModelMahasiswa;

import java.time.Instant;

public class HasilActivity extends AppCompatActivity {
    CardView step,rank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        getSupportActionBar().setTitle("Hasil");
        step=findViewById(R.id.step);
        rank=findViewById(R.id.ranking);
        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HasilActivity.this,PerhitunganActivity.class));
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
