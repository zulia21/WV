package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Service;

import java.util.ArrayList;
import java.util.List;

public class SelectServicesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView nome,valor, descrição;

    Switch ativo;

    List<Service> services;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_services, container, false);


        nome = view.findViewById(R.id.txthintselectnameservico);
        valor = view.findViewById(R.id.txthintselectvalorservico);
        descrição = view.findViewById(R.id.txthintselectdescricaoservico);

        ativo = view.findViewById(R.id.swtativoservico);

        services = Service.select(getContext());

        List<String> names = new ArrayList<>();
        for (Service service : services)
        {
            names.add(service.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinnerselectservices);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        
        return view;
    }
    public static SelectServicesFragment newInstance() {
        return new SelectServicesFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Service service = services.get(position);
        nome.setText(service.getName());
        valor.setText(String.valueOf("R$"+ service.getValor()));
        descrição.setText(service.getDescricao());
        ativo.setChecked(service.isAtivo());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
