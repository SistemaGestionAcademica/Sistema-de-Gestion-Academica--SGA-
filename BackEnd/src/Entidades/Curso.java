/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author david
 */
public class Curso {
    String codigo;
    Ciclo codigo_ciclo;
    Carrera codigo_carrera;
    String nombre;
    int creditos;
    String horas;
    
//    public Curso() {
//    }
//
//    public Curso(String codigo, String codigo_ciclo, String codigo_carrera, String nombre, int creditos, String horas) {
//        this.codigo = codigo;
//        this.codigo_ciclo = codigo_ciclo;
//        this.codigo_carrera = codigo_carrera;
//        this.nombre = nombre;
//        this.creditos = creditos;
//        this.horas = horas;
//    }
//
//    public String getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(String codigo) {
//        this.codigo = codigo;
//    }
//
//    public String getCodigo_ciclo() {
//        return codigo_ciclo;
//    }
//
//    public void setCodigo_ciclo(String codigo_ciclo) {
//        this.codigo_ciclo = codigo_ciclo;
//    }
//
//    public String getCodigo_carrera() {
//        return codigo_carrera;
//    }
//
//    public void setCodigo_carrera(String codigo_carrera) {
//        this.codigo_carrera = codigo_carrera;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public int getCreditos() {
//        return creditos;
//    }
//
//    public void setCreditos(int creditos) {
//        this.creditos = creditos;
//    }
//
//    public String getHoras() {
//        return horas;
//    }
//
//    public void setHoras(String horas) {
//        this.horas = horas;
//    }

    
    public Curso() {
    }

    public Curso(String codigo, Ciclo codigo_ciclo, Carrera codigo_carrera, String nombre, int creditos, String horas) {
        this.codigo = codigo;
        this.codigo_ciclo = codigo_ciclo;
        this.codigo_carrera = codigo_carrera;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas = horas;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Ciclo getCodigo_ciclo() {
        return codigo_ciclo;
    }

    public void setCodigo_ciclo(Ciclo codigo_ciclo) {
        this.codigo_ciclo = codigo_ciclo;
    }
    
    public String getCodigo_ciclo2() {
        return codigo_ciclo.getCodigo();
    }

    public void setCodigo_ciclo2(String codigo_ciclo) {
        this.codigo_ciclo.setCodigo(codigo_ciclo);
    }

    public Carrera getCodigo_carrera() {
        return codigo_carrera;
    }

    public void setCodigo_carrera(Carrera codigo_carrera) {
        this.codigo_carrera = codigo_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
    
//    @Override
//    public String toString() {
//        return "Curso{" + "codigo=" + codigo + ", codigo_ciclo=" + codigo_ciclo + ", codigo_carrera=" + codigo_carrera + ", nombre=" + nombre + ", creditos=" + creditos + ", horas=" + horas + '}';
//    }
    
    @Override
    public String toString() {
        return nombre;
  }

//    public Curso() {
//    }
//
//    public Curso(String codigo, char codigo_ciclo, char codigo_carrera, String nombre, int creditos, String horas) {
//        this.codigo = codigo;
//        this.codigo_ciclo = codigo_ciclo;
//        this.codigo_carrera = codigo_carrera;
//        this.nombre = nombre;
//        this.creditos = creditos;
//        this.horas = horas;
//    }
//
//    public String getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(String codigo) {
//        this.codigo = codigo;
//    }
//
//    public char getCodigo_ciclo() {
//        return codigo_ciclo;
//    }
//
//    public void setCodigo_ciclo(char codigo_ciclo) {
//        this.codigo_ciclo = codigo_ciclo;
//    }
//
//    public char getCodigo_carrera() {
//        return codigo_carrera;
//    }
//
//    public void setCodigo_carrera(char codigo_carrera) {
//        this.codigo_carrera = codigo_carrera;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public int getCreditos() {
//        return creditos;
//    }
//
//    public void setCreditos(int creditos) {
//        this.creditos = creditos;
//    }
//
//    public String getHoras() {
//        return horas;
//    }
//
//    public void setHoras(String horas) {
//        this.horas = horas;
//    }
//
//    @Override
//    public String toString() {
//        return "Curso{" + "codigo=" + codigo + ", codigo_ciclo=" + codigo_ciclo + ", codigo_carrera=" + codigo_carrera + ", nombre=" + nombre + ", creditos=" + creditos + ", horas=" + horas + '}';
//    }
//    

    

}
