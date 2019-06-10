/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Grupo;
import Main.FrontEndDesktop;
import Model.GrupoModel;
import Model.GruposModel;
import View.GruposView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class GruposController {
    Control control;
    GruposView view;
    GruposModel model;
    
    public GruposController(Control control, GruposModel model , GruposView view){
        model.init();
        this.control = control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    public void preAgregar(){
        FrontEndDesktop.GRUPO_VIEW.getModel().clearErrors();
        FrontEndDesktop.GRUPO_VIEW.getModel().setModo(FrontEndDesktop.MODO_AGREGAR);
        FrontEndDesktop.GRUPO_VIEW.getModel().setCurrent(new Grupo());
        FrontEndDesktop.GRUPO_VIEW.setVisible(true);
        FrontEndDesktop.GRUPO_VIEW.toFront();
    }
    
    public void editar(int row){
        FrontEndDesktop.GRUPO_VIEW.getModel().clearErrors();
        Grupo seleccionado = model.getGrupos().getRowAt(row);
        FrontEndDesktop.GRUPO_VIEW.getModel().setModo(FrontEndDesktop.MODO_EDITAR);
        FrontEndDesktop.GRUPO_VIEW.getModel().setCurrent(seleccionado);
        FrontEndDesktop.GRUPO_VIEW.setVisible(true);
        FrontEndDesktop.GRUPO_VIEW.toFront();
    }
    
    public void Listar()throws GlobalException, NoDataException, SQLException{
        List<Grupo> rows = control.listaGrupos();
        model.setGrupos(rows);
    }
    
    public void Buscar()throws GlobalException, NoDataException, SQLException{
        model.getFilter().setCodigo(view.busFld.getText());
        model.clearErrors();
        List<Grupo> rows = control.buscarGrupo(model.getFilter().getCodigo());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setGrupos(rows);
    }
    
    public void eliminarGrupo(int row)throws GlobalException, NoDataException, SQLException{
        try{
            control.eliminarGrupo(model.getGrupos().getRowAt(row).getCodigo(), model.getGrupos().getRowAt(row).getCurso().getCodigo(), model.getGrupos().getRowAt(row).getProfesor().getCedula());
        }catch(Exception ex){}
        this.Listar();
    }
}
