/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Control.Control;
import Entidades.Alumno;
import Main.FrontEndDesktop;
import Model.AlumnoModel;
import Model.AlumnosModel;
import View.AlumnoView;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class AlumnoController {
        private Control control;
        private AlumnoModel model;
        private AlumnoView view;

    public AlumnoController(Control control, AlumnoModel model, AlumnoView view) {
        model.init();
        this.control = control;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void guardarAlumno(){
        AlumnosModel alumnosModel = FrontEndDesktop.ALUMNOS_VIEW.getModel();
        model.clearErrors();
        Alumno alumno = new Alumno();
            
        alumno.setCedula(view.getCedulaText().getText());      
        if (view.getCedulaText().getText().length() == 0){
            model.getErrores().put("Cedula", "Cédula requerida.");
        }
        alumno.setNombre(view.getNombreText().getText());      
        if (view.getCedulaText().getText().length() == 0){
            model.getErrores().put("Nombre", "Nombre requerido.");
        }
        alumno.setTelefono(view.getTelefonoText().getText());      
        if (view.getTelefonoText().getText().length() == 0){
            model.getErrores().put("Telefono", "Teléfono requerido.");
        }
        alumno.setEmail(view.getEmailText().getText());
        if (view.getEmailText().getText().length() == 0){
            model.getErrores().put("Email", "Email requerido.");
        }
        alumno.setFecha(view.getDateFromDatePicker());
//        if (view.getFechaText().getText().length() == 0){
//            model.getErrores().put("Fecha", "Fecha requerida.");
//        }
        
        if (model.getErrores().isEmpty()){
            try{
                List<Alumno> listaAlumnos;
                switch(model.getModo()){
                    case FrontEndDesktop.MODO_AGREGAR:
                        control.insertarAlumno(alumno);
                        model.setMensaje("Alumno agregado con exito.");
                        model.setActual(new Alumno());                        
                        listaAlumnos = control.listarAlumnos();       
                        alumnosModel.setAlumnos(listaAlumnos);
                        FrontEndDesktop.ALUMNOS_VIEW.setModel(alumnosModel);
                        view.setVisible(false);
                        break;
                    case FrontEndDesktop.MODO_EDITAR:
                        control.editarAlumno(alumno);
                        model.setMensaje("Alumno editado con exito.");
                        model.setActual(alumno);
                        listaAlumnos = control.listarAlumnos();
                        alumnosModel.setAlumnos(listaAlumnos);
                        view.setVisible(false);
                        break;
                }
            }
            catch(Exception e){
                model.getErrores().put("Cedula", "Cédula ya registrada.");
                model.setMensaje("Cédula ya registrada.");
                model.setActual(alumno);
            }
        }
        else{
            model.setMensaje("ESPACIOS VACIOS.");
            model.setActual(alumno);
        }
        
        
    }
    
}
