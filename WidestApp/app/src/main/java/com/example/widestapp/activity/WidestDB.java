package com.example.widestapp.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WidestDB extends SQLiteOpenHelper {
    public static final String WV_BANCO= "WidestAppDB";

    public WidestDB(@Nullable Context context) {
        super(context, WV_BANCO, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Cliente (COD INTEGER PRIMARY KEY, Nome TEXT, CNPJ TEXT, Endereco TEXT, Telefone TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Funcionario(COD INTEGER PRIMARY KEY, Nome TEXT, Ativo INT, Senha TEXT, Email TEXT, Cargo TEXT, RecursoImagem TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Servico (COD INTEGER PRIMARY KEY, Nome TEXT, Valor REAL, Descricao TEXT, ATIVO INT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Projeto(COD INTEGER PRIMARY KEY, Nome TEXT, Tema TEXT, CodCli INTEGER, Capa TEXT, FOREIGN KEY(CodCli) REFERENCES Cliente(COD));");

        db.execSQL("CREATE TABLE IF NOT EXISTS FotosProjeto (COD INTEGER PRIMARY KEY, Caminho TEXT, CodProjeto INTEGER, FOREIGN KEY(CodProjeto) REFERENCES Projeto(COD));");

        db.execSQL("CREATE TABLE IF NOT EXISTS Contrato(COD INTEGER PRIMARY KEY, Nome TEXT, CodCli INTEGER, Descricao TEXT, CodProj INTEGER, " +
                "Valor REAL, FOREIGN KEY (CodCli) REFERENCES Cliente(COD), FOREIGN KEY(CodProj) REFERENCES Projeto(COD));");

        db.execSQL("CREATE TABLE IF NOT EXISTS Relatorio (COD INTEGER PRIMARY KEY, Nome TEXT, Data TEXT, RELATORIO TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS ASSOC_SERV_PROJ_FUNC (COD INTEGER PRIMARY KEY, CodServ INTEGER, CodProj INTEGER, CodFunc INTEGER," +
                "FOREIGN KEY (CodServ) REFERENCES Servico(COD), FOREIGN KEY (CodProj) REFERENCES Projeto(COD), FOREIGN KEY (CodFunc) " +
                "REFERENCES Funcionario(COD)); ");

        db.execSQL("CREATE TABLE IF NOT EXISTS ASSOC_CONTRATO_SERV (COD INTEGER PRIMARY KEY, CodContrato INTEGER, CodServ INTEGER," +
                "FOREIGN KEY (CodContrato) REFERENCES Contrato(COD), FOREIGN KEY (CodServ) REFERENCES Servico(COD));");

        db.execSQL("CREATE TABLE IF NOT EXISTS ASSOC_SERV_FUNC (COD INTEGER PRIMARY KEY, CodServ INTEGER, CodFunc INTEGER," +
                "FOREIGN KEY (CodServ) REFERENCES Servico(COD), FOREIGN KEY (CodFunc) REFERENCES Funcionario(COD))");
    }
    public void onInsertClient (String nome, String cnpj, String endereco, String telefone, SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO Cliente (ROWID, Nome, CNPJ, Endereco, Telefone) VALUES (null, ?, ?, ?, ?);",
                new String[]{nome, cnpj, endereco, telefone});
    }
    public void onInsertEmployee (String nome, int ativo, String senha, String email, String cargo, String recursoImagem, SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO Funcionario(ROWID, Nome, Ativo, Senha, Email, Cargo, RecursoImagem) VALUES (null, ?, ?, ?, ?, ?, ?);",
                new String[]{nome,String.valueOf(ativo), senha, email, cargo, recursoImagem});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
