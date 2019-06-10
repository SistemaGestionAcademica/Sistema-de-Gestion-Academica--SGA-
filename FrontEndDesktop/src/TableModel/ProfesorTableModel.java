/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Entidades.Profesor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Steven Villalobos
 */
public class ProfesorTableModel extends AbstractTableModel
{
    List<Profesor> rows;
    int[] cols;
    
    public ProfesorTableModel(List<Profesor> rows, int[] cols) 
    {
        this.rows = rows;
        this.cols = cols;
        initColNames();
    }
    public void initColNames()
    {
        colNames[CEDULA]= "CÉDULA";
        colNames[NOMBRE]= "NOMBRE";
        colNames[TELEFONO]= "TELÉFONO";
        colNames[EMAIL]= "EMAIL";
    }
    public Profesor getRowAt(int row){
        return rows.get(row);
    }
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }
    
    @Override
    public Object getValueAt(int row, int index) {
        Profesor profesor = rows.get(row);
        switch (cols[index])
        {
            case CEDULA: return profesor.getCedula();
            case NOMBRE: return profesor.getNombre();
            case TELEFONO: return profesor.getTelefono();
            case EMAIL: return profesor.getEmail();
            default: return "";
        }
    }
    
    public static final int CEDULA=0;
    public static final int NOMBRE=1;
    public static final int TELEFONO=2;
    public static final int EMAIL=3;
    
    String[] colNames = new String[4];
}
