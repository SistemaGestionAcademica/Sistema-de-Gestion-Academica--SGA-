/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Ciclo;
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
@WebServlet(name = "Ciclos", urlPatterns = {"/Ciclos"})
public class Ciclos extends HttpServlet {
    
    private Control control = new Control();
    String error = null, exito = null, exitoE = null;
    List<Ciclo> ciclos;

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
        //processRequest(request, response);
        
                switch(action){
                    case "buscar":
                        String options = request.getParameter("options");
                        String search = request.getParameter("idBusqueda");
                        if(options.length() == 0 && search.length() == 0 ){
                            ciclos = getListCiclos();
                        }
                        else{
                            if("codigo".equals(options)){                                
                                try {
                                    ciclos = buscarCicloCodigo(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else{
                                try {
                                    ciclos = buscarCicloAno(search);
                                } catch (GlobalException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoDataException ex) {
                                    Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                                }                                
                            }

                        }
                        //ciclos = getListCiclos();
                        request.setAttribute("cicloListar", ciclos);
                        request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                        break;
                    
                    case "editar":
                        String codigoEditar = request.getParameter("codigo");
                        String numeroEditar = request.getParameter("numero");
                        ciclos = getListCiclos();
                        Ciclo cicloEditar = null;
                        if(numeroEditar == null){
                            if(buscarCiclos(ciclos,codigoEditar)!= null){
                                cicloEditar = buscarCiclos(ciclos,codigoEditar);
                            }
                            request.setAttribute("cicloEditar", cicloEditar);
                            request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                        }
                        else{
                            cicloEditar = new Ciclo();
                            cicloEditar.setCodigo(request.getParameter("codigo"));
                            String f_inicio = request.getParameter("f_inicio");
                            java.sql.Date fechaI=java.sql.Date.valueOf(f_inicio);
                            cicloEditar.setFecha_inicio(fechaI);
                            //int numero = parseInt(request.getParameter("numero"));
                            cicloEditar.setNumero(request.getParameter("numero").charAt(0));                            
                            String f_final = request.getParameter("f_final");
                            java.sql.Date fechaF=java.sql.Date.valueOf(f_final);
                            cicloEditar.setFecha_finalizacion(fechaF);
                            cicloEditar.setEstado(request.getParameter("estado").charAt(0));
                            cicloEditar.setAno(request.getParameter("ano"));
                                                       
                            try {
                                if(modificarCiclo(cicloEditar)){
                                    request.setAttribute("result", "ExitoEditar");
                                    request.setAttribute("value", cicloEditar.getCodigo());
                                    request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                                }
                                else{
                                    request.setAttribute("result", "ErrorEditar");
                                    request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        
                        
//                        String idEditar = request.getParameter("idE");
//                        String f_inicioEditar = request.getParameter("f_inicioE");
//                        char numeroEditar = request.getParameter("numeroE").charAt(0);
//                        String f_finalEditar = request.getParameter("f_finalE");
//                        char estadoEditar = request.getParameter("estadoE").charAt(0);
//                        String anoEditar = request.getParameter("anoE");
//
//                        java.sql.Date fechaI=java.sql.Date.valueOf(f_inicioEditar);
//                        java.sql.Date fechaF=java.sql.Date.valueOf(f_finalEditar);
//                        
//                        ciclos = listarCiclos();
//                        Ciclo cicloEditar = null;
                        
//                        if(nombreEditar == null){
//                            if(buscarProfesores(profesores,cedulaEditar)!= null){
//                                profesorEditar = buscarProfesores(profesores,cedulaEditar);
//                            }
//                            request.setAttribute("profesorEditar", profesorEditar);
//                            request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
//                        }
//                        else{
//                            profesorEditar = new Profesor();
//                            profesorEditar.setCedula(request.getParameter("cedula"));
//                            profesorEditar.setNombre(request.getParameter("nombre"));
//                            profesorEditar.setTelefono(request.getParameter("telefono"));
//                            profesorEditar.setEmail(request.getParameter("email"));
//                            
//                            if(editarProfesor(profesorEditar)){
//                                request.setAttribute("result", "ExitoEditar");
//                                request.setAttribute("value", profesorEditar.getNombre());
//                                request.getRequestDispatcher("/Profesores.jsp").forward(request, response);
//                            }
//                            else{
//                                request.setAttribute("result", "ErrorEditar");
//                                request.getRequestDispatcher("/Profesores.jsp").forward(request, response); 
//                            }
//                        }

                        break;
                    case "eliminar":
                        String idEliminar = request.getParameter("codigo");
        
                        try {
                            if(eliminarCiclo(idEliminar)){
                                request.setAttribute("result", "ExitoEliminar");
                                request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorEliminar");
                                request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        break;
                    case "crear":
                        Ciclo c = new Ciclo();
                        try{
                            
                            c.setCodigo(request.getParameter("codigo"));
                            String f_inicio = request.getParameter("f_inicio");
                            java.sql.Date fechaI=java.sql.Date.valueOf(f_inicio);
                            c.setFecha_inicio(fechaI);
                            //int numero = parseInt(request.getParameter("numero"));
                            c.setNumero(request.getParameter("numero").charAt(0));                            
                            String f_final = request.getParameter("f_final");
                            java.sql.Date fechaF=java.sql.Date.valueOf(f_final);
                            c.setFecha_finalizacion(fechaF);
                            c.setEstado(request.getParameter("estado").charAt(0));
                            c.setAno(request.getParameter("ano"));
                           
                            if(ingresarCiclo(c)){
                                request.setAttribute("result", "ExitoCrear");
                                request.setAttribute("value", c.getCodigo());
                                request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorCrear");
                                request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
                            }
                        }
                        catch(Exception ex){
                            request.setAttribute("result", "ErrorCrear");
                            request.getRequestDispatcher("/Ciclos.jsp").forward(request, response);
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
    
       public List<Ciclo> getListCiclos(){
        return control.listarCiclos();      
    }
   
    
    public boolean eliminarCiclo(String id) throws Exception{
        try{          
            control.eliminarCiclo(id);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean ingresarCiclo(Ciclo c) throws Exception{
        try{          
            control.insertarCiclo(c);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean modificarCiclo(Ciclo c) throws Exception{
        try{          
            control.modificarCiclo(c);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public Ciclo buscarCiclos(List<Ciclo> cicloList, String cicloCodigo){
        for (Ciclo ciclo : cicloList) {
            if (ciclo.getCodigo().equals(cicloCodigo)) {
                return ciclo;
            }
        }
        return null;
    }

    public List<Ciclo> buscarCicloCodigo(String cod) throws GlobalException, NoDataException{
        return control.buscarCicloCodigo(cod);
    }
    
    public List<Ciclo> buscarCicloAno(String ano) throws GlobalException, NoDataException{
        return control.buscarCicloAno(ano);
    }
}
