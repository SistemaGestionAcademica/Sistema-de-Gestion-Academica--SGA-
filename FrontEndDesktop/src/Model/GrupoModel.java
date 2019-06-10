/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Curso;
import Entidades.Grupo;
import Entidades.Profesor;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Steven Villalobos
 */
public class GrupoModel extends java.util.Observable{
    Grupo current;
    ComboBoxModel<Curso> cursos;
    ComboBoxModel<Profesor> profesores;
    HashMap<String, String> errores;
    String mensaje;
    int modo;
    
    public void init(Curso[] cursos, Profesor[] profesores){
        setCursos(cursos);
        setProfesores(profesores);
        setCurrent(new Grupo());
        clearErrors();
    }

    public void setCurrent(Grupo current) {
        this.current = current;
        setChanged();
        notifyObservers();
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public Grupo getCurrent() {
        return current;
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getModo() {
        return modo;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = new DefaultComboBoxModel(cursos);
        setChanged();
        notifyObservers();
    }

    public ComboBoxModel<Curso> getCursos() {
        return cursos;
    }

    public void setProfesores(Profesor[] profesores) {
        this.profesores = new DefaultComboBoxModel(profesores);
        setChanged();
        notifyObservers();
    }

    public ComboBoxModel<Profesor> getProfesores() {
        return profesores;
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
