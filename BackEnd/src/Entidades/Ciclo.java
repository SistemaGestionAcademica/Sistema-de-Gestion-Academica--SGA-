/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author david
 */
public class Ciclo {
    private String codigo;
    private String ano;
    private char estado;
    private char numero;
    private Date fecha_inicio;
    private Date fecha_finalizacion;

    public Ciclo() {
        this.codigo = "";
        this.ano = "";
        this.estado = ' ';
        this.numero = ' ';
        this.fecha_inicio = null;
        this.fecha_finalizacion = null;
    }

    public Ciclo(String codigo, String ano, char estado, char numero, Date fecha_inicio, Date fecha_finalizacion) {
        this.codigo = codigo;
        this.ano = ano;
        this.estado = estado;
        this.numero = numero;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizacion = fecha_finalizacion;
    }

 
     
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public char getNumero() {
        return numero;
    }

    public void setNumero(char numero) {
        this.numero = numero;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    

    //@Override
    public String toString() {
        return codigo;
    }
    
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ciclo other = (Ciclo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

  
}
