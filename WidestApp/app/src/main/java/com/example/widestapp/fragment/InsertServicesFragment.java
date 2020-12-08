package com.example.widestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Service;
import com.google.android.material.snackbar.Snackbar;

public class InsertServicesFragment extends Fragment {

    EditText nome, valor, descricao;

    Button cadastrarServico;

    boolean valorswitch;

    Switch ativo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_services, container, false);

        nome = view.findViewById(R.id.edtinsertnameservices);
        valor = view.findViewById(R.id.edtinsertvalorservices);
        descricao = view.findViewById(R.id.edtinsertdescservices);

        ativo = view.findViewById(R.id.swtativoservice);

        cadastrarServico = view.findViewById(R.id.btncadastrarservico);
        cadastrarServico.setOnClickListener(this::cadastrarServicos);


        return view;
    }
    public static InsertServicesFragment newInstance() {
        return new InsertServicesFragment();
    }

    public void cadastrarServicos(View view)
    {
        String Nome = nome.getText().toString();
        String Valor = valor.getText().toString();
        String Descricao = descricao.getText().toString();


        if (nome.getText().toString().isEmpty() || valor.getText().toString().isEmpty() || descricao.getText().toString().isEmpty())
        {
            nome.setError("Um nome deve ser informado");
            valor.setError("Um valor deve ser informado");
            descricao.setError("Uma descrição deve ser informada");
        }


        if (ativo.isChecked())
        {
            valorswitch = true;
        }
        else {
            valorswitch = false;
        }

        new Service(
                null,
                Nome,
                Double.parseDouble(Valor),
                Descricao,
                valorswitch
        ).insert(getContext());

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Servico cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .show();

    }

}
