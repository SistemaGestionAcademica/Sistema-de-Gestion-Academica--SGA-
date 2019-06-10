/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Ciclo;
import Main.FrontEndDesktop;
import Model.CiclosModel;
import View.CiclosView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CiclosController {
    Control control;
    CiclosView view;
    CiclosModel model;
    
    public CiclosController(CiclosView view, CiclosModel model, Control control) {
        model.init();
        this.control= control;
        
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void preAgregar(){       
        FrontEndDesktop.CICLO_VIEW.getModel().clearErrors();
        FrontEndDesktop.CICLO_VIEW.getModel().setModo(FrontEndDesktop.MODO_AGREGAR);
        FrontEndDesktop.CICLO_VIEW.getModel().setCurrent(new Ciclo());
        FrontEndDesktop.CICLO_VIEW.setVisible(true);
        FrontEndDesktop.CICLO_VIEW.toFront();
        
    }
        
    public void editar(int row){
        FrontEndDesktop.CICLO_VIEW.getModel().clearErrors();
        Ciclo seleccionada = model.getCiclos().getRowAt(row); 
        FrontEndDesktop.CICLO_VIEW.getModel().setModo(FrontEndDesktop.MODO_EDITAR);
        FrontEndDesktop.CICLO_VIEW.getModel().setCurrent(seleccionada);
        FrontEndDesktop.CICLO_VIEW.setVisible(true);
        FrontEndDesktop.CICLO_VIEW.toFront();
    }
        
    public void Listar() throws GlobalException, NoDataException{
          List<Ciclo> rows ;//= (List<Ciclo>) control.listarCiclos();
          rows = control.listarCiclos();
          model.setCiclos(rows);
      }

    public void Buscar()throws GlobalException, NoDataException, SQLException{
        model.getFilter().setCodigo(view.busFld.getText());
        model.clearErrors();
        List<Ciclo> rows = control.buscarCiclo(model.getFilter().getCodigo());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCiclos(rows);
    }
    
    public void BuscarAno()throws GlobalException, NoDataException, SQLException{
        model.getFilter().setAno(view.busFld.getText());
        model.clearErrors();
        List<Ciclo> rows = control.buscarCicloAno(model.getFilter().getAno());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCiclos(rows);
    }
    
    public void eliminarCiclo(int row) throws GlobalException, NoDataException
    {
        try 
        {
            control.eliminarCiclo(model.getCiclos().getRowAt(row).getCodigo()); 
        } 
        catch (Exception ex) { }
        this.Listar();
    }
    
}
