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
    private final ServicioAlumno servAlum;
    private final ServicioProfesor servicioProfesor;
    private final ServicioGrupo servicioGrupo;
    private servicioCarrera servCarr;
    private servicioCiclo servCiclos;
    private servicioCursos servCursos;
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
        servAlum = new ServicioAlumno();
        servicioGrupo = new ServicioGrupo();
        servCarr = new servicioCarrera();
        servCiclos = new servicioCiclo();
        servCursos = new servicioCursos();
    }
    //ALUMNOS
    ////////////////////////////////////ALUMNOS/////////////////////
    public void insertarAlumno(Alumno alumno) throws Exception{
        servAlum.insertarAlumno(alumno);
    }
    
    public void modificarAlumno(Alumno alumno) throws Exception{
        servAlum.editarAlumno(alumno);
    }
    
   public void eliminarAlumno(String cedula) throws Exception{
        servAlum.eliminarAlumno(cedula);
    }
    
    public List<Alumno> buscarAlumno(String cedula) throws GlobalException, NoDataException{
        return (List<Alumno>)servAlum.buscarAlumno(cedula); 
    }
    
    public List<Alumno> buscarAlumnoNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Alumno>)servAlum.buscarAlumnoNombre(nombre); 
    }
    
    public List<Alumno> buscarAlumnoCedula(String cedula) throws GlobalException, NoDataException{
        return (List<Alumno>)servAlum.buscarAlumnoCedula(cedula); 
    }
    
    public List<Alumno> listarAlumnos() throws GlobalException, NoDataException{
       return (List<Alumno>) servAlum.listarAlumnos();
    }
    //PROFESORES
    public boolean insertarProfesor(Profesor profesor) throws GlobalException, SQLException{
        return servicioProfesor.insertarProfesor(profesor);
    }
    public boolean eliminarProfesor(String cedula) throws GlobalException,SQLException{
        return servicioProfesor.eliminarProfesor(cedula);
    }
    public boolean editarProfesor(Profesor profesor) throws GlobalException,SQLException{
        return servicioProfesor.editarProfesor(profesor);
    }
    public List<Profesor> listaProfesores(){
        return servicioProfesor.listaProfesores();
    }
//    public Profesor buscarProfesor(String cedula){
//        return servicioProfesor.getProfesor(cedula);
//    }
//    public List<Profesor> buscarProfesores(String busqueda){
//        return servicioProfesor.buscarProfesores(busqueda);
//    }
//    public List<Profesor> buscarProfesoresPor(String tipo,String busqueda)throws GlobalException, SQLException{
//        return servicioProfesor.buscarProfesoresPor(tipo,busqueda);
//    }
    //GRUPOS
    public void ingresarGrupo(Grupo grupo) throws GlobalException, SQLException, NoDataException{
        servicioGrupo.insertarGrupo(grupo);
    }
    public void eliminarGrupo(String codigo) throws GlobalException,SQLException, NoDataException{
        servicioGrupo.eliminarGrupo(codigo);
    }
//    public void editarGrupo(Grupo grupo) throws GlobalException, SQLException{
//        servicioGrupo.editarGrupo(grupo);
//    }
    
    public void modificarGrupo(Grupo grupo) throws GlobalException, SQLException, NoDataException{
        servicioGrupo.modificarGrupo(grupo);
    }
        
    public List<Grupo> listarGrupos() throws GlobalException, NoDataException{
        return servicioGrupo.listarGrupos();
    }
    
    
    public void insertarCarrera(Carrera carrera) throws Exception{
        servCarr.insertarCarrera(carrera);
    }
    
    public void modificarCarrera(Carrera carrera) throws Exception{
        servCarr.modificarCarrera(carrera);
    }
    
   public void eliminarCarrera(String codigo) throws Exception{
        servCarr.eliminarCarrera(codigo);
    }
    
    public List<Carrera> buscarCarreraCodigo(String codigo) throws GlobalException, NoDataException{
        return (List<Carrera>)servCarr.buscarCarreraCodigo(codigo); 
    }
   
    public List<Carrera> buscarCarreraNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Carrera>)servCarr.buscarCarreraNombre(nombre); 
    }
    
    public List<Carrera> listarCarreras(){
        return servCarr.listarCarreras();
    }
    
//    public List<Carrera> listarCarreras() throws GlobalException, NoDataException{
//       return  (List<Carrera>) servCarr.listarCarreras();
//   }
    

    
    public void insertarCiclo(Ciclo ciclo) throws Exception{
        servCiclos.insertarCiclo(ciclo);
    }
    
    public void modificarCiclo(Ciclo ciclo) throws Exception{
        servCiclos.modificarCiclo(ciclo);
    }
    
   public void eliminarCiclo(String codigo) throws Exception{
        servCiclos.eliminarCiclo(codigo);
    }
       
    public Ciclo buscarCiclo(String codigo) throws GlobalException, NoDataException{
        return servCiclos.buscarCiclo(codigo); 
    }
    
    public Carrera buscarCarrera(String codigo) throws GlobalException, NoDataException{
        return servCarr.buscarCarrera(codigo); 
    }
//    public List<Ciclo> listarCiclos() throws GlobalException, NoDataException{
//       return (List<Ciclo>)servCiclos.listarCiclos();
//   }
    
   public List<Ciclo> listarCiclos(){
        return servCiclos.listarCiclos();
    }
    
    public List<Ciclo> buscarCicloCodigo(String codigo) throws GlobalException, NoDataException{
        return (List<Ciclo>)servCiclos.buscarCodigoCiclo(codigo); 
    }
   
    public List<Ciclo> buscarCicloAno(String ano) throws GlobalException, NoDataException{
        return (List<Ciclo>)servCiclos.buscarCicloAno(ano); 
    }
    
    
     
    public void insertarCurso(Curso curso) throws Exception{
        servCursos.insertarCurso(curso);
    }
    
    public void modificarCurso(Curso curso) throws Exception{
        servCursos.modificarCurso(curso);
    }
    
   public void eliminarCurso(String codigo) throws Exception{
        servCursos.eliminarCurso(codigo);
    }
           
//    public List<Curso> listarCursos() throws GlobalException, NoDataException{
//       return (List<Curso>) servCursos.listarCursos();
//   }
    public List<Curso> listarCursos() throws GlobalException, NoDataException{
        return servCursos.listarCursos();
    }
      
    public List<Curso> buscarCursoCodigo(String codigo) throws GlobalException, NoDataException{
        return (List<Curso>)servCursos.buscarCursoCodigo(codigo); 
    }
    
    public List<Curso> buscarCursoNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Curso>)servCursos.buscarCursoNombre(nombre); 
    }
    
    public List<Curso> buscarCursoCarrera(String carrera) throws GlobalException, NoDataException{
        return (List<Curso>)servCursos.buscarCursoCarrera(carrera); 
    }
    
    public Carrera buscarCarreraPorNombre(String nombre) throws GlobalException, NoDataException{
        return servCarr.buscarCarreraPorNombre(nombre); 
    }
    public Curso buscarCurso(String codigo) throws GlobalException, NoDataException{
        return servCursos.buscarCurso(codigo); 
    }
    
    public Profesor buscarProfesor(String codigo) throws GlobalException, NoDataException{
        return servicioProfesor.buscarProfesor(codigo); 
    }
    
   public List<Profesor> buscarProfesorCedula(String cedula) throws GlobalException, NoDataException{
        return (List<Profesor>)servicioProfesor.buscarProfesorCedula(cedula); 
    }
   
    public List<Profesor> buscarProfesorNombre(String nombre) throws GlobalException, NoDataException{
        return (List<Profesor>)servicioProfesor.buscarProfesorNombre(nombre); 
    }

}
