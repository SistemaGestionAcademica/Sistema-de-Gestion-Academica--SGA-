/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Carrera;
import TableModel.CarreraTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Usuario1
 */
public class CarrerasModel extends java.util.Observable{
    Carrera filter;
    CarreraTableModel carreras;
    HashMap<String,String> errores;
    String mensaje;
    
        public void init()
    {
        filter=new Carrera();
        List<Carrera> rows=new ArrayList<Carrera>();
        this.setCarreras(rows);
        clearErrors();
    }
    
    public void setCarreras(List<Carrera> carreras){
        int[] cols={CarreraTableModel.CODIGO,CarreraTableModel.NOMBRE,CarreraTableModel.TITULO};
        this.carreras = new CarreraTableModel(cols,carreras);  
        setChanged();
        notifyObservers();       
    }
    
        public Carrera getFilter() {
        return filter;
    }
    
    public void setFilter(Carrera filter) {
        this.filter = filter;
    }
    
     public CarreraTableModel getCarreras() {
        return carreras;
    }
     
     
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
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
