package com.example.comupngarciahernandezfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.comupngarciahernandezfinal.db.DbYuGiHo;
import com.example.comupngarciahernandezfinal.modelos.Carta;

public class DetalleCartaActivity extends AppCompatActivity {
    private EditText tvNombreCarta;
    private EditText tvAtaque;
    private EditText tvDefensa;
    private EditText tvLatitud;
    private EditText tvLongitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carta);

        tvNombreCarta = findViewById(R.id.tvNombreCarta);
        tvAtaque = findViewById(R.id.tvAtaque);
        tvDefensa = findViewById(R.id.tvDefensa);

        // Obtener los datos de la carta enviados desde la actividad anterior
        Intent intent = getIntent();
        if (intent != null) {
            int cartaId = intent.getIntExtra("cartaId", 0);

            // Obtener los detalles de la carta desde la base de datos o cualquier otra fuente de datos
            Carta carta = obtenerDetalleCarta(cartaId);
            if (carta != null) {
                // Mostrar los detalles de la carta en los TextView correspondientes
                tvNombreCarta.setText(carta.getNombre());
                tvAtaque.setText("Ataque: " + carta.getAtaque());
                tvDefensa.setText("Defensa: " + carta.getDefensa());
                tvAtaque.setText("Longitud: " + carta.getAtaque());
                tvDefensa.setText("Latitud: " + carta.getDefensa());
            }
        }
    }

    private Carta obtenerDetalleCarta(int cartaId) {
        // Obtener los detalles de la carta desde la base de datos o cualquier otra fuente de datos
        // Puedes adaptar este método según cómo obtengas los detalles de la carta

        // Ejemplo:
        DbYuGiHo dbYuGiHo = new DbYuGiHo(DetalleCartaActivity.this);
        return dbYuGiHo.obtenerDetalleCarta(cartaId);
    }

    }
}