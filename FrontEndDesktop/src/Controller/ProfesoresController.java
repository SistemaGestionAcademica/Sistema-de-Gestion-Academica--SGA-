/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Profesor;
import Main.FrontEndDesktop;
import Model.ProfesorModel;
import Model.ProfesoresModel;
import View.ProfesoresView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class ProfesoresController {
    Control control;
    ProfesoresView view;
    ProfesoresModel model;
    
    public ProfesoresController(Control control, ProfesoresModel model, ProfesoresView view){
        model.init();
        this.control = control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void preAgregar(){
        FrontEndDesktop.PROFESOR_VIEW.getModel().clearErrors();
        FrontEndDesktop.PROFESOR_VIEW.getModel().setModo(FrontEndDesktop.MODO_AGREGAR);
        FrontEndDesktop.PROFESOR_VIEW.getModel().setActual(new Profesor());
        FrontEndDesktop.PROFESOR_VIEW.setVisible(true);
        FrontEndDesktop.PROFESOR_VIEW.toFront();
    }
    
    public void editar(int row){
        FrontEndDesktop.PROFESOR_VIEW.getModel().clearErrors();
        Profesor seleccionado = model.getProfesorTableModel().getRowAt(row);
        FrontEndDesktop.PROFESOR_VIEW.getModel().setModo(FrontEndDesktop.MODO_EDITAR);
        FrontEndDesktop.PROFESOR_VIEW.getModel().setActual(seleccionado);
        FrontEndDesktop.PROFESOR_VIEW.setVisible(true);
        FrontEndDesktop.PROFESOR_VIEW.toFront();
    }
    
    public void Listar() throws GlobalException, NoDataException{
        List<Profesor> rows = control.listaProfesores();
        model.setProfesores(rows);
    }
    
    public void buscarProfesor()throws GlobalException, NoDataException{
        model.getFiltro().setCedula(view.buscarFld.getText());
        model.clearErrors();
        List<Profesor> rows = control.buscarProfesor(model.getFiltro().getCedula());
        if(rows == null){
            model.getErrores().put("buscarTXT", "Ningún registro coincide");
            model.setMensaje("ningún registro coincide");
        }
        model.setProfesores(rows);
    }
    
    public void buscarProfesorNombre()throws GlobalException, NoDataException{
        model.getFiltro().setNombre(view.buscarFld.getText());
        model.clearErrors();
        List<Profesor> rows = control.buscarProfesorNombre(model.getFiltro().getNombre());
        if(rows == null){
            model.getErrores().put("buscarTXT", "Ningún registro coincide");
            model.setMensaje("ningún registro coincide");
        }
        model.setProfesores(rows);
    }
    
    public void eliminarProfesor(int row) throws GlobalException, NoDataException{
        try{
            control.eliminarProfesor(model.getProfesorTableModel().getRowAt(row).getCedula());
        }catch(Exception ex){ }
        this.Listar();
    }
}