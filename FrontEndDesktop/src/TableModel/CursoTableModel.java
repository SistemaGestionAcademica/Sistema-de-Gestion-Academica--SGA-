/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Entidades.Curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario1
 */
public class CursoTableModel extends AbstractTableModel{
    List<Curso> rows;
    int[] cols;
    
    public CursoTableModel(int[] cols,List<Curso> rows) {
        this.rows = rows;
        this.cols = cols;
        initColNames();
    }
    
    public CursoTableModel(int[] cols,Curso row) {
        this.rows = rows;
        this.cols = cols;
        initColNames();
    }
    
    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int col){
        return colNames[cols[col]];
    }
    
    public int getRowCount() {
        return rows.size();
    }
  

   
    public Object getValueAt(int row, int col) {
        Curso curso = rows.get(row);
        switch (cols[col]){
            case CODIGO: return curso.getCodigo();
            case CICLO: return curso.getCodigo_ciclo();
            case CARRERA: return curso.getCodigo_carrera();
            case NOMBRE: return curso.getNombre();
            case CREDITOS: return curso.getCreditos();
            case HORAS: return curso.getHoras();
            
            default: return "";
        }
    }
    
    public Curso getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int CODIGO=0;
    public static final int CICLO=1;
    public static final int CARRERA=2;
    public static final int NOMBRE=3;
    public static final int CREDITOS=4;
    public static final int HORAS=5;

    
    String[] colNames = new String[6];
    private void initColNames(){
        colNames[CODIGO]= "Codigo";
        colNames[CICLO]= "Ciclo";
        colNames[CARRERA]= "Carrera";   
        colNames[NOMBRE]= "Nombre";
        colNames[CREDITOS]= "Creditos";
        colNames[HORAS]= "Horas";  
    }
}
