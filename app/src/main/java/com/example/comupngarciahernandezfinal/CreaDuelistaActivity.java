package com.example.comupngarciahernandezfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comupngarciahernandezfinal.db.DbYuGiHo;

public class CreaDuelistaActivity extends AppCompatActivity {
    EditText etNombreDuelista;
    Button btnCrearDuelista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_duelista);
        etNombreDuelista = findViewById(R.id.etNombreDuelista);
        btnCrearDuelista = findViewById(R.id.btnCrearDuelista);


        btnCrearDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbYuGiHo dbYuGiHo = new DbYuGiHo(CreaDuelistaActivity.this);
                long id=dbYuGiHo.insertaDuelista(etNombreDuelista.getText().toString());
                if (id>0){
                    Toast.makeText(CreaDuelistaActivity.this, "Dato Creado", Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(CreaDuelistaActivity.this, "Error al Guardar ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void limpiar(){
        etNombreDuelista.setText("");
    }
}