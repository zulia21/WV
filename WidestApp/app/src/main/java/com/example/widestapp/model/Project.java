package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final Integer id;
    private final String name;
    private final String tema;
    private final Integer idcli;
    private final String capa;
    private final String data;

    public Project(Integer id, String name, String tema, Integer idcli, String capa, String data) {
        this.id = id;
        this.name = name;
        this.tema = tema;
        this.idcli = idcli;
        this.capa = capa;
        this.data = data;
    }

    public Project (Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.tema = cursor.getString(2);
        this.idcli = cursor.getInt(3);
        this.capa = cursor.getString(4);
        this.data = cursor.getString(5);

    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdcli() {
        return idcli;
    }

    public String getTema() {
        return tema;
    }

    public String getCapa() {
        return capa;
    }

    public String getData() {
        return data;
    }

    public int insert(Context context){

        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();
        values.put("Nome", name);
        values.put("CodCli", idcli);
        values.put("Tema", tema);
        values.put("Capa", capa);
        values.put("DataEntrega", data);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        return (int) database.insertOrThrow("Projeto", null, values);


    }
    public static List<Project> select(Context context)
    {

        SQLiteDatabase wv = Database.openFrom(context);

        Cursor cursor = wv.rawQuery("SELECT * FROM Projeto", null);

        List<Project> projects = new ArrayList<>();

        while (cursor.moveToNext()){

            Project project = new Project(cursor);
            projects.add(project);
        }

        cursor.close();
        return projects;
    }

}
