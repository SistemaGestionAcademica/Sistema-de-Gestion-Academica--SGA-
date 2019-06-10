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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
 * @author Usuario1
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/Alumnos"})
public class Alumnos extends HttpServlet {
    
    private Control control = new Control();
    String error = null, exito = null, exitoE = null;
    List<Alumno> alumnos;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        switch(action){
            case "buscar":
            try{
                String options = request.getParameter("options");
                String search = request.getParameter("idBusqueda");
                if(options.length() == 0 && search.length() == 0){
                    alumnos = getListAlumnos();
                }else{
                    if("cedula".equals(options)){                                
                                try {
                                    alumnos = buscarAlumnoCedula(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else{
                                try {
                                    alumnos = buscarAlumnoNombre(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }                                
                            }
                }
                request.setAttribute("alumnoListar", alumnos);
                request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);
            }catch(Exception e){
                System.out.println(""+e);
            }
            break;
            case "editar":
            try{
                String cedulaEditar = request.getParameter("cedula");
                String nombreEditar = request.getParameter("nombre");
                alumnos = getListAlumnos();
                Alumno alumnoEditar = null;
                if(nombreEditar == null){
                    if(buscarAlumno(alumnos, cedulaEditar) != null){
                        alumnoEditar = buscarAlumno(alumnos, cedulaEditar);
                    }
                    request.setAttribute("alumnoEditar", alumnoEditar);
                    request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);
                }else{
                    alumnoEditar = new Alumno();
                    alumnoEditar.setCedula(request.getParameter("cedula"));
                    alumnoEditar.setNombre(request.getParameter("nombre"));
                    alumnoEditar.setTelefono(request.getParameter("telefono"));
                    alumnoEditar.setEmail(request.getParameter("email"));
                    String jspFecha = request.getParameter("fecha");
                    java.sql.Date sqlDate = Date.valueOf(jspFecha);
                    alumnoEditar.setFecha(sqlDate);
                    
                    if(modificarAlumno(alumnoEditar)){
                        request.setAttribute("result", "ExitoEditar");
                        request.setAttribute("value", alumnoEditar.getCedula());
                        request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);
                    }else{
                        request.setAttribute("result", "ErrorEditar");
                        request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);
                    }
                }
            }catch(Exception e){
                System.out.println(""+e);
                Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, e);
            }
            break;
            case "eliminar":
            try{
                String cedEliminar = request.getParameter("cedula");
                
                if(eliminarAlumno(cedEliminar)){
                    request.setAttribute("result", "ExitoEliminar");
                    request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);
                }else{
                    request.setAttribute("result", "ErrorEliminar");
                    request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);                
                }
                
            }catch(Exception e){
                System.out.println(""+e);
                Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, e);
            }
            break;
            case "crear":
            Alumno a = null;
            try{
                a = new Alumno();
                a.setCedula(request.getParameter("cedula"));
                a.setNombre(request.getParameter("nombre"));
                a.setTelefono(request.getParameter("telefono"));
                a.setEmail(request.getParameter("email"));
                
                String jspFecha = request.getParameter("fecha");
                java.sql.Date sqlDate = Date.valueOf(jspFecha);
                a.setFecha(sqlDate);
                
                if(ingresarAlumno(a)){
                    request.setAttribute("result", "ExitoCrear");
                    request.setAttribute("value", a.getCedula());
                    request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);                          
                }else{
                    request.setAttribute("result", "ErrorCrear");
                    request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);                
                }
            }catch(Exception e){
                System.out.println("Error "+e);
                request.setAttribute("result", "ErrorCrear");
                request.getRequestDispatcher("/Alumnos.jsp").forward(request, response);
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

    public List<Alumno> getListAlumnos() throws Exception{
        try{
            return control.listarAlumnos();      
        }catch(GlobalException ex){
            System.out.println(""+ex);
            return null;
        }
    }
   
    
    public boolean eliminarAlumno(String id) throws Exception{
        try{          
            control.eliminarAlumno(id);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean ingresarAlumno(Alumno a) throws Exception{
        try{          
            control.insertarAlumno(a);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean modificarAlumno(Alumno a) throws Exception{
        try{          
            control.modificarAlumno(a);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 

    public List<Alumno> buscarAlumnoCedula(String cedula) throws GlobalException, NoDataException{
        return control.buscarAlumnoCedula(cedula);
    }
    
    public List<Alumno> buscarAlumnoNombre(String nombre) throws GlobalException, NoDataException{
        return control.buscarAlumnoNombre(nombre);
    }

   public Alumno buscarAlumno(List<Alumno> alumnoList, String alumnoCedula){
        for (Alumno alumno : alumnoList) {
            if (alumno.getCedula().equals(alumnoCedula)) {
                return alumno;
            }
        }
        return null;
    }
}
