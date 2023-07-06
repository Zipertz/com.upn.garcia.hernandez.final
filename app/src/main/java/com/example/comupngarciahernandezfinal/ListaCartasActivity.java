package com.example.comupngarciahernandezfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.comupngarciahernandezfinal.adapter.CartaAdapter;
import com.example.comupngarciahernandezfinal.db.DbYuGiHo;
import com.example.comupngarciahernandezfinal.modelos.Carta;

import java.util.ArrayList;

public class ListaCartasActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartaAdapter cartaAdapter;
    private ArrayList<Carta> listaCartas;
    private DbYuGiHo dbYuGiHo;
    private int duelistaId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cartas);

        duelistaId = getIntent().getIntExtra("duelistaId", 0);

        dbYuGiHo = new DbYuGiHo(this);
        // Obtener la lista de movimientos por cuenta
        listaCartas = dbYuGiHo.obtenerCartasPorDuelista(duelistaId);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartaAdapter = new CartaAdapter(listaCartas);
        recyclerView.setAdapter(cartaAdapter);

        cartaAdapter.setOnItemClickListener(new CartaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Carta cartaSeleccionada = listaCartas.get(position);
                int cartaId = cartaSeleccionada.getId();
                Intent intent = new Intent(ListaCartasActivity.this, DetalleCartaActivity.class);
                intent.putExtra("cartaId", cartaId);
                startActivity(intent);
            }
        });

    }



}