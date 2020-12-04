package com.example.widestapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawer;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        // navbar
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.opendrawer, R.string.closedrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMenu()).commit();
            navigationView.setCheckedItem(R.id.nav_menu);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
            {
                case R.id.nav_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMenu()).commit();
                    break;
                case R.id.nav_cliente:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClienteFragment()).commit();
                    break;
                case R.id.nav_funcionario:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FuncionarioFragment()).commit();
                    break;

            }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}