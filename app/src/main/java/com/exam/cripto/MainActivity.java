package com.exam.cripto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageView imageBg = findViewById(R.id.main_bg);
        imageBg.setImageResource(R.drawable.mainbg);
        imageBg.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Intent intent = new Intent();

        switch (id) {
            case R.id.nav_rsa:
                intent = new Intent(MainActivity.this, RsaActivity.class);
                break;
            case R.id.nav_rc4:
                intent = new Intent(MainActivity.this, Rc4Activity.class);
                break;
            case R.id.nav_elgamal:
                intent = new Intent(MainActivity.this, ElGamalActivity.class);
                break;
            case R.id.nav_diffie_hellman:
                intent = new Intent(MainActivity.this, DiffieHellmanKeyProtocolActivity.class);
        }

        startActivityForResult(intent, 101);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}