package com.example.alquilervehiculosmobile.servicios;

import com.example.alquilervehiculosmobile.modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicioUsuario {

    @POST("usuario")
    Call<Void> registrar(@Body Usuario vehiculo);

    @GET("usuario/{CEDULA}")
    Call<Usuario> buscar(@Path("CEDULA") Long cedula);
}
