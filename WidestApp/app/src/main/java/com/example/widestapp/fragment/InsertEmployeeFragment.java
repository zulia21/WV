package com.example.widestapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Employee;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

public class InsertEmployeeFragment extends Fragment {

    public final static String IMAGE_DIRECTORY = "wv_perfil";

    EditText nome, cargo, senha, email;

    String Nome, Cargo, Senha, Email;

    Uri uri;

    Switch ativo;

    Button cadastrar;

    ImageView fotoPerfil;

    boolean valorSwitch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_employee, container, false);

        cadastrar = view.findViewById(R.id.btninsertemployee);
        nome = view.findViewById(R.id.edtinsertnameemployee);
        cargo = view.findViewById(R.id.edtinsertcargoemployee);
        senha = view.findViewById(R.id.edtinsertsenhaemployee);
        email = view.findViewById(R.id.edtinsertemailemployee);
        fotoPerfil = view.findViewById(R.id.imgfotoperfilinsertemployee);
        ativo = view.findViewById(R.id.swtativo);

        fotoPerfil.setOnClickListener(this::pegarCaminhoFoto);
        cadastrar.setOnClickListener(this::inserirFuncionario);

        return view;

    }
    public static InsertEmployeeFragment newInstance() {
        return new InsertEmployeeFragment();
    }


    public void pegarCaminhoFoto(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            uri = data.getData();
            if (uri != null) {

                fotoPerfil.setImageURI(uri);

                    try {
                        salvarImagem(uri);

                    } catch (IOException e) {
                        Toast.makeText(getContext(), "Falha ao salvar imagem", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    }
            }
            else
            {
                Snackbar.make(getActivity().findViewById(android.R.id.content), "Falha no carregamento da imagem", Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }

    public final static String PRE_IMG = "img_";
    public void salvarImagem (@NonNull Uri uri) throws IOException
    {
        File directory = new File(getContext().getFilesDir(), IMAGE_DIRECTORY);

        if (!directory.exists())
        {
            if(!directory.mkdir())
            {
                throw new IOException("Falha na criação da pasta");
            }
            int arquivos = directory.list().length;
            File result = new File(directory, PRE_IMG + arquivos + 1);

            if (result.exists())
            {
                Toast.makeText(getContext(), "Pasta já existente", Toast.LENGTH_SHORT).show();
            }

            OutputStream os = new FileOutputStream(result);
            InputStream is = getContext().getContentResolver().openInputStream(uri);

            byte[] buffer = new byte[4096];
            int size;
            while ((size = is.read(buffer)) != -1)
            {
                os.write(buffer, 0, size);
            }

        }
    }

    public void inserirFuncionario(View view)
    {
        Nome = nome.getText().toString();
        Cargo = cargo.getText().toString();
        Senha = senha.getText().toString();
        Email = email.getText().toString();

        if (nome.getText().toString().isEmpty() || cargo.getText().toString().isEmpty()  || senha.getText().toString().isEmpty()   || email.getText().toString().isEmpty() )
        {
            nome.setError("O nome deve ser preenchido!");
            cargo.setError("O cargo deve ser preenchido!");
            senha.setError("A senha deve ser preenchida!");
            email.setError("O email deve ser preenchido!");
            return;
        }
        if (uri == null)
        {
            Toast.makeText(getContext(), "Uma imagem deve ser inserida!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (ativo.isChecked())
        {
            valorSwitch = true;
        }
        else {
            valorSwitch = false;
        }

        new Employee(
                null,
                Nome,
                valorSwitch,
                Senha,
                Email,
                Cargo,
                String.valueOf(uri)
        ).insert(getContext());

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Funcionário cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .show();


    }


}
