package com.example.usuario.tp4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Usuario on 9/6/2017.
 */

public class baseTP4SQLiteHelper extends SQLiteOpenHelper {

    public baseTP4SQLiteHelper(Context context, String Nombre, SQLiteDatabase.CursorFactory fabrica, int version)
    {
        super(context, Nombre, fabrica,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQLite","Declaro e inicializo la variable para crear la tabla Usuarios");
        String sqlCrearTablaUsuarios;
        sqlCrearTablaUsuarios= "create table Usuarios(User text, Pass text, Logged text)";
        Log.d("SQLite" , "Invoco creador de tabla");
        db.execSQL(sqlCrearTablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
