/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import TableModel.AlumnoTableModel;
import Entidades.Alumno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Steven Villalobos
 */
public class AlumnosModel extends java.util.Observable {
    private Alumno filtro;
    private AlumnoTableModel tableAlumnos;
    private HashMap<String,String> errores;
    private String mensaje;
    
    public AlumnosModel()
    {

    }
    public void setAlumnos(List<Alumno> alumnos)
    {
        int[] cols={AlumnoTableModel.CEDULA,AlumnoTableModel.NOMBRE,AlumnoTableModel.TELEFONO,AlumnoTableModel.EMAIL,AlumnoTableModel.FECHA};
        this.tableAlumnos = new AlumnoTableModel(alumnos, cols);  
        setChanged();
        notifyObservers();
    }
    public void init()
    {
        filtro = new Alumno();
        List<Alumno> rows = new ArrayList<Alumno>();
        this.setAlumnos(rows);
        clearErrors();
    }
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
    public Alumno getFiltro() {
        return filtro;
    }
    public AlumnoTableModel getAlumnoTableModel() {
        return tableAlumnos;
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
