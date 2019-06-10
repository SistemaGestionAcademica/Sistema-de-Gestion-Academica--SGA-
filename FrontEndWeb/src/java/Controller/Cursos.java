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
import Entidades.Ciclo;
import Entidades.Curso;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author david
 */
@WebServlet(name = "Cursos", urlPatterns = {"/Cursos"})
public class Cursos extends HttpServlet {

    private Control control = new Control();
    String error = null, exito = null, exitoE = null;
    List<Curso> cursos;
    Carrera carrera;
    
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
                        try {
                            cursos = getListCursos();
                        } catch (GlobalException ex) {
                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoDataException ex) {
                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                        else{
                                if("codigo".equals(options)){     
                                    try {
                                            cursos = buscarCursoCodigo(search);
                                        } catch (GlobalException ex) {
                                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (NoDataException ex) {
                                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                        }                                    
                                }else if("nombre".equals(options)){
                                        try {
                                                cursos = buscarCursoNombre(search);
                                            } catch (GlobalException ex) {
                                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (NoDataException ex) {
                                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                            }                                     
                                }else{
                                        try {
                                                carrera = buscarCarreraPorNombre(search);
                                            } catch (GlobalException ex) {
                                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (NoDataException ex) {
                                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                            }

                                            String codCarrera = carrera.getCodigo();
                                            
                                        try {
                                            cursos = buscarCursoCarrera(codCarrera);
                                        } catch (GlobalException ex) {
                                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (NoDataException ex) {
                                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                                        }  
                                }                                
                        }        
        
                        request.setAttribute("cursoListar", cursos);
                        request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                        break;

                    case "crear":
                        Curso c = new Curso();
                        try{
                            c.setCodigo(request.getParameter("codigo"));
                            c.setCodigo_ciclo(buscarCiclo(request.getParameter("codigoCiclo")));
                            c.setCodigo_carrera(buscarCarrera(request.getParameter("codigoCarrera")));
                            c.setNombre(request.getParameter("nombre"));
                            c.setCreditos(Integer.parseInt(request.getParameter("creditos")));
                            c.setHoras(request.getParameter("horas"));
                           
                            if(ingresarCurso(c)){
                                request.setAttribute("result", "ExitoCrear");
                                request.setAttribute("value", c.getCodigo());
                                request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorCrear");
                                request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                            }
                        }
                        catch(Exception ex){
                            request.setAttribute("result", "ErrorCrear");
                            request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                        }
                        
                        break;
                       
                    case "eliminar":
                        String idEliminar = request.getParameter("codigo");
        
                        try {
                            if(eliminarCurso(idEliminar)){
                                request.setAttribute("result", "ExitoEliminar");
                                request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("result", "ErrorEliminar");
                                request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        break;
                        
                    case "editar":
                        String codigoEditar = request.getParameter("codigo");
                        String nombreEditar = request.getParameter("nombre");
        
                        try {
                            cursos = getListCursos();
                        } catch (GlobalException ex) {
                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoDataException ex) {
                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                        Curso cursoEditar = null;
                        if(nombreEditar == null){
                            if(buscarCurso(cursos,codigoEditar)!= null){
                                cursoEditar = buscarCurso(cursos,codigoEditar);
                            }
                            request.setAttribute("cursoEditar", cursoEditar);
                            request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                        }
                        else{
                            cursoEditar = new Curso();
                            
                            cursoEditar.setCodigo(request.getParameter("codigo"));
                            try {
                                cursoEditar.setCodigo_ciclo(buscarCiclo(request.getParameter("codigoCiclo")));
                            } catch (GlobalException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                cursoEditar.setCodigo_carrera(buscarCarrera(request.getParameter("codigoCarrera")));
                            } catch (GlobalException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            cursoEditar.setNombre(request.getParameter("nombre"));
                            cursoEditar.setCreditos(Integer.parseInt(request.getParameter("creditos")));
                            cursoEditar.setHoras(request.getParameter("horas"));
                                                       
                            try {
                                if(modificarCurso(cursoEditar)){
                                    request.setAttribute("result", "ExitoEditar");
                                    request.setAttribute("value", cursoEditar.getCodigo());
                                    request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                                }
                                else{
                                    request.setAttribute("result", "ErrorEditar");
                                    request.getRequestDispatcher("/Cursos.jsp").forward(request, response);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Ciclos.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
    
    public List<Curso> getListCursos() throws GlobalException, NoDataException{
        return control.listarCursos();      
    }
    
    public ArrayList listarCiclos() throws GlobalException, NoDataException {
        ArrayList<Ciclo> array;
        array = (ArrayList<Ciclo>) control.listarCiclos();
        return array;
    }

    public ArrayList listarCarreras() throws GlobalException, NoDataException {
        ArrayList<Carrera> array;
        array = (ArrayList<Carrera>) control.listarCarreras();
        return array;
    }
    
    public Carrera buscarCarrera(String id) throws GlobalException, NoDataException {

        return (Carrera) control.buscarCarrera(id);
    }
    
    public Ciclo buscarCiclo(String id) throws GlobalException, NoDataException {

        return (Ciclo)control.buscarCiclo(id);
    }
    
    public boolean ingresarCurso(Curso c) throws Exception{
        try{          
            control.insertarCurso(c);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    }
    
    public boolean modificarCurso(Curso c) throws Exception{
        try{          
            control.modificarCurso(c);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    }
    
    public boolean eliminarCurso(String id) throws Exception{
        try{          
            control.eliminarCurso(id);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public Curso buscarCurso(List<Curso> cursoList, String cursoCodigo){
        for (Curso curso : cursoList) {
            if (curso.getCodigo().equals(cursoCodigo)) {
                return curso;
            }
        }
        return null;
    }
    
    public List<Curso> buscarCursoCodigo(String cod) throws GlobalException, NoDataException{
        return control.buscarCursoCodigo(cod);
    }
    
    public List<Curso> buscarCursoNombre(String nom) throws GlobalException, NoDataException{
        return control.buscarCursoNombre(nom);
    }

    public List<Curso> buscarCursoCarrera(String carrera) throws GlobalException, NoDataException{
        return control.buscarCursoCarrera(carrera);
    }
    
    public Carrera buscarCarreraPorNombre(String nombre) throws GlobalException, NoDataException{
        return control.buscarCarreraPorNombre(nombre);
    }
}
