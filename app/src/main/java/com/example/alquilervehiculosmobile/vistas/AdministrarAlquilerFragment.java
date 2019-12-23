package com.example.alquilervehiculosmobile.vistas;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.alquilervehiculosmobile.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdministrarAlquilerFragment extends Fragment {

    private EditText fechaInicio;
    private EditText fechaFin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_alquiler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fechaInicio = view.findViewById(R.id.fechaInicio);
        fechaFin = view.findViewById(R.id.fechaFin);

        fechaInicio.setFocusable(false);
        fechaFin.setFocusable(false);

        fechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFecha(fechaInicio);
            }
        });

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFecha(fechaFin);
            }
        });
    }

    /**
     * llena el campo con la fecha seleccionada
     *
     * @param input el input a llenar
     */
    public void setFecha(final EditText input) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String dia = String.valueOf(dayOfMonth);
                String mes = String.valueOf((monthOfYear + 1));
                if (dayOfMonth < 10) {
                    dia = "0" + dayOfMonth;
                }
                if ((monthOfYear + 1) < 10) {
                    mes = "0" + (monthOfYear + 1);
                }
                input.setText(dia + "-" + mes + "-" + year);
            }
        }, 0, 0, 2019);
        Calendar fechaHoy = new GregorianCalendar();
        datePickerDialog.updateDate(fechaHoy.get(Calendar.YEAR), fechaHoy.get(Calendar.MONTH), fechaHoy.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
