package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;

public class FotosProjeto {
    private final Integer id;
    private final String caminhoFoto;
    private final Integer idprojeto;

    public FotosProjeto(Integer id, String caminhoFoto, Integer idprojeto) {
        this.id = id;
        this.caminhoFoto = caminhoFoto;
        this.idprojeto = idprojeto;
    }
    public FotosProjeto (Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.caminhoFoto = cursor.getString(1);
        this.idprojeto = cursor.getInt(2);
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdprojeto() {
        return idprojeto;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void insert(Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);
        ContentValues values = new ContentValues();

        values.put("CodProjeto", idprojeto);
        values.put("Caminho", caminhoFoto);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("FotosProjeto", null, values);

    }
    public static List<FotosProjeto> select(Context context)
    {
        SQLiteDatabase wv = Database.openFrom(context);

        Cursor cursor = wv.rawQuery("SELECT * FROM FotosProjeto", null);
        List<FotosProjeto> fotosProjeto = new ArrayList<>();

        while (cursor.moveToNext())
        {
            FotosProjeto fotoP = new FotosProjeto(cursor);
            fotosProjeto.add(fotoP);
        }

        cursor.close();
        return fotosProjeto;

    }
}
