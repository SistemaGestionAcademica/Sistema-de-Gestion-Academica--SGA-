/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Profesor;
import TableModel.ProfesorTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Steven Villalobos
 */
public class ProfesoresModel extends java.util.Observable{
    private Profesor filtro;
    private ProfesorTableModel tableProfesor;
    private HashMap<String,String> errores;
    private String mensaje;
    public ProfesoresModel()
    {

    }
    public void setProfesores(List<Profesor> profesores)
    {
        int[] cols={ProfesorTableModel.CEDULA,ProfesorTableModel.NOMBRE,ProfesorTableModel.TELEFONO,ProfesorTableModel.EMAIL};
        this.tableProfesor = new ProfesorTableModel(profesores, cols);  
        setChanged();
        notifyObservers();
    }
    public void init()
    {
        filtro = new Profesor();
        List<Profesor> rows = new ArrayList<Profesor>();
        this.setProfesores(rows);
        clearErrors();
    }
    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
    public Profesor getFiltro() {
        return filtro;
    }
    public ProfesorTableModel getProfesorTableModel() {
        return tableProfesor;
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
}
