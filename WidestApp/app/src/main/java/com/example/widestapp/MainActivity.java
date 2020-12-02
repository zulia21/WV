package com.example.widestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imgsenha;

    Boolean olhoRiscado = false;

    EditText edtsenha;

    RecyclerView recyclerView;

    TrabAdapter adapter;

    List<Trab_Anterior_Item> result = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgsenha = (ImageView) findViewById(R.id.imgsenha);

        edtsenha = (EditText) findViewById(R.id.edtSenha);



    }
    public void trocarVisibilidade(View v)
    {
        olhoRiscado = !olhoRiscado;
       if (olhoRiscado)
        {
            deixarVisivel();
        }
        else
        {
            deixarInvisivel();
        }
    }
    public void deixarVisivel()
    {
        imgsenha.setImageResource(R.drawable.invisible);
        edtsenha.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }
    public void deixarInvisivel()
    {
        imgsenha.setImageResource(R.drawable.view);
        edtsenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }
    public void testar(View view)
        {
           Intent intent = new Intent(this, MenuActivity.class);
           startActivity(intent);
        }

}

