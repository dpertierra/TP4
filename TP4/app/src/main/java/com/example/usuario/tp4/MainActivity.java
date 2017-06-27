package com.example.usuario.tp4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.inputmethod.InputContentInfo;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.annotation.ElementType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    String LogSQL;
    List<String> listusers = new ArrayList<>();
    List<String> listusersordalf = new ArrayList<>();
    List<String> listusersultlog = new ArrayList<>();
    FragmentTransaction transaction;
    baseTP4SQLiteHelper accesoabase;
    SQLiteDatabase basededatos;
    boolean responder;
    String lista;
    String listafechalog;
    String listaordalf;
    //DateFormat format = new SimpleDateFormat("dd, mmm, yyyy", Locale.FRENCH);
    //Date fechalogueo;
    String UserSQL;
    String PassSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        Fragment frgEntrar = new FragmentEntrar();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.holder, frgEntrar);
        transaction.commit();
        accesoabase = new baseTP4SQLiteHelper(this, "baseTP4", null, 1);

    }
    public void CambiarFragment()
    {
        manager = getSupportFragmentManager();
        Fragment frgIniciar = new FragmentIniciar();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.holder, frgIniciar);
        transaction.commit();
    }
    public void IraRegistrarse()
    {
        manager = getSupportFragmentManager();
        Fragment frgRegistrarse = new FragmentResgistrarse();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.holder, frgRegistrarse);
        transaction.commit();
    }
    public void registrarse(String Usuario, String Pass, String Repetirpass)
    {
        if (Pass.equals(Repetirpass))
        {
            inicializarbase();
            if (responder == true)
            {
                Cursor ConjuntoRegistros;
                ConjuntoRegistros = basededatos.rawQuery("select User from Usuarios where User='" + Usuario+"'", null);
                if(ConjuntoRegistros.moveToFirst() == true)
                {

                    do
                    {

                        String UserSQL = ConjuntoRegistros.getString(0);

                        if(UserSQL.equals(Usuario))
                        {
                            Toast.makeText(this, "El usuario ya existe por favor elija otro", Toast.LENGTH_LONG).show();

                        }
                    }while (ConjuntoRegistros.moveToNext()==true);



            }
            else {
                    boolean hasUppercase = !Pass.equals(Pass.toLowerCase());
                    boolean hasLowercase = !Pass.equals(Pass.toUpperCase());
                    if (hasUppercase == true && hasLowercase== true && Pass.length()>7)
                    {
                        ContentValues nuevoregistro;
                        nuevoregistro= new ContentValues();
                        nuevoregistro.put("User", Usuario);
                        nuevoregistro.put("Pass", Pass);
                        basededatos.insert("Usuarios", null, nuevoregistro);
                        manager = getSupportFragmentManager();
                        Fragment frgRegistrado = new FragmentRegistrado();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.holder, frgRegistrado);
                        transaction.commit();
                    }
                    else
                    {
                        Toast.makeText(this, "La contraseña no cumple con los requisitos", Toast.LENGTH_LONG).show();
                    }

        }

    }
            basededatos.close();
        }
        else if(Pass.equals(Repetirpass)==false)
        {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

    }

    public void irainiciar()
    {
        manager = getSupportFragmentManager();
        Fragment frgirainiciar = new FragmentIniciar();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.holder, frgirainiciar);
        transaction.commit();
    }
    public void volver()
    {
        manager = getSupportFragmentManager();
        Fragment volver = new FragmentEntrar();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.holder, volver);
        transaction.commit();
    }



    public boolean inicializarbase()
    {
        basededatos = accesoabase.getWritableDatabase();
        if (basededatos!=null)
        {
            responder = true;
        }
        else {
            responder = false;
        }
        return responder;
    }
    public void iniciarsesion(String user, String pass)
    {
        inicializarbase();
        if (responder ==true)
        {
            Cursor ConjuntoRegistros;
            ConjuntoRegistros = basededatos.rawQuery("SELECT User, Pass from Usuarios WHERE User='" + user+"'and Pass='"+pass+"'", null);
            if(ConjuntoRegistros.moveToFirst() == true)
            {

                do
                {

                    String UserSQL = ConjuntoRegistros.getString(0);
                    String PassSQL = ConjuntoRegistros.getString(1);
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    Date date = new Date();
                    if(UserSQL.equals(user) && PassSQL.equals(pass))
                    {
                        manager = getSupportFragmentManager();
                        Fragment iniciado = new FragmentIniciado();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.holder, iniciado);
                        transaction.commit();
                        basededatos.execSQL("UPDATE Usuarios SET Logged='" +dateFormat.format(date).toString() + "' where User='" +user+ "'");
                    }
                }while (ConjuntoRegistros.moveToNext()==true);

            }
            else
                {
                    Toast.makeText(this,"El usuario o la contraseña son incorrectos", Toast.LENGTH_LONG).show();
                }
        }
        basededatos.close();
    }

}

