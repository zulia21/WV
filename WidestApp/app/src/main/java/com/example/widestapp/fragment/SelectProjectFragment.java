package com.example.widestapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.widestapp.R;
import com.example.widestapp.model.Client;
import com.example.widestapp.model.Employee;
import com.example.widestapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class SelectProjectFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView nome, tema, clicorrespond, data;

    ImageView fotocapa;

    List<Client> clientes;
    List<Project> projects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_select_projeto, container, false);

         nome = view.findViewById(R.id.txthintselectnameproject);
         tema = view.findViewById(R.id.txthintselecttemaproject);
         clicorrespond = view.findViewById(R.id.txtclicorrespondselectproject);
         data = view.findViewById(R.id.txthintdataselectproject);
         fotocapa = view.findViewById(R.id.imgcapaproj);

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
        Client client = clientes.get(position);
        Project project = projects.get(position);
        nome.setText(project.getName());
        tema.setText(project.getTema());
        data.setText(project.getData());

            if (client.getId().equals(project.getIdcli()))
            {
                clicorrespond.setText(client.getName());
            }
            if (project.getCapa() == null)
            {
                fotocapa.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.back));
            }

        fotocapa.setImageURI(Uri.parse(project.getCapa()));


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
