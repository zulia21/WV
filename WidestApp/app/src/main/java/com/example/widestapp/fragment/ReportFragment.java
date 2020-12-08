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

public class ReportFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        bottomNavigationView = view.findViewById(R.id.bottom_navreport);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null)
        {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment insertReport = InsertReportFragment.newInstance();
            transaction.replace(R.id.fragment_container_report, insertReport);
            transaction.commit();

        }

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (item.getItemId())
        {
            case R.id.add_page:
                Fragment insertReport = InsertReportFragment.newInstance();
                transaction.replace(R.id.fragment_container_report, insertReport);
                transaction.commit();
                break;
            case R.id.select_page:
                Fragment selectReport = SelectReportFragment.newInstance();
                transaction.replace(R.id.fragment_container_report, selectReport);
                transaction.commit();
                break;
        }

        return true;
    }
}
