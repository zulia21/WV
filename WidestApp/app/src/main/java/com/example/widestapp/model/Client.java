package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final Integer id;
    private final String name;
    private final String cnpj;
    private final String address;
    private final String telephone;

    public Client(Integer id, String name, String cnpj, String endereco, String telefone)
    {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = endereco;
        this.telephone = telefone;

    }
    public Client(Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.cnpj = cursor.getString(2);
        this.address = cursor.getString(3);
        this.telephone = cursor.getString(4);

    }

    public Integer getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public void insert(Context context){
        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();
        values.put("Nome", name);
        values.put("CNPJ", cnpj);
        values.put("Endereco", address);
        values.put("Telefone", telephone);
        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("Cliente", null, values);
    }
    public static List<Client> select (@NonNull Context context)
    {
        SQLiteDatabase wv = Database.openFrom(context);
        Cursor cursor = wv.rawQuery("SELECT * FROM Cliente", null);
        List<Client> clients = new ArrayList<>();
        while (cursor.moveToNext()){

            Client client = new Client(cursor);
            clients.add(client);
        }
        cursor.close();
        return clients;
    }

}
