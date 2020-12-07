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
import com.example.widestapp.model.Client;
import com.example.widestapp.model.Contract;
import com.example.widestapp.model.Project;
import com.example.widestapp.model.Service;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InsertContractFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerservico, spinnercli, spinnerproj;

    EditText nome, descricao, valor;

    List<Client> clients;
    List<Project> projects;
    List<Service> services;

    int codcli, codserv, codproj;

    List<String> nameforprojects, nameforclients, nameforservices;

    Button cadastrarServico;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_contract, container, false);

        nome = view.findViewById(R.id.edtnameinsertcontract);
        descricao = view.findViewById(R.id.edtdescinsertcontract);
        valor = view.findViewById(R.id.edtvalorinsertcontract);

        spinnercli = view.findViewById(R.id.spinnerclicorresp);
        spinnerproj = view.findViewById(R.id.spinnerprojcorresp);
        spinnerservico = view.findViewById(R.id.spinnerservicocorresp);

        projects = Project.select(getContext());
        clients = Client.select(getContext());
        services = Service.select(getContext());

        nameforclients = new ArrayList<>();
        nameforprojects = new ArrayList<>();
        nameforservices = new ArrayList<>();

        for (Project project : projects)
        {
            nameforprojects.add(project.getName());
        }
        for (Client client : clients)
        {
            nameforclients.add(client.getName());
        }
        for (Service service : services)
        {
            nameforservices.add(service.getName());
        }
        ArrayAdapter<String> adaptercli = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, nameforclients);

        ArrayAdapter<String> adapterproj = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, nameforprojects);

        ArrayAdapter<String> adapterserv = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, nameforservices);

        adaptercli.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapterproj.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapterserv.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerservico.setAdapter(adapterserv);
        spinnerproj.setAdapter(adapterproj);
        spinnercli.setAdapter(adaptercli);

        spinnercli.setOnItemSelectedListener(this);
        spinnerproj.setOnItemSelectedListener(this);
        spinnerservico.setOnItemSelectedListener(this);

        cadastrarServico = view.findViewById(R.id.btncadastrarcontract);
        cadastrarServico.setOnClickListener(this::cadastrar);

        return view;
    }
    public static InsertContractFragment newInstance()
    {
        return new InsertContractFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (view.getId())
        {
            case R.id.spinnerclicorresp:
                Client client = clients.get(position);
                codcli = client.getId();
                break;
            case R.id.spinnerprojcorresp:
                Project project = projects.get(position);
                codproj = project.getId();
                break;
            case R.id.spinnerservicocorresp:
                Service service = services.get(position);
                codserv = service.getId();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void cadastrar (View view)
    {
        if (nome.getText().toString().isEmpty() || valor.getText().toString().isEmpty() || descricao.getText().toString().isEmpty())
        {
            nome.setError("O nome deve ser preenchido!");
            valor.setError("O valor deve ser preenchido!");
            descricao.setError("A descrição deve ser preenchida");
        }

        String Nome = nome.getText().toString();
        String Valor = valor.getText().toString();
        String Descricao = descricao.getText().toString();


        new Contract(
                null,
                Nome,
                codcli,
                Descricao,
                codproj,
                Double.parseDouble(Valor),
                codserv
        ).insert(getContext());

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Contrato cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .show();

    }
}
