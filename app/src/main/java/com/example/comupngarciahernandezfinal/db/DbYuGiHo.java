package com.example.comupngarciahernandezfinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.comupngarciahernandezfinal.modelos.Carta;
import com.example.comupngarciahernandezfinal.modelos.Duelistas;

import java.util.ArrayList;

public class DbYuGiHo extends DbHelper {
    Context context;

    public DbYuGiHo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public  long insertaDuelista(String nombre){
        long id=0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Nombre", nombre);

            id = db.insert(TABLE_DUELISTA,null,values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Duelistas> mostrarDuelistas(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Duelistas> listaDuelistas = new ArrayList<>();
        Duelistas duelista = null;
        Cursor cursorDuelistas = null;
        cursorDuelistas = db.rawQuery("SELECT * FROM " + TABLE_DUELISTA,null);
        if (cursorDuelistas.moveToFirst()) {
            do {
                duelista = new Duelistas();
                duelista.setId(cursorDuelistas.getInt(0));
                duelista.setNombre(cursorDuelistas.getString(1));
                listaDuelistas.add(duelista);

            }while (cursorDuelistas.moveToNext());
        }
        cursorDuelistas.close();
        return  listaDuelistas;
    }

    public Duelistas detalleDuelistas(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Duelistas duelista = null;
        Cursor cursorDuelistas = null;
        cursorDuelistas = db.rawQuery("SELECT * FROM " + TABLE_DUELISTA + " WHERE id =" + id + " LIMIT 1",null);
        if (cursorDuelistas.moveToFirst()) {

            duelista = new Duelistas();
            duelista.setId(cursorDuelistas.getInt(0));
            duelista.setNombre(cursorDuelistas.getString(1));



        }
        cursorDuelistas.close();
        return  duelista;
    }


    public long insertaCartas(int duelistaId, String mounstro, String ataque,String defensa,String imagen, double latitud, double longitud) {
        long cartaId = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("duelistaId", duelistaId);

            values.put("mounstro", mounstro);
            values.put("ataque", ataque);
            values.put("defensa", defensa);
            values.put("imagen", imagen);
            values.put("latitud", latitud);
            values.put("longitud", longitud);

            cartaId = db.insert(TABLE_CARTAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return cartaId;
    }

    public ArrayList<Carta> obtenerCartasPorDuelista(int duelistaId) {
        ArrayList<Carta> listaCartas = new ArrayList<>();

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CARTAS + " WHERE duelistaId = ?", new String[]{String.valueOf(duelistaId)});

            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int mounstroIndex = cursor.getColumnIndex(COLUMN_MOUNSTRO);
            int ataqueIndex = cursor.getColumnIndex(COLUMN_ATAQUE);
            int defensaIndex = cursor.getColumnIndex(COLUMN_DEFENSA);
            int imagenIndex = cursor.getColumnIndex(COLUMN_IMAGE);
            int latitudIndex = cursor.getColumnIndex(COLUMN_LATITUD);
            int longitudIndex = cursor.getColumnIndex(COLUMN_LONGITUD);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String mounstro = cursor.getString(mounstroIndex);
                String ataque = cursor.getString(ataqueIndex);
                String defensa = cursor.getString(defensaIndex);
                String imagen = cursor.getString(imagenIndex);
                double latitud = cursor.getDouble(latitudIndex);
                double longitud = cursor.getDouble(longitudIndex);

                Carta carta = new Carta(id, mounstro, ataque, defensa,imagen,latitud, longitud);
                listaCartas.add(carta);
            }

            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaCartas;

    }

    public Carta obtenerDetalleCarta(int cartaId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                COLUMN_MOUNSTRO,
                COLUMN_ATAQUE,
                COLUMN_DEFENSA,
                COLUMN_IMAGE,
                COLUMN_LATITUD,
                COLUMN_LONGITUD
        };
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(cartaId)};
        Cursor cursor = db.query(TABLE_CARTAS, projection, selection, selectionArgs, null, null, null);
        Carta carta = null;
        if (cursor != null && cursor.moveToFirst()) {
            String mounstro = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOUNSTRO));
            String ataque = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ATAQUE));
            String defensa = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEFENSA));
            String imagen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
            String latitud = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LATITUD));
            String longitud = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LONGITUD));


            carta = new Carta(cartaId, mounstro, ataque, defensa,imagen,Double.parseDouble(latitud),Double.parseDouble(longitud));
            cursor.close();
        }
        return carta;
    }

}