package com.example.widestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.widestapp.R;

import com.example.widestapp.model.Employee;
import com.google.android.material.snackbar.Snackbar;

import java.util.NoSuchElementException;

public class LoginActivity extends AppCompatActivity {

    EditText email, senha;

    ImageView imgsenha;

    Boolean olhoRiscado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imgsenha = findViewById(R.id.imgsenha);

        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);


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
        senha.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }
    public void deixarInvisivel()
    {
        imgsenha.setImageResource(R.drawable.visible);
        senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }
    public void testar(View view)
        {
            String Email = email.getText().toString();
            String Senha = senha.getText().toString();

            try {
                Employee.authenticate(Email, Senha, this);

               Intent intent = new Intent(this, MenuActivity.class);
               startActivity(intent);
               email.getText().clear();
               senha.getText().clear();
            }
            catch (NoSuchElementException ex) {

                Snackbar.make(findViewById(android.R.id.content), "Usuário incorreto!", Snackbar.LENGTH_LONG)
                    .show();
            }

        }

}

