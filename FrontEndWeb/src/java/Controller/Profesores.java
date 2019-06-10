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
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven Villalobos
 */
@WebServlet(name = "Profesores", urlPatterns = {"/Profesores"})
public class Profesores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Control control = new Control();
    String error = null, exito = null, exitoE = null;
    List<Profesor> profesores;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String action = request.getParameter("action");
                out.println(action);
                switch(action){
                    case "buscar":
                        String options = request.getParameter("options");
                        String search = request.getParameter("idBusqueda");
                        if(options.length() == 0 && search.length() == 0 ){
                            profesores = getListProfesores();
                        }
                        else{
                             if("cedula".equals(options)){                                
                                try {
                                    profesores = buscarProfesorCedula(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else{
                                try {
                                    profesores = buscarProfesorNombre(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }                                
                            }
                        }

                        request.setAttribute("profesorListar", profesores);
                        request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                        break;
                    case "editar":
                        String cedulaEditar = request.getParameter("cedula");
                        String nombreEditar = request.getParameter("nombre");
                        profesores = getListProfesores();
                        Profesor profesorEditar = null;
                        if(nombreEditar == null){
                            if(buscarProfesores(profesores,cedulaEditar)!= null){
                                profesorEditar = buscarProfesores(profesores,cedulaEditar);
                            }
                            request.setAttribute("profesorEditar", profesorEditar);
                            request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                        }
                        else{
                            profesorEditar = new Profesor();
                            profesorEditar.setCedula(request.getParameter("cedula"));
                            profesorEditar.setNombre(request.getParameter("nombre"));
                            profesorEditar.setTelefono(request.getParameter("telefono"));
                            profesorEditar.setEmail(request.getParameter("email"));
                            
                            if(editarProfesor(profesorEditar)){
                                request.setAttribute("result", "ExitoEditar");
                                request.setAttribute("value", profesorEditar.getNombre());
                                request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorEditar");
                                request.getRequestDispatcher("/Profesores.jsp").forward(request, response); 
                            }
                        }

                        break;
                    case "eliminar":
                        String cedulaEliminar = request.getParameter("cedula");
                        if(eliminarProfesor(cedulaEliminar)){
                            request.setAttribute("result", "ExitoEliminar");
                            request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                        }
                        else{
                            request.setAttribute("result", "ErrorEliminar");
                            request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                        }
                        break;
                    case "crear":
                        Profesor profesor = new Profesor();
                        try{
                            profesor.setCedula(request.getParameter("cedula"));
                            profesor.setNombre(request.getParameter("nombre"));
                            profesor.setTelefono(request.getParameter("telefono"));
                            profesor.setEmail(request.getParameter("email"));
                            if(control.insertarProfesor(profesor)){
                                request.setAttribute("result", "ExitoCrear");
                                request.setAttribute("value", profesor.getNombre());
                                request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorCrear");
                                request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                            }
                        }
                        catch(Exception ex){
                            request.setAttribute("result", "ErrorCrear");
                            request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
                        }
                        
                        break;
                    default:
                        
                        break;
                }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public List<Profesor> getListProfesores(){
        return control.listaProfesores();      
    }
//    public List<Profesor> buscarProfesores(String busqueda){
//        return control.buscarProfesores(busqueda);      
//    }
    public boolean eliminarProfesor(String cedula){
        try{          
            return control.eliminarProfesor(cedula);      
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    public boolean editarProfesor(Profesor profesor){
        try{          
            return control.editarProfesor(profesor);      
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public List<Profesor> buscarProfesorCedula(String cedula) throws GlobalException, NoDataException{
        return control.buscarProfesorCedula(cedula);
    }
    
    public List<Profesor> buscarProfesorNombre(String nombre) throws GlobalException, NoDataException{
        return control.buscarProfesorNombre(nombre);
    }
//    public Profesor buscarProfesor(String cedula){
//        try{
//            return control.buscarProfesor(cedula);
//        }
//        catch(Exception ex){
//            return null;
//        }
//    }
    public Profesor buscarProfesores(List<Profesor> profesoresList, String profesorCedula){
        for (Profesor prof : profesoresList) {
            if (prof.getCedula().equals(profesorCedula)) {
                return prof;
            }
        }
        return null;
    }
//    public List<Profesor> buscarProfesoresPor(String tipo, String busqueda){
//        try{
//            return control.buscarProfesoresPor(tipo,busqueda);
//        }
//        catch(GlobalException | SQLException ex){
//            return null;
//        }
//    }
}
