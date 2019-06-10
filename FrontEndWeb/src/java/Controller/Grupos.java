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
@WebServlet(name = "Grupos", urlPatterns = {"/Grupos"})
public class Grupos extends HttpServlet {
    private Control control = new Control();
    List<Grupo> grupos;
    List<Curso> cursos;
    List<Profesor> profesores;
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
                        grupos = getListGrupos();
                    }else{
                        grupos = getListGrupos();
                    }
                   
                    request.setAttribute("grupoListar", grupos);
                    request.getRequestDispatcher("/Grupos.jsp").forward(request, response); 
                }catch(Exception e){
                    log(""+e);
                }
                break;
            case "editar":
                        String codigoEditar = request.getParameter("codigo");
                        String horarioEditar = request.getParameter("horario");                        
        
                        try {
                            grupos = getListGrupos();
                        } catch (GlobalException ex) {
                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoDataException ex) {
                            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                        Grupo grupoEditar = null;
                        if(horarioEditar == null){
                            if(buscarGrupo(grupos,codigoEditar)!= null){
                                grupoEditar = buscarGrupo(grupos,codigoEditar);
                            }
                            request.setAttribute("grupoEditar", grupoEditar);
                            request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                        }
                        else{
                            grupoEditar = new Grupo();
                            
                            grupoEditar.setCodigo(request.getParameter("codigo"));
                            try {
                                grupoEditar.setCurso(buscarCurso(request.getParameter("curso")));
                            } catch (GlobalException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                grupoEditar.setProfesor(buscarProfesor(request.getParameter("profesor")));
                            } catch (GlobalException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            grupoEditar.setHorario(request.getParameter("horario"));
                                                                               
                            try {
                                if(modificarGrupo(grupoEditar)){
                                    request.setAttribute("result", "ExitoEditar");
                                    request.setAttribute("value", grupoEditar.getCodigo());
                                    request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                                }
                                else{
                                    request.setAttribute("result", "ErrorEditar");
                                    request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        break;
            case "eliminar":
                try{
                    String codEliminar  = request.getParameter("codigo");

                    if(eliminarGrupo(codEliminar)){
                        request.setAttribute("result", "ExitoEliminar");
                        request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                    }else{
                        request.setAttribute("result", "ErrorEliminar");    
                        request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                    }
                }catch(Exception e){
                    log(""+e);
                }
                break;
            case "crear":
                Grupo g = null;
                try{
                    g = new Grupo();
                    g.setCodigo(request.getParameter("codigo"));
                    g.setCurso(buscarCurso(request.getParameter("curso")));
                    g.setProfesor(buscarProfesor(request.getParameter("profesor")));                   
                    g.setHorario(request.getParameter("horario"));
                    if(ingresarGrupo(g)){
                        request.setAttribute("result", "ExitoCrear");
                        request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                    }else{
                        request.setAttribute("result", "ErrorCrear");
                        request.setAttribute("value", g.getCodigo());
                        request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
                    }
                }catch(Exception e){
                    log(""+e);
                    request.setAttribute("result", "ErrorCrear");
                    request.getRequestDispatcher("/Grupos.jsp").forward(request, response);
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

    public List<Grupo> getListGrupos() throws GlobalException, NoDataException{
        return control.listarGrupos();
    }
    
    public List<Curso> getListCursos() throws Exception{
        try{
            return control.listarCursos();      
        }catch(GlobalException ex){
            System.out.println(""+ex);
            return null;
        }
    }
        
    public List<Profesor> getListProfesores() throws Exception{
        return control.listaProfesores();
    }
    
    public boolean eliminarGrupo(String codigo) throws Exception{
        try{          
            control.eliminarGrupo(codigo);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean ingresarGrupo(Grupo a) throws Exception{
        try{          
            control.ingresarGrupo(a);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public boolean modificarGrupo(Grupo g) throws Exception{
        try{          
            control.modificarGrupo(g);    
            return true;
        }
        catch(GlobalException | SQLException ex){
            return false;
        }
    } 
    
    public Grupo buscarGrupo(List<Grupo> alumnoList, String codigo){
        for (Grupo grupo : alumnoList) {
            if (grupo.getCodigo().equals(codigo)) {
                return grupo;
            }
        }
        return null;
    }
    

    
    public Curso buscarCurso(String id) throws GlobalException, NoDataException {

        return (Curso)control.buscarCurso(id);
    }
    
    public Profesor buscarProfesor(String id) throws GlobalException, NoDataException {

        return (Profesor)control.buscarProfesor(id);
    }
    
    public ArrayList listarCursos() throws GlobalException, NoDataException {
        ArrayList<Curso> array;
        array = (ArrayList<Curso>) control.listarCursos();
        return array;
    }
    
    public ArrayList listarProfesores() throws GlobalException, NoDataException {
        ArrayList<Profesor> array;
        array = (ArrayList<Profesor>) control.listaProfesores();
        return array;
    }
    
}