package com.example.widestapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.widestapp.fragment.AboutFragment;
import com.example.widestapp.fragment.ContractFragment;
import com.example.widestapp.fragment.MenuFragment;
import com.example.widestapp.R;
import com.example.widestapp.fragment.ClientFragment;
import com.example.widestapp.fragment.EmployeeFragment;
import com.example.widestapp.fragment.ProjectFragment;
import com.example.widestapp.fragment.ServicesFragment;
import com.example.widestapp.model.Database;
import com.example.widestapp.model.Employee;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawer;

    NavigationView navigationView;

    List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        employees = Employee.select(this);
        // navbar

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);

        ImageView perfil = header.findViewById(R.id.imguserprincipal);
        TextView nome = header.findViewById(R.id.txtnomeheader);
        TextView email = header.findViewById(R.id.txtemailheader);



        Intent intent = getIntent();

        String Email = intent.getStringExtra(LoginActivity.EMAIL);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.opendrawer, R.string.closedrawer);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_menu);
        }

        Cursor cursor = Database.openFrom(this).rawQuery("SELECT * from Funcionario WHERE EMAIL = ?", new String[]{Email}, null);
        while (cursor.moveToNext()){
            assert Email != null;
            if (Email.equals(cursor.getString(4)))
            {
                nome.setText(cursor.getString(1));
                email.setText(Email);
                if (cursor.getString(6) != null) {
                    perfil.setImageURI(Uri.parse(cursor.getString(6)));
                }
                else {
                    perfil.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back));
                }
            }

        }
        cursor.close();
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
        map.put(R.id.nav_about, new AboutFragment());

        Fragment fragment = map.get(item.getItemId());

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}