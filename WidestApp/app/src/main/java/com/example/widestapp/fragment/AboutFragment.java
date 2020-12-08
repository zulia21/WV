package com.example.widestapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AboutFragment extends Fragment implements OnSuccessListener<Location>, OnFailureListener {

    public final static int COD_LOCAL = 0;

    TextView resultadostext;

    ImageView maps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        maps = view.findViewById(R.id.imgabout);
        maps.setOnClickListener(this::mapear);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, COD_LOCAL);
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Requerindo permissão", Snackbar.LENGTH_LONG)
                    .show();
        }
        else{
            final FusedLocationProviderClient fusedLocation = LocationServices.getFusedLocationProviderClient(getContext());
            fusedLocation.getLastLocation().addOnSuccessListener(this);
            fusedLocation.getLastLocation().addOnFailureListener(this);

        }
        resultadostext = view.findViewById(R.id.txtresultados);
        return view;
    }
    public void mapear(View view){
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

            List<Address> addresses = geocoder.getFromLocationName("Rua Vergueiro, 2016 - Vila Mariana", 1);
            Address endereco = addresses.get(0);

            double latitude = endereco.getLatitude();
            double longitude = endereco.getLongitude();

            Uri uri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);
        }
        catch (IOException E)
        {
            E.printStackTrace();
        }

    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Log.e("Localização Fused", "Falha em obter localização");
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Não conseguimos obter sua localização :(, por favor, tente novamente", Snackbar.LENGTH_LONG)
                .show();

    }
    float[] results = new float[1];

    @Override
    public void onSuccess(Location location) {
        if(location != null)
        {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

            try {
                List<Address> addresses = geocoder.getFromLocationName("Rua Vergueiro, 2016 - Vila Mariana", 1);
                Address endereco = addresses.get(0);

                double latitude2 = endereco.getLatitude();
                double longitude2 = endereco.getLongitude();

                Location.distanceBetween(latitude, longitude, latitude2, longitude2, results);

                float resultados = results[0]/1000;

                resultadostext.setText(String.valueOf(resultados + ""+ "km"));



            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        else {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Não foi possível obter sua localização :(", Snackbar.LENGTH_LONG)
                    .show();
        }

    }
}
