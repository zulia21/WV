package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Project;
import com.example.widestapp.model.Report;
import com.example.widestapp.model.Service;

import java.util.ArrayList;
import java.util.List;

public class SelectReportFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView nome, data, conteudo, projcorresp;

    Spinner spinner;

    List<Report> reports;

    List<Project> projects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_report, container, false);


        reports = Report.select(getContext());
        projects = Project.select(getContext());

        nome = view.findViewById(R.id.txtselectnomereport);
        data = view.findViewById(R.id.txtdataselectreport);
        conteudo = view.findViewById(R.id.txtconteudoselectreport);
        projcorresp = view.findViewById(R.id.txtprojetoselectreport);
        spinner = view.findViewById(R.id.spinnerselectreport);

        List<String> names = new ArrayList<>();

        for (Report report : reports)
        {
            names.add(report.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }

    public static SelectReportFragment newInstance() {
        return new SelectReportFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Report report = reports.get(position);

        nome.setText(report.getName());
        data.setText(report.getData());
        conteudo.setText(report.getRelatorio());
            for(Project project : projects)
            {
                if (project.getId().equals(report.getIdProj()))
                {
                    projcorresp.setText(project.getName());
                }
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
