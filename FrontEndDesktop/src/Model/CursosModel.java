/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Curso;
import TableModel.CursoTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Usuario1
 */
public class CursosModel extends java.util.Observable{
    Curso filter; 
    CursoTableModel cursos;
    HashMap<String,String> errores;
    String mensaje;   
    
    public void init()
    { 
        filter = new Curso();
        List<Curso> rows = new ArrayList<Curso>();
        this.setCursos(rows);
        clearErrors();
    }
    
    public void setCursos(List<Curso> cursos){
        int[] cols={CursoTableModel.CODIGO,CursoTableModel.CICLO,CursoTableModel.CARRERA,CursoTableModel.NOMBRE, CursoTableModel.CREDITOS,
        CursoTableModel.HORAS};
        this.cursos =new CursoTableModel(cols,cursos);  
        setChanged();
        notifyObservers();        
    }
    
    public CursoTableModel getCursos() {
        return cursos;
    }
    
    public Curso getFilter() {
        return filter;
    }
    
    public void setFilter(Curso filter) {
        this.filter = filter;
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
