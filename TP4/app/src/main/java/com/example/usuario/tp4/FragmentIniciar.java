package com.example.usuario.tp4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Usuario on 8/6/2017.
 */

public class FragmentIniciar extends Fragment implements View.OnClickListener{
    Button btniniciar;
    Button btnregistrarse;
    EditText edtuser;
    EditText edtpass;
    MainActivity anfitriona;
    @Override
    public void onClick(View v) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle datosrecibidos) {
        View VistaaDevolver = inflater.inflate(R.layout.fragment_iniciar_layout, container, false);
        anfitriona = (MainActivity)getActivity();
        btniniciar = (Button)VistaaDevolver.findViewById(R.id.btniniciar);
        btnregistrarse = (Button)VistaaDevolver.findViewById(R.id.btniraregistrarse);
        edtuser = (EditText) VistaaDevolver.findViewById(R.id.txtuser);
        edtpass = (EditText)VistaaDevolver.findViewById(R.id.txtpass);

        btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anfitriona.iniciarsesion(edtuser.getText().toString(), edtpass.getText().toString());
                edtuser.setText("");
                edtpass.setText("");
                edtuser.requestFocus();

            }
        });
        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anfitriona = (MainActivity)getActivity();
                anfitriona.IraRegistrarse();
            }
        });
        return VistaaDevolver;
    }
}
