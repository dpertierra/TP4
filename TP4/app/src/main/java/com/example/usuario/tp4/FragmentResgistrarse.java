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
 * Created by Usuario on 9/6/2017.
 */

public class FragmentResgistrarse extends Fragment implements View.OnClickListener{
    EditText edtuser;
    EditText edtpass;
    EditText edtreingresopass;
    Button btnregistrase;
    MainActivity anfitriona;
    @Override
    public void onClick(View v) {
        anfitriona = (MainActivity)getActivity();
        anfitriona.registrarse(edtuser.getText().toString(), edtpass.getText().toString(), edtreingresopass.getText().toString());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle datosrecibidos) {
        View VistaaDevolver = inflater.inflate(R.layout.fragment_registro_layout, container, false);
        edtuser = (EditText)VistaaDevolver.findViewById(R.id.txtcreausuario);
        edtpass = (EditText)VistaaDevolver.findViewById(R.id.txtcreapass);
        edtreingresopass = (EditText)VistaaDevolver.findViewById(R.id.txtreingresapass);
        btnregistrase = (Button)VistaaDevolver.findViewById(R.id.btnregistrarse);
        btnregistrase.setOnClickListener(this);
        return VistaaDevolver;
    }
}
