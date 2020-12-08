package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private final Integer id;
    private final String name;
    private final double valor;
    private final String descricao;
    private final boolean ativo;


    public Service(Integer id, String name, double valor, String descricao, boolean ativo) {
        this.id = id;
        this.name = name;
        this.valor = valor;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Service (Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.valor = cursor.getDouble(2);
        this.descricao = cursor.getString(3);
        this.ativo = cursor.getInt(4) != 0;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void insert(Context context){
        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();
        values.put("Nome", name);
        values.put("Valor", valor);
        values.put("Descricao", descricao);
        values.put("ATIVO", ativo);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("Servico", null, values);

    }
    public static List<Service> select(Context context) {
        SQLiteDatabase wv = Database.openFrom(context);
        Cursor cursor = wv.rawQuery("SELECT * FROM Servico;", null);
        List<Service> services = new ArrayList<>();
        while (cursor.moveToNext())
        {
         Service service = new Service(cursor);
         services.add(service);
        }
        cursor.close();
        return services;
    }



}
