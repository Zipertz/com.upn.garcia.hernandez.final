package com.example.comupngarciahernandezfinal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "yuGiHo.db";
    public static final String TABLE_DUELISTA = "duelista";
    public static final String TABLE_CARTAS = "cartas";
    public static final String COLUMN_DUELISTA_ID = "duelistaId";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_MOUNSTRO = "mounstro";
    public static final String COLUMN_ATAQUE = "ataque";
    public static final String COLUMN_DEFENSA = "defensa";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LATITUD = "latitud";
    public static final String COLUMN_LONGITUD = "longitud";
    public static final String COLUMN_IMAGE = "imagen";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createDuelistasTableQuery = "CREATE TABLE " + TABLE_DUELISTA + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT)";
        sqLiteDatabase.execSQL(createDuelistasTableQuery);

        String createCartasTableQuery = "CREATE TABLE " + TABLE_CARTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "duelistaId INTEGER," +
                COLUMN_MOUNSTRO + " TEXT," +
                COLUMN_ATAQUE + " TEXT," +
                COLUMN_DEFENSA + " TEXT," +
                COLUMN_LATITUD + " REAL," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_LONGITUD + " REAL," +
                "FOREIGN KEY(" + COLUMN_DUELISTA_ID + ") REFERENCES " + TABLE_DUELISTA + "(id))";
        sqLiteDatabase.execSQL(createCartasTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARTAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DUELISTA);
        onCreate(sqLiteDatabase);
    }
}
