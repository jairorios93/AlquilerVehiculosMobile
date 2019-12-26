package com.example.alquilervehiculosmobile.servicios;

import com.example.alquilervehiculosmobile.modelo.AlquilarVehiculo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicioAlquiler {

    @POST("alquiler")
    Call<Void> alquilar(@Body AlquilarVehiculo alquilarVehiculo);

    @GET("alquiler/{PLACA}")
    Call<Void> devolver(@Path("PLACA") String placa);
}
