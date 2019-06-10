/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Steven Villalobos
 */
public class Alumno {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private Date fecha;

    public Alumno() {
        this.cedula = "";
        this.nombre = "";
        this.telefono = "";
        this.email = "";
        this.fecha = new Date(Calendar.getInstance().getTime().getTime());
    }

    public Alumno(String cedula, String nombre, String telefono, String email, Date fecha) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fecha = fecha;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
