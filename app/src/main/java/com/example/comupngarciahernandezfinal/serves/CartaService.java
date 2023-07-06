package com.example.comupngarciahernandezfinal.serves;

import com.example.comupngarciahernandezfinal.modelos.Carta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartaService {
    @GET("Carta/")
    Call<List<Carta>> getContacts();

    @GET("Carta/{id}")
    Call<Carta> findContact(@Path("id") int id);

    @POST("Carta")
    Call<Carta> create(@Body Carta carta);

    @PUT("Carta/{id}")
    Call<Void> actualizar(@Path("id") int id, @Body Carta carta);


}
