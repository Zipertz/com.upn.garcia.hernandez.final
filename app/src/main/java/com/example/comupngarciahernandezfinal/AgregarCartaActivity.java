package com.example.comupngarciahernandezfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comupngarciahernandezfinal.db.DbYuGiHo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarCartaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, LocationListener {
    EditText etLatitud, etLongitud, etMounstro, etAtaque, etDefensa,etImagen;
    Button btnAgregarCarta;
    int duelistaId;
    GoogleMap mMap;
    private static final int OPEN_GALLERY_REQUEST = 1002;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carta);

        etMounstro = findViewById(R.id.etMounstro);
        etAtaque = findViewById(R.id.etAtaque);
        etDefensa = findViewById(R.id.etDefensa);
        etImagen = findViewById(R.id.etImagen);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
        btnAgregarCarta = findViewById(R.id.btnAgregarCartas);
        Button btnGaleria = findViewById(R.id.btnGaleria);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (
                checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            requestPermissions(permissions, 3000);

        } else {
            // configurar frecuencia de actualización de GPS
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1, this);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }


        duelistaId = getIntent().getIntExtra("duelistaId", 0); // Obtener el ID de la duelista seleccionada
        btnAgregarCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores del formulario
                String mounstro = etMounstro.getText().toString();
                String ataque = etAtaque.getText().toString();
                String defensa = etDefensa.getText().toString();
                String imagen = etImagen.getText().toString();
                double latitud = Double.parseDouble(etLatitud.getText().toString());
                double longitud = Double.parseDouble(etLongitud.getText().toString());

                // Llamar al método para insertar el carta en la base de datos
                DbYuGiHo dbYuGiHo = new DbYuGiHo(AgregarCartaActivity.this);
                long cartaId = dbYuGiHo.insertaCartas(duelistaId, mounstro, ataque, defensa,imagen, latitud, longitud);
                //insertaMovimiento(cuentaId, tipoMovimiento, monto, motivo, latitud, longitud);

                // Verificar si la inserción del movimiento fue exitosa
                if (cartaId != -1) {
                    Toast.makeText(AgregarCartaActivity.this, "Carta creada", Toast.LENGTH_SHORT).show();
                    limpiarFormulario();
                } else {
                    Toast.makeText(AgregarCartaActivity.this, "Error al crear el Carta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                else {
                    String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 2000);
                }
            }
        });


    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

           Log.i("ImagenSIUUUU",picturePath);
            etImagen.setText(String.valueOf(picturePath));

        }
    }

    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    private void limpiarFormulario() {
        etMounstro.setText("");
        etAtaque.setText("");
        etDefensa.setText("");
        etLatitud.setText("");
        etLongitud.setText("");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        showCurrentLocation();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        etLatitud.setText(String.valueOf(latLng.latitude));
        etLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        etLatitud.setText(String.valueOf(latLng.latitude));
        etLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        Log.i("MAIN_APP: Location - ",  "Latitude: " + latitude);
        Log.i("MAIN_APP: Location - ",  "Longitude: " + longitude);
    }

    private void showCurrentLocation() {
        // Verificar si los permisos de ubicación están disponibles
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Obtener la última ubicación conocida del dispositivo
            FusedLocationProviderClient fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(this);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            // Obtener la latitud y longitud
                            LatLng currentLatLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());

                            // Mover la cámara del mapa a la ubicación actual
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                            mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Marker"));
                            etLatitud.setText(String.valueOf(currentLatLng.latitude));
                            etLongitud.setText(String.valueOf(currentLatLng.longitude));
                        }
                    });
        }
    }
}

