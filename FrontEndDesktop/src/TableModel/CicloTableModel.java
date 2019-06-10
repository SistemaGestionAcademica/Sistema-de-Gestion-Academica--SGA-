/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Entidades.Ciclo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario1
 */
public class CicloTableModel extends AbstractTableModel{
    List<Ciclo> rows;
    int[] cols;
    
    public CicloTableModel(int[] cols,List<Ciclo> rows) {
        this.rows = rows;
        this.cols = cols;
        initColNames();
    }
    
    public CicloTableModel(int[] cols,Ciclo row) {
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
        Ciclo ciclo = rows.get(row);
        switch (cols[col]){
            case CODIGO: return ciclo.getCodigo();
            case ANO: return ciclo.getAno();
            case ESTADO: return ciclo.getEstado();
            case NUMERO: return ciclo.getNumero();
            case FECHA_INICIO: return ciclo.getFecha_inicio();
            case FECHA_FINALIZACION: return ciclo.getFecha_finalizacion();
            
            default: return "";
        }
    }
    
    public Ciclo getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int CODIGO=0;
    public static final int ANO=1;
    public static final int ESTADO=2;
    public static final int NUMERO=3;
    public static final int FECHA_INICIO=4;
    public static final int FECHA_FINALIZACION=5;

    
    String[] colNames = new String[6];
    private void initColNames(){
        colNames[CODIGO]= "Codigo";
        colNames[ANO]= "Año";
        colNames[ESTADO]= "Estado";
        colNames[NUMERO]= "Codigo";
        colNames[FECHA_INICIO]= "Fecha ";
        colNames[FECHA_FINALIZACION]= "Fecha Finalizacion";   
    }
    
    
}
