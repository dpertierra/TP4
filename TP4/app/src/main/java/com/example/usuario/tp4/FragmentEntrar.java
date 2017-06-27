package com.example.usuario.tp4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Usuario on 8/6/2017.
 */

public class FragmentEntrar extends Fragment implements View.OnClickListener{
    Button entrar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle datosrecibidos) {
        View VistaaDevolver = inflater.inflate(R.layout.fragment_entrar_layout, container, false);
        entrar = (Button)VistaaDevolver.findViewById(R.id.btnentrar);
        entrar.setOnClickListener(this);
        return VistaaDevolver;
    }
    public void onClick(View v) {
        MainActivity Anfitriona= (MainActivity)getActivity();
        Anfitriona.CambiarFragment();
    }
}
