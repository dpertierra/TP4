package com.example.usuario.tp4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Usuario on 9/6/2017.
 */

public class FragmentRegistrado extends Fragment implements View.OnClickListener{
    Button btnirainiciar;
    MainActivity anfitriona;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle datos) {
        View VistaaDevolver = inflater.inflate(R.layout.fragment_registrado_layout, container, false);
        btnirainiciar = (Button)VistaaDevolver.findViewById(R.id.irainiciar);
        btnirainiciar.setOnClickListener(this);
        return VistaaDevolver;

    }

    @Override
    public void onClick(View v) {
        anfitriona = (MainActivity)getActivity();
        anfitriona.irainiciar();

    }
}
