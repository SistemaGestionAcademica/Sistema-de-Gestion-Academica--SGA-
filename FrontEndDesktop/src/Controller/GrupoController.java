/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Curso;
import Entidades.Grupo;
import Entidades.Profesor;
import Main.FrontEndDesktop;
import Model.GrupoModel;
import Model.GruposModel;
import View.GrupoView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class GrupoController {
   Control control;
    GrupoView view;
    GrupoModel model;
    
    public GrupoController(Control control, GrupoModel model, GrupoView view)throws GlobalException, NoDataException{
        model.init(control.listarCursos().toArray(new Curso[0]), control.listaProfesores().toArray(new Profesor[0]));
        this.control = control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Control getControl() {
        return control;
    }
    
    public void guardar(){
        GruposModel gruposModel = FrontEndDesktop.GRUPOS_VIEW.getModel();
        Grupo nuevo = new Grupo();
        model.clearErrors();
        
        nuevo.setCodigo(view.getCodigoText());
        if(view.codigoText.getText().length() == 0){
            model.getErrores().put("Codigo", "El codigo del grupo es requerido");
        }

        nuevo.setHorario(view.getHorarioText());
        if(view.getHorarioText().length() == 0){
            model.getErrores().put("Horario", "El horario del grupo es requerido");
        }
        nuevo.setCurso((Curso)view.codigoCursoComboBox.getSelectedItem());
        nuevo.setProfesor((Profesor)view.profesorComboBox.getSelectedItem());

        List<Grupo> grupos;
        if(model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                    case FrontEndDesktop.MODO_AGREGAR:
                        control.insertarGrupo(nuevo);
                        model.setMensaje("Grupo agregado");
                        model.setCurrent(new Grupo());
                        grupos = control.listaGrupos();
                        gruposModel.setGrupos(grupos);
                        view.setVisible(false);
                        break;
                    case FrontEndDesktop.MODO_EDITAR:
                        control.editarGrupo(nuevo);
                        model.setMensaje("Grupo modificado");
                        model.setCurrent(new Grupo());
                        grupos = control.listaGrupos();
                        gruposModel.setGrupos(grupos);
                        view.setVisible(false);
                        break;
                }
            }catch(Exception e){
                model.getErrores().put("id", "EL GRUPO YA EXISTE");
                model.setMensaje("EL GRUPO YA EXISTE");
                model.setCurrent(nuevo);
            }
        }else{
            model.setMensaje("ESPACIOS INCOMPLETOS.");
            model.setCurrent(nuevo);
        }
        
    }
    
    public void refreshCombBox() throws GlobalException, NoDataException{
        model.init(control.listarCursos().toArray(new Curso[0]), control.listaProfesores().toArray(new Profesor[0]));
    }
}
