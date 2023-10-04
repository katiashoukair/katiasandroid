package com.example.katiasproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiasproject.Admin.AddProductActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.katiasproject.User.home;
import com.example.katiasproject.User.info;
import com.example.katiasproject.User.settings;
import com.example.katiasproject.User.shoppingcart;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    TextView Username,email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Initialize Firebase Auth
        mAuth=FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //user is signed in
            View headeView = navigationView.getHeaderView(0);
            Username = headeView.findViewById(R.id.username4);
            email = headeView.findViewById(R.id.email4);
            Username.setText(user.getDisplayName());
            email.setText(user.getEmail());
        } else {

            // No user is signed
            Intent i = new Intent(MainActivity.this, AddProductActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        Toast.makeText(this, "start!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        Toast.makeText(this, "start!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.nav_home==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();

        }
        else if(R.id.nav_shoppingcart==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new shoppingcart()).commit();
        }
        else if(R.id.nav_info==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new info()).commit();
        }
        else if(R.id.nav_settings==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new settings()).commit();
        }
        else if(R.id.nav_logout==item.getItemId()){
            mAuth.signOut();
            startActivity(new Intent(this,Loginactivity.class));
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}