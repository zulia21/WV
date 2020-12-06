package com.example.widestapp.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.widestapp.fragment.ContractFragment;
import com.example.widestapp.fragment.MenuFragment;
import com.example.widestapp.R;
import com.example.widestapp.fragment.ClientFragment;
import com.example.widestapp.fragment.EmployeeFragment;
import com.example.widestapp.fragment.ProjectFragment;
import com.example.widestapp.fragment.ServicesFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_menu);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        HashMap<Integer, Fragment> map = new HashMap<>();

        map.put(R.id.nav_menu, new MenuFragment());
        map.put(R.id.nav_client, new ClientFragment());
        map.put(R.id.nav_employee, new EmployeeFragment());
        map.put(R.id.nav_services, new ServicesFragment());
        map.put(R.id.nav_project, new ProjectFragment());
        map.put(R.id.nav_contract, new ContractFragment());

        Fragment fragment = map.get(item.getItemId());

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}