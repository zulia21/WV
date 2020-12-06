package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateContractFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_contract, container, false);

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("");
        spinnerArray.add("Nome");
        spinnerArray.add("Descrição");
        spinnerArray.add("Valor");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.spinnerupdatecampocontract);
        spinner.setAdapter(adapter);

        return view;
    }
    public static UpdateContractFragment newInstance()
    {
        return new UpdateContractFragment();
    }
}