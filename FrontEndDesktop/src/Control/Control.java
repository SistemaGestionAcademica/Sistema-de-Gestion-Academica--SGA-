/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServicioAlumno;
import AccesoDatos.ServicioGrupo;
import AccesoDatos.ServicioProfesor;
import AccesoDatos.servicioCarrera;
import AccesoDatos.servicioCiclo;
import AccesoDatos.servicioCursos;
import Entidades.Alumno;
import Entidades.Carrera;
import Entidades.Ciclo;
import Entidades.Curso;
import Entidades.Grupo;
import Entidades.Profesor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class Control {
    private ServicioAlumno servicioAlumno;
    private ServicioProfesor servicioProfesor;
    private ServicioGrupo servicioGrupo;
    private servicioCarrera servCarr;
    private servicioCiclo servCicl;
    private servicioCursos servCurs;


    private static Control uniqueInstance;
    public static Control instance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Control();
        }
        return uniqueInstance;
    }
    public Control()
    {
        servicioProfesor = new ServicioProfesor();
        servicioAlumno = new ServicioAlumno();
        servicioGrupo = new ServicioGrupo();
        servCarr = new servicioCarrera();
        servCicl = new servicioCiclo();
        servCurs = new servicioCursos();
    }
    //ALUMNOS
    public void insertarAlumno(Alumno alumno) throws GlobalException,SQLException{
        servicioAlumno.insertarAlumno_(alumno);
    }
    public void eliminarAlumno(String cedula) throws GlobalException,SQLException{
        servicioAlumno.eliminarAlumno_(cedula);
    }
    public void editarAlumno(Alumno alumno) throws GlobalException,SQLException{
        servicioAlumno.editarAlumno_(alumno);
    }
    
    public List<Alumno> buscarAlumno(String cedula) throws GlobalException, NoDataException{
        return (List<Alumno>)servicioAlumno.buscarAlumno_(cedula); 
    }
    
    public List<Alumno> buscarAlumnoNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Alumno>)servicioAlumno.buscarAlumnoNombre_(nombre); 
    }
    
    public List listarAlumnos() throws GlobalException{
        return servicioAlumno.listarAlumnos_();
    }
    //PROFESORES
    public void insertarProfesor(Profesor profesor) throws GlobalException, SQLException, NoDataException{
        servicioProfesor.insertarProfesor_(profesor);
    }
    public void eliminarProfesor(String cedula) throws GlobalException,SQLException, NoDataException{
        servicioProfesor.eliminarProfesor_(cedula);
    }
    public void editarProfesor(Profesor profesor) throws GlobalException, SQLException, NoDataException{
        servicioProfesor.modificarProfesor_(profesor);
    }
    
    public List<Profesor> buscarProfesor(String cedula) throws GlobalException, NoDataException{
        return (List<Profesor>)servicioProfesor.buscarProfesor_(cedula); 
    }
    
    public List<Profesor> buscarProfesorNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Profesor>)servicioProfesor.buscarProfesorNombre_(nombre); 
    }
    
    public List<Profesor> listaProfesores() throws GlobalException, NoDataException{
        return (List<Profesor>)servicioProfesor.listarProfesores_();
    }
    //GRUPOS
    public void insertarGrupo(Grupo grupo) throws GlobalException, SQLException, NoDataException{
        servicioGrupo.insertarGrupo_(grupo);
    }
    public void eliminarGrupo(String codigo, String curso, String profesor) throws GlobalException,SQLException, NoDataException{
        servicioGrupo.eliminarGrupo_(codigo, curso, profesor);
    }
    public void editarGrupo(Grupo grupo) throws GlobalException, SQLException, NoDataException{
        servicioGrupo.modificarGrupo_(grupo);
    }
    public List<Grupo> listaGrupos() throws GlobalException, SQLException, NoDataException{
        return (List<Grupo>)servicioGrupo.listarGrupos_();
    }
    
    public List<Grupo> buscarGrupo(String codigo) throws GlobalException, SQLException, NoDataException{
        return (List<Grupo>)servicioGrupo.buscarGrupo_(codigo);
    }
    //Carreras
    public void insertarCarrera(Carrera carrera) throws Exception{
        servCarr.insertarCarrera_(carrera);
    }
    
    public void modificarCarrera(Carrera carrera) throws Exception{
        servCarr.modificarCarrera_(carrera);
    }
    
   public void eliminarCarrera(String codigo) throws Exception{
        servCarr.eliminarCarrera_(codigo);
    }
    
    public List<Carrera> buscarCarreraCodigo(String codigo) throws GlobalException, NoDataException{
        return (List<Carrera>)servCarr.buscarCarreraCodigo_(codigo); 
    }
   
    public List<Carrera> buscarCarreraNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Carrera>)servCarr.buscarCarreraNombre_(nombre); 
    }
    
    public List<Carrera> listarCarreras() throws GlobalException, NoDataException{
       return (List<Carrera>) servCarr.listarCarreras_();
    }
    
    //CURSOS
    public void insertarCurso(Curso curso) throws Exception{
        servCurs.insertarCurso_(curso);
    }
    
    public void modificarCurso(Curso curso) throws Exception{
        servCurs.modificarCurso_(curso);
    }
    
   public void eliminarCurso(String codigo) throws Exception{
        servCurs.eliminarCurso_(codigo);
    }
    
    public List<Curso> buscarCurso(String codigo) throws GlobalException, NoDataException{
        return (List<Curso>) servCurs.buscarCursoCodigo_(codigo); 
    }
    
    public List<Curso> buscarCursoNombre(String codigo) throws GlobalException, NoDataException{
        return (List<Curso>) servCurs.buscarCursoNombre_(codigo); 
    }
    
    public List<Curso> listarCursos() throws GlobalException, NoDataException{
       return (List<Curso>) servCurs.listarCursos_();
    }
    
    //Ciclos
    public void insertarCiclo(Ciclo ciclo) throws Exception{
        servCicl.insertarCiclo_(ciclo);
    }
    
    public void modificarCiclo(Ciclo grupo) throws Exception{
        servCicl.modificarCiclo_(grupo);
    }
    
   public void eliminarCiclo(String codigo) throws Exception{
        servCicl.eliminarCiclo_(codigo);
    }
    
    public List<Ciclo> buscarCiclo(String codigo) throws GlobalException, NoDataException{
        return (List<Ciclo>) servCicl.buscarCodigoCiclo_(codigo); 
    }
    
        public List<Ciclo> buscarCicloAno(String codigo) throws GlobalException, NoDataException{
        return (List<Ciclo>) servCicl.buscarCicloAno_(codigo); 
    }
    
    public List<Ciclo> listarCiclos() throws GlobalException, NoDataException{
       return (List<Ciclo>) servCicl.listCiclos();
    }
    
}
