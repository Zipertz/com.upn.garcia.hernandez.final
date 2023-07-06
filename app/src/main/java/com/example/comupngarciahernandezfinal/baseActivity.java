package com.example.comupngarciahernandezfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.comupngarciahernandezfinal.db.DbHelper;

public class baseActivity extends AppCompatActivity {
    Button btnCrear,btnCrearDuelista,btnListarDuelistas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        btnCrear = findViewById(R.id.btnCrear);
        btnCrearDuelista = findViewById(R.id.btnCrearDuelista);
        btnListarDuelistas = findViewById(R.id.btnListarDuelistas);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(baseActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(baseActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(baseActivity.this, "ERRORAL CREAR BASE DE DATOS ", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCrearDuelista.setOnClickListener(view -> {


            Intent intent = new Intent(getApplicationContext(), CreaDuelistaActivity.class);
            startActivity(intent);

        });

        btnListarDuelistas.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), ListaDuelistasActivity.class);
            startActivity(intent);

        });
    }
}