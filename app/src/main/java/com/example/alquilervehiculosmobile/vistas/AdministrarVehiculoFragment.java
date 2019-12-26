package com.example.alquilervehiculosmobile.vistas;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alquilervehiculosmobile.R;
import com.example.alquilervehiculosmobile.modelo.Vehiculo;
import com.example.alquilervehiculosmobile.servicios.Endpoint;
import com.example.alquilervehiculosmobile.servicios.ServicioVehiculo;
import com.example.alquilervehiculosmobile.servicios.StatusResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdministrarVehiculoFragment extends Fragment {

    private EditText placa;
    private EditText modelo;
    private EditText marca;
    private EditText color;
    private EditText precio;
    private Button guardar;
    private Button buscar;
    private ProgressDialog progressDialog;

    private ServicioVehiculo servicioVehiculo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_vehiculo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placa = view.findViewById(R.id.placa);
        modelo = view.findViewById(R.id.modelo);
        marca = view.findViewById(R.id.marca);
        color = view.findViewById(R.id.color);
        precio = view.findViewById(R.id.precio);
        guardar = view.findViewById(R.id.guardar);
        buscar = view.findViewById(R.id.buscar);

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioVehiculo = retrofit.create(ServicioVehiculo.class);

        guardar();
        buscar();
    }

    private void guardar() {
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_registrando));
                progressDialog.show();

                String placaVehiculo = placa.getText().toString();
                String modeloVehiculo = modelo.getText().toString();
                String marcaVehiculo = marca.getText().toString();
                String colorVehiculo = color.getText().toString();
                double precioVehiculo = Double.parseDouble(precio.getText().toString());

                Vehiculo vehiculo = new Vehiculo(placaVehiculo, modeloVehiculo, marcaVehiculo, colorVehiculo, precioVehiculo);

                servicioVehiculo.registrar(vehiculo).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        progressDialog.dismiss();
                        if (response.code() == StatusResponse.OK) {
                            limpiarCamposPantalla();
                            Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_registrado), Toast.LENGTH_SHORT).show();
                        } else {
                            errorRespuesta(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressDialog.dismiss();
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    private void buscar() {
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_buscando));
                progressDialog.show();

                String placaVehiculo = placa.getText().toString();

                servicioVehiculo.buscar(placaVehiculo).enqueue(new Callback<Vehiculo>() {
                    @Override
                    public void onResponse(Call<Vehiculo> call, Response<Vehiculo> response) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            modelo.setText(response.body().getModelo());
                            marca.setText(response.body().getMarca());
                            color.setText(response.body().getColor());
                            precio.setText(String.valueOf(response.body().getPrecio()));
                        } else {
                            Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_no_encontrado), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Vehiculo> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_no_encontrado), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void errorRespuesta(ResponseBody errorBody) {
        try {
            JSONObject jsonObject = new JSONObject(errorBody.string());
            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarCamposPantalla() {
        placa.setText("");
        modelo.setText("");
        marca.setText("");
        color.setText("");
        precio.setText("");
    }
}
