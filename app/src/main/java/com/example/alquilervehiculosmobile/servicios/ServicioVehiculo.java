package com.example.alquilervehiculosmobile.servicios;

import com.example.alquilervehiculosmobile.modelo.Vehiculo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicioVehiculo {

    @POST("vehiculo")
    Call<Void> registrar(@Body Vehiculo vehiculo);

    @GET("vehiculo/{PLACA}")
    Call<Vehiculo> buscar(@Path("PLACA") String placa);
}
