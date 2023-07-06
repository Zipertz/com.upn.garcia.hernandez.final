package com.example.comupngarciahernandezfinal.serves;

import com.example.comupngarciahernandezfinal.modelos.Duelistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DuelistaService {

    @GET("Duelistas/")
    Call<List<Duelistas>> getContacts();

    @GET("Duelistas/{id}")
    Call<Duelistas> findContact(@Path("id") int id);

    @POST("Duelistas")
    Call<Duelistas> create(@Body Duelistas duelistas);

    @PUT("Duelistas/{id}")
    Call<Void> actualizar(@Path("id") int id, @Body Duelistas duelistas);


}
