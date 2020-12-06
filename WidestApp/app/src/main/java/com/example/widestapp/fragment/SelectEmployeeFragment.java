package com.example.widestapp.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Database;
import com.example.widestapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class SelectEmployeeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Context mContext;



    TextView nome, cargo, email;

    Switch switchemployee;

    ImageView fotoperfil;

    List<Employee> employees;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_employee, container, false);


        employees = Employee.select(getContext());
        nome = view.findViewById(R.id.txthintnameemployee);
        cargo = view.findViewById(R.id.txthintcargoemployee);
        email = view.findViewById(R.id.txthintemailemployee);
        fotoperfil = view.findViewById(R.id.imgfotoemployee);
        switchemployee = view.findViewById(R.id.switchemployee);

        List<String> names = new ArrayList<>();
        for (Employee employee : employees) {
            names.add(employee.getName());
        }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getContext(), android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = view.findViewById(R.id.spinnerfunc);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);


        return view;
    }

    public static SelectEmployeeFragment newInstance() {
        return new SelectEmployeeFragment();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Employee employee = employees.get(position);
        nome.setText(employee.getName());
        cargo.setText(employee.getFunction());
        email.setText(employee.getEmail());
        if (employee.getImageName() != null) {
            fotoperfil.setImageURI(Uri.parse(employee.getImageName()));
        }
        else {
            fotoperfil.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.back));
        }
            switchemployee.setChecked(employee.isActive());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
