package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class ProjectFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_page:
                break;
            case R.id.change_page:
                break;
            case R.id.select_page:
                break;
        }
       return true;
    }
}
