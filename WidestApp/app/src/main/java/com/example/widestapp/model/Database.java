package com.example.widestapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

public abstract class Database {
    public static SQLiteDatabase openFrom(@NonNull Context context)
    {
        return context.openOrCreateDatabase("WidestAppDB", Context.MODE_PRIVATE, null);
    }
}
