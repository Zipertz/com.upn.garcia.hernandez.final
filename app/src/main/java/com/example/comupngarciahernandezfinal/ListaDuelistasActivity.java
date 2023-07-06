package com.example.comupngarciahernandezfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.comupngarciahernandezfinal.adapter.DuelistaAdapter;
import com.example.comupngarciahernandezfinal.db.DbYuGiHo;
import com.example.comupngarciahernandezfinal.modelos.Duelistas;

import java.util.ArrayList;

public class ListaDuelistasActivity extends AppCompatActivity {
    RecyclerView listaDuelistas;
    ArrayList<Duelistas> listaArrayDuelistas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_duelistas);

        listaDuelistas = findViewById(R.id.rvListaCuentas);
        listaDuelistas.setLayoutManager(new LinearLayoutManager(this));
        DbYuGiHo dbYuGiHo = new DbYuGiHo(ListaDuelistasActivity.this);
        listaArrayDuelistas = new ArrayList<>();


        DuelistaAdapter adapter = new DuelistaAdapter(dbYuGiHo.mostrarDuelistas());
        listaDuelistas.setAdapter(adapter);
    }
}