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
import com.example.widestapp.model.Client;
import com.example.widestapp.model.Contract;
import com.example.widestapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class SelectContractFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView nome, clicorresp, projcorresp, descricao, valor;

    List<Client> clients;

    List<Project> projects;

    List<Contract> contracts;

    Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_contract, container, false);
        
        nome = view.findViewById(R.id.txtnomeselectcontract);
        clicorresp = view.findViewById(R.id.txtclicorrespcontrato);
        projcorresp = view.findViewById(R.id.txtprojcorrespcontract);
        descricao = view.findViewById(R.id.txtselectdescricaocontract);
        valor = view.findViewById(R.id.txtselectvalorcontract);

        spinner = view.findViewById(R.id.spinnerselectcontract);

        clients = Client.select(getContext());
        projects = Project.select(getContext());
        contracts = Contract.select(getContext());

        List<String> names = new ArrayList<>();

        for (Contract contract : contracts)
        {
            names.add(contract.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        
        return view;
    }
    public static SelectContractFragment newInstance(){
        return new SelectContractFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Contract contract = contracts.get(position);

        for (Client client : clients)
        {
            if (client.getId().equals(contract.getIdCli()))
            {
                clicorresp.setText(client.getName());
            }
        }
        for (Project project : projects)
        {
            if (project.getId().equals(contract.getIdProj()))
            {
                projcorresp.setText(project.getName());
            }
        }
        nome.setText(contract.getName());
        descricao.setText(contract.getDescricao());
        valor.setText(String.valueOf(contract.getValor()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
