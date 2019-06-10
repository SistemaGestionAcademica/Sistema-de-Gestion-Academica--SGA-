/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Controller.AlumnoController;
import Controller.AlumnosController;
import Controller.CarreraController;
import Controller.CarrerasController;
import Controller.CiclosController;
import Controller.CicloController;
import Controller.CursoController;
import Controller.CursosController;
import Controller.GrupoController;
import Controller.GruposController;
import Controller.ProfesorController;
import Controller.ProfesoresController;
import Model.AlumnoModel;
import Model.AlumnosModel;
import Model.CarreraModel;
import Model.CarrerasModel;
import Model.CicloModel;
import Model.CiclosModel;
import Model.CursoModel;
import Model.CursosModel;
import Model.GrupoModel;
import Model.GruposModel;
import Model.ProfesorModel;
import Model.ProfesoresModel;
import View.AlumnoView;
import View.AlumnosView;
import View.CarreraView;
import View.CarrerasView;
import View.CicloView;
import View.CiclosView;
import View.CursoView;
import View.CursosView;
import View.GrupoView;
import View.GruposView;
import View.ProfesorView;
import View.ProfesoresView;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Steven Villalobos
 */
public class FrontEndDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, SQLException, NoDataException {
        
        Control control = Control.instance(); 
        
        AlumnosView alumnosView = new AlumnosView();
        AlumnoView alumnoView = new AlumnoView();
        
        AlumnosModel alumnosModel = new AlumnosModel();
        AlumnoModel alumnoModel = new AlumnoModel();
        
        AlumnosController controller = new AlumnosController(control,alumnosModel,alumnosView);
        AlumnoController alumnoController = new AlumnoController(control,alumnoModel,alumnoView);
        
        ProfesorView profesorView = new ProfesorView();
        ProfesoresView profesoresView = new ProfesoresView();
        
        ProfesorModel profesorModel = new ProfesorModel();
        ProfesoresModel profesoresModel = new ProfesoresModel();
        
        ProfesoresController profesoresController = new ProfesoresController(control,profesoresModel,profesoresView);
        ProfesorController profesorController = new ProfesorController(control,profesorModel,profesorView);
        
        GrupoView grupoView = new GrupoView();
        GruposView gruposView = new GruposView();
        
        GruposModel gruposModel = new GruposModel();
        GrupoModel grupoModel = new GrupoModel();
        
        GruposController gruposController = new GruposController(control,gruposModel,gruposView);
        GrupoController grupoController = new GrupoController(control,grupoModel,grupoView);
        
        //
        CarreraView carreraView = new CarreraView();
        CarrerasView carrerasView = new CarrerasView();
        CarrerasModel carrerasModel = new CarrerasModel();
        CarreraModel carreraModel = new CarreraModel();
        CarreraController carreraController = new CarreraController(carreraView, carreraModel, control);
        CarrerasController carrerasController = new CarrerasController(carrerasView, carrerasModel, control);

        //
        CicloView cicloView = new CicloView();
        CiclosView ciclosView = new CiclosView();
        CicloModel cicloModel = new CicloModel();
        CiclosModel ciclosModel = new CiclosModel();
        CicloController cicloController = new CicloController(cicloView, cicloModel, control);
        CiclosController ciclosController = new CiclosController(ciclosView, ciclosModel, control);
        //
        CursoView cursoView = new CursoView();
        CursosView cursosView = new CursosView();
        CursoModel cursoModel = new CursoModel();
        CursosModel cursosModel = new CursosModel();
        CursoController cursoController = new CursoController(cursoView, cursoModel, control);
        CursosController cursosController = new CursosController(cursosView, cursosModel, control);
        //
        
        GRUPOS_VIEW = gruposView;
        GRUPO_VIEW = grupoView;
        PROFESORES_VIEW = profesoresView;
        PROFESOR_VIEW = profesorView;
        ALUMNOS_VIEW = alumnosView;
        ALUMNO_VIEW = alumnoView;
        CURSO_VIEW = cursoView;
        CURSOS_VIEW = cursosView;
        CARRERA_VIEW = carreraView;
        CARRERAS_VIEW = carrerasView;
        CICLO_VIEW = cicloView;
        CICLOS_VIEW = ciclosView;
        
        MainView main = new MainView(ALUMNOS_VIEW,ALUMNO_VIEW,PROFESORES_VIEW,PROFESOR_VIEW,
                                    GRUPOS_VIEW,GRUPO_VIEW, CARRERAS_VIEW, CARRERA_VIEW,
                                    CURSOS_VIEW, CURSO_VIEW, CICLOS_VIEW, CICLO_VIEW);
        main.setVisible(true);
    }
    public static GruposView GRUPOS_VIEW;
    public static GrupoView GRUPO_VIEW;
    
    public static ProfesorView PROFESOR_VIEW;
    public static ProfesoresView PROFESORES_VIEW;
    
    public static AlumnosView ALUMNOS_VIEW;
    public static AlumnoView ALUMNO_VIEW;
    
    public static CarreraView CARRERA_VIEW;
    public static CarrerasView CARRERAS_VIEW;
    
    public static CicloView CICLO_VIEW;
    public static CiclosView CICLOS_VIEW;
    
    public static CursoView CURSO_VIEW;
    public static CursosView CURSOS_VIEW;
    
    public static  final int  MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    
    public static Border BORDER_ERROR = BorderFactory.createLineBorder(Color.red);
    public static Border BORDER_NOBORDER = BorderFactory.createLineBorder(Color.red);
}
