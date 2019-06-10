/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Entidades.Grupo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Steven Villalobos
 */
public class GrupoTableModel extends AbstractTableModel{
    List<Grupo> rows;
    int[] cols;
    
    public GrupoTableModel(int[] cols,List<Grupo> rows) 
    {
        this.rows = rows;
        this.cols = cols;
        this.initColNames();
    }
    public void initColNames()
    {
        colNames[CODIGO]= "CODIGO";
        colNames[CODIGOCURSO]= "CODIGOCURSO";
        colNames[PROFESOR]= "PROFESOR";
        colNames[HORARIO]= "HORARIO";
    }
    public Grupo getRowAt(int row){
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
        Grupo grupo = rows.get(row);
        switch (cols[index])
        {
            case CODIGO: return grupo.getCodigo();
            case CODIGOCURSO: return grupo.getCurso();
            case PROFESOR: return grupo.getProfesor();
            case HORARIO: return grupo.getHorario();
            default: return "";
        }
    }
    
    public static final int CODIGO=0;
    public static final int CODIGOCURSO=1;
    public static final int PROFESOR=2;
    public static final int HORARIO=3;

    
    String[] colNames = new String[4];
}
