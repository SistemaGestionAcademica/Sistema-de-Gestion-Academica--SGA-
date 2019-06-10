/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Carrera;
import Main.FrontEndDesktop;
import Model.CarrerasModel;
import View.CarrerasView;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CarrerasController {
    Control control;
    CarrerasView view;
    CarrerasModel model;
 
    public CarrerasController(CarrerasView view, CarrerasModel model, Control control) {
        model.init();
        this.control= control;
        
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void preAgregar(){       
        FrontEndDesktop.CARRERA_VIEW.getModel().clearErrors();
        FrontEndDesktop.CARRERA_VIEW.getModel().setModo(FrontEndDesktop.MODO_AGREGAR);
        FrontEndDesktop.CARRERA_VIEW.getModel().setCurrent(new Carrera());
        FrontEndDesktop.CARRERA_VIEW.setVisible(true);
        FrontEndDesktop.CARRERA_VIEW.toFront();
        
    }
        
    public void editar(int row){
        FrontEndDesktop.CARRERA_VIEW.getModel().clearErrors();
        Carrera seleccionada = model.getCarreras().getRowAt(row); 
        FrontEndDesktop.CARRERA_VIEW.getModel().setModo(FrontEndDesktop.MODO_EDITAR);
        FrontEndDesktop.CARRERA_VIEW.getModel().setCurrent(seleccionada);
        FrontEndDesktop.CARRERA_VIEW.setVisible(true);
        FrontEndDesktop.CARRERA_VIEW.toFront();
    }
        
    public void Listar() throws GlobalException, NoDataException{
          List<Carrera> rows = (List<Carrera>) control.listarCarreras();
          model.setCarreras(rows);
      }
    
    public void buscarCarreraCodigo() throws GlobalException, NoDataException{
        model.getFilter().setCodigo(view.busFld.getText());
        model.clearErrors();
        List<Carrera> rows = control.buscarCarreraCodigo(model.getFilter().getCodigo());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCarreras(rows);
    }
    
    public void buscarCarreraNombre() throws GlobalException, NoDataException{
        model.getFilter().setNombre(view.busFld.getText());
        model.clearErrors();
        List<Carrera> rows = control.buscarCarreraNombre(model.getFilter().getNombre());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCarreras(rows);
    }

    
    public void eliminarCarrera(int row) throws GlobalException, NoDataException
    {
        try 
        {
            control.eliminarCarrera(model.getCarreras().getRowAt(row).getCodigo()); 
        } 
        catch (Exception ex) { }
        this.Listar();
    }
}
