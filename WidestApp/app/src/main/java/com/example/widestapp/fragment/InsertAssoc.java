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
import com.example.widestapp.model.AssocProjServFunc;
import com.example.widestapp.model.Employee;
import com.example.widestapp.model.Project;
import com.example.widestapp.model.Service;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InsertAssoc extends Fragment implements AdapterView.OnItemSelectedListener {

    int codproj, codfunc, codserv;

    EditText funcionario, servico;

    Spinner spinner;

    List<Project> projects;

    List<Employee> employees;

    List<Service> services;

    Button cadastrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assoc_proj_serv_func, container, false);

        projects = Project.select(getContext());
        services = Service.select(getContext());
        employees = Employee.select(getContext());

        funcionario = view.findViewById(R.id.funcassoc);
        spinner = view.findViewById(R.id.spinnerassoc);
        cadastrar = view.findViewById(R.id.cadastrarAssoc);
        servico = view.findViewById(R.id.txtassocserv);

        List<String> names = new ArrayList<>();

        for (Project project : projects) {
            names.add(project.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        cadastrar.setOnClickListener(this::cadastrarAssoc);

        return view;
    }

    public static InsertAssoc newInstance() {
        return new InsertAssoc();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Project project = projects.get(position);
        codproj = project.getId();
    }

    public void cadastrarAssoc(View view) {

        boolean func = false;
        boolean serv = false;

        for (Employee employee : employees) {
            if (funcionario.getText().toString().equals(employee.getName())) {
                codfunc = employee.getId();
                func = true;
            }
        }
        if (!func) {
            funcionario.setError("O funcionário não existe");
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Funcionário não encontrado", Snackbar.LENGTH_LONG)
                    .show();
            return;
        }
        for (Service service : services) {
            if (servico.getText().toString().equals(service.getName())) {
                codserv = service.getId();
                serv = true;
            }
        }
        if (!serv) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Serviço não encontrado", Snackbar.LENGTH_LONG)
                    .show();
            servico.setError("O funcionário não existe");
            return;
        }

        new AssocProjServFunc(
                null,
                codfunc,
                codserv,
                codproj
        ).insert(getContext());

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Cadastro de conjunto efetuado com sucesso", Snackbar.LENGTH_LONG)
                .show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
