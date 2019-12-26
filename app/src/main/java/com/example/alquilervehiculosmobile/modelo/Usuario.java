package com.example.alquilervehiculosmobile.modelo;

public class Usuario {

    private Long cedula;

    private String nombres;

    private String apellidos;

    private String fechaNacimiento;

    public Usuario() {
    }

    public Usuario(Long cedula, String nombres, String apellidos, String fechaNacimiento) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}
