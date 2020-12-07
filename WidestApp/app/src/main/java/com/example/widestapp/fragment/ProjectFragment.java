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

public class ProjectFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener,
InsertProjectFragment.onFragmentChange{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navproject);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState == null)
        {
            InsertProjectFragment fragment = new InsertProjectFragment();
            fragment.setChangeListener(this);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_project, fragment).commit();

        }
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (item.getItemId())
        {
            case R.id.add_page:
                InsertProjectFragment insertproject =  InsertProjectFragment.newInstance();
                insertproject.setChangeListener(this);
                transaction.replace(R.id.fragment_container_project, insertproject);
                transaction.commit();
                break;
            case R.id.change_page:
                Fragment alterproject = UpdateProjectFragment.newInstance();
                transaction.replace(R.id.fragment_container_project, alterproject);
                transaction.commit();
                break;
            case R.id.select_page:
                Fragment selectproject =  SelectProjectFragment.newInstance();
                transaction.replace(R.id.fragment_container_project, selectproject);
                transaction.commit();
                break;
        }
       return true;
    }

    @Override
    public void onChange() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment assocInsert = InsertAssoc.newInstance();
        transaction.replace(R.id.fragment_container_project, assocInsert);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
