package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class UpdateEmployeeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    
    List<Employee> employees;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_employee, container, false);
        
        employees = Employee.select(getContext());
        
        List<String> names = new ArrayList<>();
        
        for (Employee employee : employees) {
            names.add(employee.getName());
        }

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("");
        spinnerArray.add("Nome");
        spinnerArray.add("Codigo");
        spinnerArray.add("Ativo");
        spinnerArray.add("Email");
        spinnerArray.add("Cargo");
        spinnerArray.add("Foto de Perfil");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.spinnerupdatecampoemployee);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, names
        );
        
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = view.findViewById(R.id.spinnerchooseemployee);
        spinner2.setOnItemSelectedListener(this);
        spinner2.setAdapter(adapter2);
        
        
        
        return view;
    }
    public static UpdateEmployeeFragment newInstance() {
        return new UpdateEmployeeFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
