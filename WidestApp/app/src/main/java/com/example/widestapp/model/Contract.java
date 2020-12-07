package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class Contract {
    private final Integer id;
    private final String name;
    private final Integer idCli;
    private final String descricao;
    private final Integer idProj;
    private final double valor;
    private final Integer idServico;

    public Contract(Integer id, String name, Integer idCli, String descricao, Integer idProj, double valor, Integer idServico) {
        this.id = id;
        this.name = name;
        this.idCli = idCli;
        this.descricao = descricao;
        this.idProj = idProj;
        this.valor = valor;
        this.idServico = idServico;
    }
    public Contract (Cursor cursor){
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.idCli = cursor.getInt(2);
        this.descricao = cursor.getString(3);
        this.idProj = cursor.getInt(4);
        this.valor = cursor.getDouble(5);
        this.idServico = cursor.getInt(6);
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdCli() {
        return idCli;
    }

    public Integer getIdProj() {
        return idProj;
    }

    public Integer getIdServico() {
        return idServico;
    }
    public void insert(Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();
        values.put("Nome",name);
        values.put("CodCli", idCli);
        values.put("Descricao", descricao);
        values.put("CodProj", idProj);
        values.put("Valor", valor);
        values.put("CodServico", idServico);
        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("Contrato",null, values);
    }

    public List<Contract> select(Context context)
    {
        SQLiteDatabase wv = Database.openFrom(context);
        Cursor cursor = wv.rawQuery("SELECT * FROM Contrato", null);
        List<Contract> contracts = new ArrayList<>();
        while (cursor.moveToNext())
        {
            Contract contract = new Contract(cursor);
            contracts.add(contract);

        }

        cursor.close();
        return contracts;
    }
}
