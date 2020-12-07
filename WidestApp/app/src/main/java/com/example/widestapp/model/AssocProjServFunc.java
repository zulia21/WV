package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class AssocProjServFunc {
    private final Integer id;
    private final Integer idfunc;
    private final Integer idserv;
    private final Integer idproj;

    public AssocProjServFunc(Integer id, Integer idfunc, Integer idserv, Integer idproj) {
        this.id = id;
        this.idfunc = idfunc;
        this.idserv = idserv;
        this.idproj = idproj;
    }
    public AssocProjServFunc (Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.idserv = cursor.getInt(1);
        this.idproj = cursor.getInt(2);
        this.idfunc = cursor.getInt(3);
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdfunc() {
        return idfunc;
    }

    public Integer getIdproj() {
        return idproj;
    }

    public Integer getIdserv() {
        return idserv;
    }
    public void insert(Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();

        values.put("CodServ", idserv);
        values.put("CodProj", idproj);
        values.put("CodFunc", idfunc);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("ASSOC_SERV_PROJ_FUNC",null, values);

    }

    public List<AssocProjServFunc> select(Context context)
    {
        SQLiteDatabase wv = Database.openFrom(context);
        Cursor cursor = wv.rawQuery("SELECT * FROM ASSOC_SERV_PROJ_FUNC", null);
        List<AssocProjServFunc> assocs = new ArrayList<>();

        while(cursor.moveToNext())
        {
            AssocProjServFunc assoc = new AssocProjServFunc(cursor);
            assocs.add(assoc);
        }
        cursor.close();
        return assocs;
    }
}
