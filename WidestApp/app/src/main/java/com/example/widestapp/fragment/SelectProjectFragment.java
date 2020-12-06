package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.widestapp.R;
import com.example.widestapp.model.Client;
import com.example.widestapp.model.Employee;
import com.example.widestapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class SelectProjectFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    List<Client> clientes;
    List<Project> projects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_select_projeto, container, false);

         clientes = Client.select(getContext());
         projects = Project.select(getContext());

         List<String> names = new ArrayList<>();

         for (Project project : projects)
         {
             names.add(project.getName());
         }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.spinnerselectproj);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

         return view;
    }
    public static SelectProjectFragment newInstance() {
        return new SelectProjectFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Project project = projects.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
