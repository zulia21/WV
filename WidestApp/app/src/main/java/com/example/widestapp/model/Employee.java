package com.example.widestapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;

import com.example.widestapp.activity.WidestDB;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Employee {

    private final Integer id;
    private final String name;
    private final boolean active;
    private final String password;
    private final String email;
    private final String function;
    private final String imageName;

    public Employee(Integer id, String name, boolean active, String password, String email, String function, String imageName) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.password = password;
        this.email = email;
        this.function = function;
        this.imageName = imageName;
    }

    public Employee(Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.active = cursor.getInt(2) != 0;
        this.password = cursor.getString(3);
        this.email = cursor.getString(4);
        this.function = cursor.getString(5);
        this.imageName = cursor.getString(6);

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFunction() {
        return function;
    }

    public String getImageName() {
        return imageName;
    }

    public void insert(Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);

        ContentValues values = new ContentValues();
        values.put("Nome", name);
        values.put("Ativo", active);
        values.put("Senha", password);
        values.put("Email", email);
        values.put("Cargo", function);
        values.put("RecursoImagem", imageName);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.insertOrThrow("Funcionario", null, values);
    }

    public static Employee authenticate(String email, String password, @NonNull Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);

        Cursor cursor = database.query(
                "Funcionario",
                null,
                    "Email = ? and Senha = ?",
                new String[]{ email, password },
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            return new Employee(cursor);
        }
        else {
            throw new NoSuchElementException("Employee with email and password [" +
                    email + "," + password + "] not found");
        }

    }
    public static List<Employee> select(@NonNull Context context)
     {
        SQLiteDatabase wv = Database.openFrom(context);
        Cursor cursor = wv.rawQuery("SELECT * FROM Funcionario;", null);
        List<Employee> employees = new ArrayList<>();
        while (cursor.moveToNext())
        {
            Employee employee = new Employee(cursor);
            employees.add(employee);

        }
        cursor.close();
        return employees;
    }



    public void update(Context context)
    {
        SQLiteDatabase database = Database.openFrom(context);

        ContentValues values = new ContentValues();
        values.put("Nome", name);
        values.put("Ativo", active);
        values.put("Senha", password);
        values.put("Email", email);
        values.put("Cargo", function);
        values.put("RecursoImagem", imageName);

        WidestDB wv = new WidestDB(context);
        wv.onCreate(database);
        database.update("Funcionario", values, "COD" + " = ?", new String[]{String.valueOf(id)});
    }


}
