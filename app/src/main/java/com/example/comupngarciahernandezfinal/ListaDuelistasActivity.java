package com.example.comupngarciahernandezfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListaDuelistasActivity extends AppCompatActivity {
    RecyclerView listaDuelistas;
    ArrayList<Cuentas> listaArrayCuentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_duelistas);
    }
}