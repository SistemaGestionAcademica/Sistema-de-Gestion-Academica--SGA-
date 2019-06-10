/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Alumno;
import java.util.HashMap;

/**
 *
 * @author Steven Villalobos
 */
public class AlumnoModel extends java.util.Observable
{
    private Alumno actual;
    private HashMap<String,String> errores;
    private String mensaje;
    private int modo;
    
    public void init()
    {
        setActual(new Alumno());
        clearErrors();
    }
    public Alumno getActual() {
        return actual;
    }

    public void setActual(Alumno actual) {
        this.actual = actual;
        setChanged();
        notifyObservers();  
    }
    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<String,String>());
        setMensaje("");  
    }
    
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
}
