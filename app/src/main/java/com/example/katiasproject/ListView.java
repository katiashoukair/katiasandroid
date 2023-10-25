package com.example.katiasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.katiasproject.Admin.ShowProduct;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

    public void setOnItemClickListener(ShowProduct showProduct) {
    }
}