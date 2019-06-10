/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Entidades.Alumno;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Steven Villalobos
 */
public class AlumnoTableModel extends AbstractTableModel
{
    List<Alumno> rows;
    int[] cols;    

    public AlumnoTableModel(List<Alumno> rows, int[] cols) 
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
        colNames[FECHA]= "FECHA";
    }
    
    public Alumno getRowAt(int row) 
    {
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
    public static final int CEDULA=0;
    public static final int NOMBRE=1;
    public static final int TELEFONO=2;
    public static final int EMAIL=3;
    public static final int FECHA=4;   
    
    String[] colNames = new String[5];
    
    @Override
    public Object getValueAt(int row, int index) {
        Alumno alumno = rows.get(row);
        switch (cols[index])
        {
            case CEDULA: return alumno.getCedula();
            case NOMBRE: return alumno.getNombre();
            case TELEFONO: return alumno.getTelefono();
            case EMAIL: return alumno.getEmail();
            case FECHA: return alumno.getFecha();
            default: return "";
        }
    }
    
}