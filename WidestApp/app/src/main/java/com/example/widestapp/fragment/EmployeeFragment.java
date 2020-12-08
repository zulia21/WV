package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.widestapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmployeeFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_employee, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav_employee);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState == null)
        {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_employee, new InsertEmployeeFragment()).commit();
        }
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (item.getItemId())
        {
            case R.id.add_page:
                Fragment insertemployee =  InsertEmployeeFragment.newInstance();
                transaction.replace(R.id.fragment_container_employee, insertemployee);
                transaction.commit();
                break;

            case R.id.select_page:
                Fragment selectemployee =  SelectEmployeeFragment.newInstance();
                transaction.replace(R.id.fragment_container_employee, selectemployee);
                transaction.commit();
                break;

        }
        return true;
    }
}
