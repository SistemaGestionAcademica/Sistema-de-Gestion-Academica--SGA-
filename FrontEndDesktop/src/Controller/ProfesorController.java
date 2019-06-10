/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Control.Control;
import Entidades.Profesor;
import Main.FrontEndDesktop;
import Model.ProfesorModel;
import Model.ProfesoresModel;
import View.ProfesorView;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class ProfesorController {
    private Control control;
    private ProfesorModel model;
    private ProfesorView view;
    
    public ProfesorController(Control control, ProfesorModel model, ProfesorView view) {
        model.init();
        this.control = control;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void guardarProfesor(){
        ProfesoresModel profesoresModel = FrontEndDesktop.PROFESORES_VIEW.getModel();
        model.clearErrors();
        Profesor profesor = new Profesor();
        profesor.setCedula(view.getCedulaText().getText());      
        if (view.getCedulaText().getText().length() == 0){
            model.getErrores().put("Cedula", "Cédula requerida.");
        }
        profesor.setNombre(view.getNombreText().getText());      
        if (view.getCedulaText().getText().length() == 0){
            model.getErrores().put("Nombre", "Nombre requerido.");
        }
        profesor.setTelefono(view.getTelefonoText().getText());      
        if (view.getTelefonoText().getText().length() == 0){
            model.getErrores().put("Telefono", "Teléfono requerido.");
        }
        profesor.setEmail(view.getEmailText().getText());
        if (view.getEmailText().getText().length() == 0){
            model.getErrores().put("Email", "Email requerido.");
        }
        
        if (model.getErrores().isEmpty()){
            try{
                List<Profesor> listaProfesores;
                switch(model.getModo()){
                    case FrontEndDesktop.MODO_AGREGAR:
                        control.insertarProfesor(profesor);
                        model.setMensaje("Profesor agregado con exito.");
                        model.setActual(new Profesor());                        
                        listaProfesores = control.listaProfesores();      
                        profesoresModel.setProfesores(listaProfesores);
                        FrontEndDesktop.PROFESORES_VIEW.setModel(profesoresModel);
                        view.setVisible(false);
                        break;
                    case FrontEndDesktop.MODO_EDITAR:
                        control.editarProfesor(profesor);
                        model.setMensaje("Profesor editado con exito.");
                        model.setActual(profesor);
                        listaProfesores = control.listaProfesores();
                        profesoresModel.setProfesores(listaProfesores);
                        view.setVisible(false);
                        break;
                }
            }
            catch(Exception e){
                model.getErrores().put("Cedula", "Cédula ya registrada.");
                model.setMensaje("Cédula ya registrada.");
                model.setActual(profesor);
            }
        }
        else{
            model.setMensaje("ESPACIOS VACIOS.");
            model.setActual(profesor);
        }  
    }
}
