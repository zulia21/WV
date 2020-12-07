package com.example.widestapp.model;

import android.database.Cursor;

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
}
