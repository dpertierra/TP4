package com.example.usuario.tp4;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Usuario on 9/6/2017.
 */

public class FragmentIniciado extends Fragment implements View.OnClickListener {
    Button btnvolver;
    Button btnordenaralf;
    Button btnordenarfecha;
    TextView textView;
    MainActivity anfitriona;
    ArrayAdapter<String> adapternormal;
    ArrayAdapter<String> adapteralf;
    ArrayAdapter<String> adapterlog;
    baseTP4SQLiteHelper accesoabase;
    SQLiteDatabase basededatos;
    boolean responder;
    @Override
    public void onClick(View v) {

        anfitriona.volver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle datosrecibidos) {
        View VistaaDevolver = inflater.inflate(R.layout.fragment_iniciado_layout, container, false);
        anfitriona = (MainActivity)getActivity();
        final Spinner spinner;
        accesoabase = new baseTP4SQLiteHelper(getContext(), "baseTP4", null, 1);
        spinner =(Spinner)VistaaDevolver.findViewById(R.id.listarusuarios);
        btnvolver = (Button)VistaaDevolver.findViewById(R.id.btnvolveralinicio);
        textView = (TextView) VistaaDevolver.findViewById(R.id.labelusuarios);
        btnordenaralf = (Button)VistaaDevolver.findViewById(R.id.btnordenaralf);
        btnordenarfecha = (Button)VistaaDevolver.findViewById(R.id.btnordenarporlogueo);
        btnvolver.setOnClickListener(this);
        final ArrayList<String> itemsByLogin;
        itemsByLogin = getUltimoLogin();
        updatelogin();
        btnordenarfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnordenaralf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                adapteralf = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getorderbyalf());
                adapteralf.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapteralf);

            }
        });
        btnordenarfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterlog = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, itemsByLogin);
                adapterlog.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapterlog);
            }
        });
        adapternormal = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mostrarusuarios());
        adapternormal.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapternormal);
        return VistaaDevolver;
    }
    boolean connectToDatabase() {
        basededatos = accesoabase.getWritableDatabase();
        if (basededatos != null) {
            return true;
        }
        return false;
    }
    ArrayList<String> getUltimoLogin(){
        ArrayList<String> ultlogin=new ArrayList<>();

        if(connectToDatabase()) {

            Cursor rows;
            rows = basededatos.rawQuery("select User, Logged from Usuarios order by Logged desc", null);

            if (rows.moveToFirst()) {
                do {
                    ultlogin.add(rows.getString(0) + " | último inicio de sesión: " + rows.getString(1));
                } while (rows.moveToNext());
            }
            basededatos.close();
        }

        return ultlogin;
    }
    ArrayList<String>getorderbyalf(){
        ArrayList<String> orderalf=new ArrayList<>();
        if(connectToDatabase()) {
            Cursor rows;
            rows = basededatos.rawQuery("select User from usuarios order by User asc", null);

            if (rows.moveToFirst()) {
                do {
                    orderalf.add(rows.getString(0));
                } while (rows.moveToNext());
            }
            basededatos.close();

        }
        return orderalf;
    }
    ArrayList<String> mostrarusuarios() {
        ArrayList<String> users = new ArrayList<>();
        if (connectToDatabase()) {
            Cursor cursor = basededatos.rawQuery("SELECT User from Usuarios ", null);
            if (cursor.moveToFirst()) {
                do {
                    users.add(cursor.getString(0));
                } while (cursor.moveToNext());


            }
            basededatos.close();
        }
        return users;
    }
    public void updatelogin()
    {
        if(connectToDatabase()) {
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
            Date date = new Date();
            basededatos.execSQL("UPDATE Usuarios SET Logged='" +dateFormat.format(date).toString() + "' where User='" +anfitriona.UserSQL+ "'");
        }
        basededatos.close();

    }

}
