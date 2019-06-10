/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Ciclo;
import TableModel.CicloTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Usuario1
 */
public class CiclosModel extends java.util.Observable{
    Ciclo filter;
    CicloTableModel ciclos;
    HashMap<String,String> errores;
    String mensaje;
    
    public void init()
    {
        filter=new Ciclo();
        List<Ciclo> rows=new ArrayList<Ciclo>();
        this.setCiclos(rows);
        clearErrors();
    }
    
    public void setCiclos(List<Ciclo> ciclos){
        int[] cols={CicloTableModel.CODIGO,CicloTableModel.ANO,CicloTableModel.ESTADO,
        CicloTableModel.NUMERO,CicloTableModel.FECHA_INICIO,CicloTableModel.FECHA_FINALIZACION};
        this.ciclos = new CicloTableModel(cols,ciclos);  
        setChanged();
        notifyObservers();       
    }
    
        public Ciclo getFilter() {
        return filter;
    }
    
    public void setFilter(Ciclo filter) {
        this.filter = filter;
    }
    
     public CicloTableModel getCiclos() {
        return ciclos;
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
