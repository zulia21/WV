package com.example.widestapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.widestapp.activity.WidestDB;

public abstract class Database {
    public static SQLiteDatabase openFrom(@NonNull Context context)
    {
        return context.openOrCreateDatabase(WidestDB.WV_BANCO, Context.MODE_PRIVATE, null);
    }
    public static void resetDatabase(Context context)
    {
        context.deleteDatabase(WidestDB.WV_BANCO);
    }
}
