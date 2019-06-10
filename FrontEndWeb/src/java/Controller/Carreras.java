/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Carrera;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author david
 */
@WebServlet(name = "Carreras", urlPatterns = {"/Carreras"})
public class Carreras extends HttpServlet {
    
    private Control control = new Control();
    String error = null, exito = null, exitoE = null;
    List<Carrera> carreras;

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
        //processRequest(request, response);
        
                switch(action){
                    case "buscar":
                        String options = request.getParameter("options");
                        String search = request.getParameter("idBusqueda");
                        if(options.length() == 0 && search.length() == 0 ){
                            carreras = getListCarreras();
                        }
                        else{
                            if("codigo".equals(options)){                                
                                try {
                                    carreras = buscarCarreraCodigo(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else{
                                try {
                                    carreras = buscarCarreraNombre(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }                                
                            }

                        }
                        request.setAttribute("carreraListar", carreras);
                        request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                        break;
                    
                    case "editar":
                        String codigoEditar = request.getParameter("codigo");
                        String nombreEditar = request.getParameter("nombre");
                        carreras = getListCarreras();
                        Carrera carreraEditar = null;
                        if(nombreEditar == null){
                            if(buscarCarrera(carreras,codigoEditar)!= null){
                                carreraEditar = buscarCarrera(carreras,codigoEditar);
                            }
                            request.setAttribute("carreraEditar", carreraEditar);
                            request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                        }
                        else{
                            carreraEditar = new Carrera();
                            carreraEditar.setCodigo(request.getParameter("codigo"));
                            carreraEditar.setNombre(request.getParameter("nombre"));
                            carreraEditar.setTitulo(request.getParameter("titulo"));
                                                         
                            try {
                                if(modificarCarrera(carreraEditar)){
                                    request.setAttribute("result", "ExitoEditar");
                                    request.setAttribute("value", carreraEditar.getCodigo());
                                    request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                                }
                                else{
                                    request.setAttribute("result", "ErrorEditar");
                                    request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Carreras.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;
                    case "eliminar":
                        String idEliminar = request.getParameter("codigo");
        
                        try {
                            if(eliminarCarrera(idEliminar)){
                                request.setAttribute("result", "ExitoEliminar");
                                request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorEliminar");
                                request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(Carreras.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                        
       
                        break;
                    case "crear":
                        Carrera c = new Carrera();
                        try{
                            
                            c.setCodigo(request.getParameter("codigo"));
                            c.setNombre(request.getParameter("nombre"));
                            c.setTitulo(request.getParameter("titulo"));
                           
                            if(ingresarCarrera(c)){
                                request.setAttribute("result", "ExitoCrear");
                                request.setAttribute("value", c.getCodigo());
                                request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorCrear");
                                request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
                            }
                        }
                        catch(Exception ex){
                            request.setAttribute("result", "ErrorCrear");
                            request.getRequestDispatcher("/Carreras.jsp").forward(request, response);
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

    public List<Carrera> getListCarreras(){
        return control.listarCarreras();      
    }
   
    
    public boolean eliminarCarrera(String id) throws Exception{
        try{          
            control.eliminarCarrera(id);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean ingresarCarrera(Carrera c) throws Exception{
        try{          
            control.insertarCarrera(c);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean modificarCarrera(Carrera c) throws Exception{
        try{          
            control.modificarCarrera(c);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public Carrera buscarCarrera(List<Carrera> carreraList, String carreraCodigo){
        for (Carrera carrera : carreraList) {
            if (carrera.getCodigo().equals(carreraCodigo)) {
                return carrera;
            }
        }
        return null;
    }
    
    public List<Carrera> buscarCarreraCodigo(String cod) throws GlobalException, NoDataException{
        return control.buscarCarreraCodigo(cod);
    }
    
    public List<Carrera> buscarCarreraNombre(String cod) throws GlobalException, NoDataException{
        return control.buscarCarreraNombre(cod);
    }
}
