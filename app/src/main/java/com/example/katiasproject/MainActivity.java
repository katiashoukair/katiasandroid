package com.example.katiasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=findViewById(R.id.btlogPage);


              loginButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      startActivity(new Intent( MainActivity.this,Loginactivity.class));


                  }
              });











    }
}