package com.yusril.asrama.activity.hal_peraturan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yusril.asrama.R;

public class PeraturanAsrama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peraturan_asrama);
        getSupportActionBar().hide();
    }
}
