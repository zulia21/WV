package com.example.widestapp.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.widestapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;


public class ClientFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_client, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null)
        {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cli, new InsertClientFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.add_page);
        }

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (item.getItemId())
        {
            case R.id.add_page:
                Fragment insertcli =  InsertClientFragment.newInstance();
                transaction.replace(R.id.fragment_container_cli, insertcli);
                transaction.commit();
                break;


            case R.id.select_page:
                Fragment selectcli = SelectCliFragment.newInstance();
                transaction.replace(R.id.fragment_container_cli, selectcli);
                transaction.commit();
                break;
        }
        return true;
    }

}
