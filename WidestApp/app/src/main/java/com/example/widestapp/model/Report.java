package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private final Integer id;
    private final String name;
    private final String data;
    private final String relatorio;
    private final Integer idProj;

    public Report(Integer id, String name, String data, String relatorio, Integer idProj) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.relatorio = relatorio;
        this.idProj = idProj;
    }

    public Report (Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.data = cursor.getString(2);
        this.relatorio = cursor.getString(3);
        this.idProj = cursor.getInt(4);

    }

    public Integer getId() {
        return id;
    }

    public Integer getIdProj() {
        return idProj;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void insert (Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();
        values.put("Nome", name);
        values.put("Data", data);
        values.put("RELATORIO", relatorio);
        values.put("CodProjeto", idProj);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("Relatorio", null, values);
    }
    public static List<Report> select(Context context)
    {
        SQLiteDatabase wv = Database.openFrom(context);

        Cursor cursor = wv.rawQuery("SELECT * FROM Relatorio", null);

        List<Report> reports = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Report report = new Report(cursor);
            reports.add(report);
        }
        cursor.close();
        return reports;
    }
}

