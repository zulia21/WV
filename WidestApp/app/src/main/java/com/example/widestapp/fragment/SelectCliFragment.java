package com.example.widestapp.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectCliFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView nome, cnpj, telefone, endereco;

    String address;

    List <Client> clientes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_cli, container, false);

        nome = view.findViewById(R.id.txthintnameselectcli);
        cnpj = view.findViewById(R.id.txthintcnpjselectcli);
        telefone = view.findViewById(R.id.txthintelefoneselectcli);
        endereco = view.findViewById(R.id.txthintenderecoselectcli);

        endereco.setPaintFlags(endereco.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        endereco.setOnClickListener(this::gotoMaps);
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

        address = client.getAddress();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void gotoMaps(View view){
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            Address endereco = addresses.get(0);

            double latitude = endereco.getLatitude();
            double longitude = endereco.getLongitude();

            Uri uri = Uri.parse( "geo:0,0?q="+latitude + "," + longitude);
            Intent intentMap = new Intent(Intent.ACTION_VIEW, uri);
            intentMap.setPackage("com.google.android.apps.maps");
            intentMap.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            if (intentMap.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intentMap);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
