package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Client;
import com.google.android.material.snackbar.Snackbar;

public class InsertClientFragment extends Fragment {

    EditText nome, cnpj, telefone, endereco;

    Button cadastrarcli;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_cli, container, false);

        cadastrarcli = view.findViewById(R.id.btncadastrarcli);
        nome = view.findViewById(R.id.edtnameinsertcli);
        cnpj = view.findViewById(R.id.edtcnpjinsertcli);
        telefone = view.findViewById(R.id.edttelefoneinsertcli);
        endereco = view.findViewById(R.id.edtenderecoinsertcli);

        cadastrarcli.setOnClickListener(this::insertCLi);

        return view;
    }
    public static InsertClientFragment newInstance() {
        return new InsertClientFragment();
    }

    public void insertCLi(View view)
    {
        String Nome = nome.getText().toString();
        String CNPJ = cnpj.getText().toString();
        String Telefone = telefone.getText().toString();
        String Endereco = endereco.getText().toString();

        if (nome.getText().toString().isEmpty() || cnpj.getText().toString().isEmpty() || telefone.getText().toString().isEmpty() || endereco.getText().toString().isEmpty())
        {
            nome.setError("O nome do cliente deve ser preenchido!");
            cnpj.setError("O CNPJ do cliente deve ser preenchido!");
            telefone.setError("O telefone do cliente deve ser preenchido!");
            endereco.setError("O endereço do cliente deve ser preenchido!");
            return;
        }
        if (!CNPJ.matches(("\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}")))
        {
            cnpj.setError("O CNPJ deve ser preenchido corretamente");
            return;
        }

        new Client(
                null,
                Nome,
                CNPJ,
                Endereco,
                Telefone
        ).insert(getContext());

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Cliente cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .show();
    }
    
}
