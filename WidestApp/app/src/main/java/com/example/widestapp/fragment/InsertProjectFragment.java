package com.example.widestapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.widestapp.R;
import com.example.widestapp.model.Client;
import com.example.widestapp.model.FotosProjeto;
import com.example.widestapp.model.Project;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class InsertProjectFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private final static String CAPA_PRE = "capa_pre";

    private final static String FOTOS_PRE = "fotos_pre";

    List<Project> projects;
    List<Client> clients;

    EditText nome, tema, data;

    Button cadastrarProj, adicionar;

    ImageView capa, fotos;

    Uri uricapa, urifotos;

    int codcli, id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_project, container, false);

        nome = view.findViewById(R.id.edtnameinsertproject);
        tema = view.findViewById(R.id.edttemainsertproject);
        data = view.findViewById(R.id.edtdataentrega);

        cadastrarProj = view.findViewById(R.id.btncadastrarproj);
        cadastrarProj.setOnClickListener(this::inserirProjeto);

        adicionar = view.findViewById(R.id.btnadicionarfoto);

        capa = view.findViewById(R.id.imgcapaproject);
        capa.setOnClickListener(this::pegarCapa);
        fotos = view.findViewById(R.id.imgfotosproject);
        fotos.setOnClickListener(this::pegarFotos);


        projects = Project.select(getContext());
        clients = Client.select(getContext());

        List<String> names = new ArrayList<>();

        for (Client client : clients)
        {
            names.add(client.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinnercliinsertproject);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }
    public static InsertProjectFragment newInstance()
    {
        return new InsertProjectFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Client client = clients.get(position);
        codcli = client.getId();
    }

    public void pegarCapa(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    public void pegarFotos(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {

            if (requestCode == 0) {
                uricapa = data.getData();
                if (uricapa != null) {

                    capa.setImageURI(uricapa);
                    try {
                        salvarCapa(uricapa);
                    }
                    catch (IOException e) {
                        Toast.makeText(getContext(), "Falha ao salvar imagem", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    }
                }
                else {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "Falha no carregamento da imagem", Snackbar.LENGTH_LONG)
                            .show();
                }

            }

            if (requestCode == 1) {
                urifotos = data.getData();
                if (urifotos != null)
                {
                    fotos.setImageURI(urifotos);
                }

            }
        }
    }

    public final static String CAPA = "img_capa_";
    public void salvarCapa (Uri uri) throws IOException
    {
        File directory = new File(getContext().getFilesDir(), CAPA_PRE);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Falha na criação da pasta");
            }
            int arquivos = directory.list().length;
            File result = new File(directory, CAPA + arquivos + 1);
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

    public final static String FOTOS = "img_fotos_";
    public void salvarFotos (Uri uri) throws IOException
    {
        File directory = new File(getContext().getFilesDir(), FOTOS_PRE);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Falha na criação da pasta");
            }
        }
            int arquivos = directory.list().length;

            File result = new File(directory, FOTOS + arquivos + 1);

            if (result.exists()) {
                Toast.makeText(getContext(), "Pasta já existente", Toast.LENGTH_SHORT).show();
            }

                OutputStream os = new FileOutputStream(result);
                InputStream is = getContext().getContentResolver().openInputStream(uri);

                byte[] buffer = new byte[4096];
                int size;
                while ((size = is.read(buffer)) != -1) {
                    os.write(buffer, 0, size);
                }
                new FotosProjeto(
                        null,
                        String.valueOf(urifotos),
                        id
                ).insert(getContext());
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Imagem cadastrada com sucesso", Snackbar.LENGTH_LONG)
                .show();


    }


    public void inserirProjeto(View view)
    {

        String Name = nome.getText().toString();
        String Data = data.getText().toString();
        String Tema = tema.getText().toString();

        if (nome.getText().toString().isEmpty() || data.getText().toString().isEmpty() || tema.getText().toString().isEmpty())
        {
            nome.setError("O nome do projeto deve ser informado");
            data.setError("A data de entrega do projeto deve ser informada");
            tema.setError("O tema deve ser informado");
            return;
        }

        id  = new Project(
                null,
                Name,
                Tema,
                codcli,
                String.valueOf(uricapa),
                Data
        ).insert(getContext());

        projects = Project.select(getContext());

        adicionar.setEnabled(true);
        adicionar.setOnClickListener(this::salvarImagem);

        Snackbar.make(getActivity().findViewById(android.R.id.content), "Projeto cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .show();


    }

    public void salvarImagem(View view){
        try {
            salvarFotos(urifotos);
        }
        catch (IOException e)
        {
            Toast.makeText(getContext(), "Falha ao salvar imagem", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
