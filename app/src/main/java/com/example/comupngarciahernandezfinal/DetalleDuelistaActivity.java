package com.example.comupngarciahernandezfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.comupngarciahernandezfinal.db.DbYuGiHo;
import com.example.comupngarciahernandezfinal.modelos.Duelistas;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalleDuelistaActivity extends AppCompatActivity implements OnMapReadyCallback {
    EditText etNombreDelDuelista;
    Button btnCrearCarta, btnVerCarta, Sincronisar;
    Duelistas duelista;
    int id = 0;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_duelista);

        etNombreDelDuelista = findViewById(R.id.etNombreDelDuelista);
        btnCrearCarta = findViewById(R.id.btnCrearCarta);
        btnVerCarta = findViewById(R.id.btnVerCarta);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = 0;
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = savedInstanceState.getInt("ID", 0);
        }

        DbYuGiHo dbYuGiHo = new DbYuGiHo(DetalleDuelistaActivity.this);
        duelista = dbYuGiHo.detalleDuelistas(id);
        if (duelista != null) {
            etNombreDelDuelista.setText(duelista.getNombre());
        }

        btnCrearCarta.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AgregarCartaActivity.class);
            intent.putExtra("cuentaId", id); // Pasa el ID de la cuenta seleccionada al crear movimiento
            startActivity(intent);
        });

        btnVerCarta.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListaCartasActivity.class);
            intent.putExtra("cuentaId", id); // Pasa el ID de la cuenta seleccionada a la actividad de lista de movimientos
            startActivity(intent);
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng marker1 = new LatLng(-7.1555073, -78.526508);


        // Agregar los marcadores al mapa
        map.addMarker(new MarkerOptions().position(marker1).title("Carta 1"));


        // Centrar la cámara en los marcadores
        map.moveCamera(CameraUpdateFactory.newLatLng(marker1));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
}