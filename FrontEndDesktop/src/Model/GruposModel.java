/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Grupo;
import TableModel.GrupoTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Steven Villalobos
 */
public class GruposModel extends java.util.Observable{
    Grupo filter;
    GrupoTableModel grupos;
    HashMap<String, String> errores;
    String mensaje;
    
    public void init(){
        filter = new Grupo();
        List<Grupo> rows = new ArrayList<Grupo>();
        this.setGrupos(rows);
        clearErrors();
    }
    
    public void setGrupos(List<Grupo> grupos){
        int[] cols = {GrupoTableModel.CODIGO, GrupoTableModel.CODIGOCURSO, GrupoTableModel.PROFESOR, GrupoTableModel.HORARIO};
        this.grupos = new GrupoTableModel(cols, grupos);
        setChanged();
        notifyObservers();
    }
    
    public GrupoTableModel getGrupos(){
        return grupos;
    }
    
    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }
    
    public HashMap<String, String> getErrores() {
        return errores;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setFilter(Grupo filter) {
        this.filter = filter;
    }

    public Grupo getFilter() {
        return filter;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<String,String>());
        setMensaje(""); 
    }
    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
}
