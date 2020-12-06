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
import com.example.widestapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class SelectCliFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView nome, cnpj, telefone, endereco;

    List <Client> clientes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_cli, container, false);

        nome = view.findViewById(R.id.txthintnameselectcli);
        cnpj = view.findViewById(R.id.txthintcnpjselectcli);
        telefone = view.findViewById(R.id.txthintelefoneselectcli);
        endereco = view.findViewById(R.id.txthintenderecoselectcli);

        clientes = Client.select(getContext());

        List<String> names = new ArrayList<>();

        for (Client client : clientes)
        {
            names.add(client.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinnerselectcli);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        
        return view;
    }
    public static SelectCliFragment newInstance()
    {
        return new SelectCliFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Client client = clientes.get(position);
        nome.setText(client.getName());
        cnpj.setText(client.getCnpj());
        endereco.setText(client.getAddress());
        telefone.setText(client.getTelephone());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
