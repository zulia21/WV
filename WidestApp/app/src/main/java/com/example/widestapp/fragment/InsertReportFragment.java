package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Project;
import com.example.widestapp.model.Report;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InsertReportFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText nome, data, conteudo;

    Spinner spinner;

    List<Project> projects;

    int codproj;

    Button cadastrarReport;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_report, container, false);

        projects = Project.select(getContext());

        nome = view.findViewById(R.id.edtnameinsertreport);
        data = view.findViewById(R.id.edtdatainsertreport);
        conteudo = view.findViewById(R.id.edtconteudoinsertreport);

        spinner = view.findViewById(R.id.spinnerinsertreport);

        cadastrarReport = view.findViewById(R.id.btncadastrareport);

        List<String> names = new ArrayList<>();

        for(Project project : projects)
        {
            names.add(project.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        cadastrarReport.setOnClickListener(this::insertReport);

        return view;

    }
    public static InsertReportFragment newInstance() {
        return new InsertReportFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Project project = projects.get(position);
        codproj = project.getId();

    }

    public void insertReport(View view)
    {

        String Data = data.getText().toString();
        String Conteudo = conteudo.getText().toString();
        String Nome = nome.getText().toString();

        if (nome.getText().toString().isEmpty() || conteudo.getText().toString().isEmpty() || data.getText().toString().isEmpty())
        {
            nome.setError("O nome deve ser preenchido");
            conteudo.setError("O conteúdo deve ser preenchido");
            data.setError("Uma data deve ser informada");
            return;

        }
        new Report(
                null,
                Nome,
                Data,
                Conteudo,
                codproj
        ).insert(getContext());

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Relatório cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
