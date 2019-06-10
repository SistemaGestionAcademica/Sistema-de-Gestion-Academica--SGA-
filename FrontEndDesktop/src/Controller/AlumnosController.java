/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Alumno;
import Main.FrontEndDesktop;
import Model.AlumnoModel;
import Model.AlumnosModel;
import View.AlumnosView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class AlumnosController {
    private AlumnosModel model;
    private AlumnosView view;
    private Control control;
    
    public AlumnosController(Control control, AlumnosModel model, AlumnosView view)
    {
        model.init();
        this.control = control;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void buscarCedula() throws GlobalException, NoDataException
    {
        model.getFiltro().setCedula(view.buscarFld.getText());
        model.clearErrors();
        List<Alumno> rows = control.buscarAlumno(model.getFiltro().getCedula());
        if(rows == null){
            model.getErrores().put("buscarTXT", "Ningún registro coincide");
            model.setMensaje("ningún registro coincide");
        }
        model.setAlumnos(rows);
    }
    
    public void buscarNombre() throws GlobalException, NoDataException
    {
        model.getFiltro().setNombre(view.buscarFld.getText());
        model.clearErrors();
        List<Alumno> rows = control.buscarAlumnoNombre(model.getFiltro().getNombre());
        if(rows == null){
            model.getErrores().put("buscarTXT", "Ningún registro coincide");
            model.setMensaje("ningún registro coincide");
        }
        model.setAlumnos(rows);
    }
    
    public void Listar() throws GlobalException{
        List<Alumno> rows = control.listarAlumnos();
        model.setAlumnos(rows);
    }
        
    public void agregar()
    {
        AlumnoModel alumnoModel = FrontEndDesktop.ALUMNO_VIEW.getModel();
        alumnoModel.clearErrors();
        alumnoModel.setModo(FrontEndDesktop.MODO_AGREGAR);
        alumnoModel.setActual(new Alumno());
        FrontEndDesktop.ALUMNO_VIEW.setVisible(true);
        FrontEndDesktop.ALUMNO_VIEW.toFront();
    }
    
    public void editar(int row)
    {
        AlumnoModel alumnoModel = FrontEndDesktop.ALUMNO_VIEW.getModel();
        alumnoModel.clearErrors();
        Alumno alumno = model.getAlumnoTableModel().getRowAt(row);
        alumnoModel.setModo(FrontEndDesktop.MODO_EDITAR);
        alumnoModel.setActual(alumno);
        FrontEndDesktop.ALUMNO_VIEW.setVisible(true);
        FrontEndDesktop.ALUMNO_VIEW.toFront();
    }
    public void borrar(int row) throws GlobalException{
        try{
            control.eliminarAlumno(model.getAlumnoTableModel().getRowAt(row).getCedula());
        }catch(Exception ex){ }
        this.Listar();
    }
}