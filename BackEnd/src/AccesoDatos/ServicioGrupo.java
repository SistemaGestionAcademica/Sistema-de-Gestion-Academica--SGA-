/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Alumno;
import Entidades.Carrera;
import Entidades.Ciclo;
import Entidades.Curso;
import Entidades.Grupo;
import Entidades.Profesor;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Steven Villalobos
 */
public class ServicioGrupo extends Servicio{
    
    private static final String INSERTARGRUPO = "{call insertarGrupo(?,?,?,?)}";
    
    private static final String BUSCARGRUPO = "{?=call buscarGrupo(?)}";
    
    private static final String MODIFICARGRUPO = "{call modificarGrupo(?,?,?,?)}";
    
    private static final String LISTARGRUPOS = "{?=call listarGrupos()}";
    
    private static final String ELIMINARGRUPO = "{call eliminarGrupo(?)}";
    
    public ServicioGrupo() { }
    
    //////////////////////Para manejo de Referencias/////////////////////////////////
    
    public Carrera carrera(ResultSet rs) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }try {
            Carrera c= new Carrera();
            c.setCodigo(rs.getString("COD_CARRERA"));
            c.setNombre(rs.getString("NOMBRE_CARRERA"));
            c.setTitulo(rs.getString("TITULO"));
            return c;
        } catch (SQLException ex) {
            return null;
        }
    }
    public Ciclo ciclo(ResultSet rs) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }try {
            Ciclo c= new Ciclo();
            c.setCodigo(rs.getString("COD_CICLO"));
            c.setAno(rs.getString("ANO"));
            c.setEstado(rs.getString("ESTADO").charAt(0));
            c.setNumero(rs.getString("NUMERO").charAt(0));
            c.setFecha_inicio(rs.getDate("FECHA_INICIO"));
            c.setFecha_finalizacion(rs.getDate("FECHA_FINALIZACION"));
            return c;
        } catch (SQLException ex) {
            return null;
        }
    }



    private Curso curso(ResultSet rs) throws GlobalException, NoDataException{
        try {
            Curso c = new Curso();
            c.setCodigo(rs.getString("CODIGO"));
            c.setCodigo_ciclo(ciclo(rs));
            c.setCodigo_carrera(carrera(rs));
            c.setNombre(rs.getString("NOMBRE"));
            c.setCreditos(rs.getInt("CREDITOS"));
            c.setHoras(rs.getString("HORAS"));
            return c;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    private Profesor profesor(ResultSet rs) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        try {
                Profesor p = new Profesor();
            p.setCedula(rs.getString("CEDULA"));
            p.setNombre(rs.getString("NOMBRE_PROFESOR"));//
            p.setTelefono(rs.getString("TELEFONO"));//
            p.setEmail(rs.getString("EMAIL"));
            return p;
        } catch(SQLException e) {
            System.out.println("PROFESOR "+e+'\n');
            return null;
        }
    }
    

    public Grupo grupo(ResultSet rs) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        try{
            Grupo gru = new Grupo();
            gru.setCodigo(rs.getString("COD_GRUPO"));
            gru.setCurso(curso(rs));
            gru.setProfesor(profesor(rs));
            gru.setHorario(rs.getString("HORARIO"));
            return gru;
        }catch(SQLException e){
            System.out.println("GRUPO "+e+'\n');
            return null;
        }
    }
    
    /////////////////////////////////////////////////////////////////
    
    //Insertar grupo
    public void insertarGrupo(Grupo grupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        CallableStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARGRUPO);
            pstmt.setString(1,grupo.getCodigo());
            
            String codCurso = "";
            codCurso+=grupo.getCurso().getCodigo();
            pstmt.setString(2, codCurso);
            
            String cedProf = "";
            cedProf+=grupo.getProfesor().getCedula();
            pstmt.setString(3,cedProf);
            
            pstmt.setString(4,grupo.getHorario());
            
            boolean resultado = pstmt.execute();
            if(resultado == true){
                throw  new NoDataException("No se realizo la insercion del Grupo");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                desconectar();
            } catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }//FINAL DE INSERTAR GRUPO
    
    //Buscar Grupo
    public Collection buscarGrupo(String codigo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(BUSCARGRUPO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, codigo);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            while(rs.next()){
                coleccion.add(grupo(rs));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no valida");
        }
        finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
            }catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if(coleccion == null || coleccion.size()==0){
            throw new NoDataException("No hay datos");        
        }
        return coleccion;    
    }//FINAL DE BUSCAR GRUPO

    //Modificar Grupo
//    public void modificarGrupo(Grupo elgrupo) throws GlobalException, NoDataException {
//        try {
//            conectar();
//        } catch(ClassNotFoundException e) {
//            throw  new GlobalException("No se ha localizado el driver");
//        } catch (SQLException e) {
//            throw  new NoDataException("La base de datos no se encuentra disponible");
//        }
//        PreparedStatement pstmt = null;
//        try{
//            pstmt = conexion.prepareStatement(MODIFICARGRUPO);
//            pstmt.setString(1, elgrupo.getCodigo());
//            
//            String codCurso = "";
//            codCurso += elgrupo.getCurso().getCodigo();
//            pstmt.setString(2, codCurso);
//            
//            String cedProf = "";
//            cedProf += elgrupo.getProfesor().getCedula();
//            pstmt.setString(3, cedProf);
//            
//            pstmt.setString(4, elgrupo.getHorario());
//            
//            int resultado = pstmt.executeUpdate();
//            
//            if (resultado != 0){
//                System.out.println("\nModificación Satisfactoria!");
//            }
//            else{
//                throw new NoDataException("No se realizo la actualización");
//            }
//            
//        }catch(SQLException e){
//            throw new GlobalException("Sentencia no valida");
//        }finally{
//            try{
//                if(pstmt!=null){
//                    pstmt.close();
//                }
//                desconectar();
//            }catch(SQLException e){
//                throw new GlobalException("Estatutos invalidos o nulos");
//            }
//        }
//    }//final modificar grupo
    
        public boolean modificarGrupo(Grupo elgrupo) throws GlobalException,SQLException
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.");
        }
        CallableStatement pstmt=null;
        
        try 
        {
            pstmt = conexion.prepareCall(MODIFICARGRUPO);
                    pstmt.setString(1, elgrupo.getCodigo());

                    String codCurso = "";
                    codCurso += elgrupo.getCurso().getCodigo();
                    pstmt.setString(2, codCurso);

                    String cedProf = "";
                    cedProf += elgrupo.getProfesor().getCedula();
                    pstmt.setString(3, cedProf);

                    pstmt.setString(4, elgrupo.getHorario());
          
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el curso.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new GlobalException("Número de identificación duplicado.");
        } 
        finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                desconectar();
                
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Error al desconectar.");
            }
            return true;
        } 
    }

    public List listarGrupos() 	throws GlobalException, NoDataException
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            try{
                throw new GlobalException("No se ha localizado el driver.");
            } 
            catch (GlobalException ex)
            {
                Logger.getLogger(ServicioGrupo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (SQLException e) 
        {
            try {
                throw new GlobalException("La base de datos no se encuentra disponible.");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioGrupo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        CallableStatement pstmt=null;
        ResultSet rs = null;
	ArrayList coleccion = new ArrayList();
	//Curso curso = null;
        try
        {
            
                pstmt = conexion.prepareCall(LISTARGRUPOS);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                    coleccion.add(grupo(rs));
                }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            try {
                throw new GlobalException("Error al recuperar datos.\n");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                try {
                    throw new GlobalException("Estatutos invalidos o nulos.");
                } catch (GlobalException ex) {
                    Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (coleccion == null || coleccion.isEmpty())
        {
            try {
                throw new GlobalException("No hay datos.");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return coleccion;
        
    }
    //Listar Grupo
//    public List listarGrupos() 	throws GlobalException, NoDataException
//    {
//        try 
//        {
//            conectar();
//        } 
//        catch (ClassNotFoundException e) 
//        {
//            try{
//                throw new GlobalException("No se ha localizado el driver.");
//            } 
//            catch (GlobalException ex)
//            {
//                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } 
//        catch (SQLException e) 
//        {
//            try {
//                throw new GlobalException("La base de datos no se encuentra disponible.");
//            } catch (GlobalException ex) {
//                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        CallableStatement pstmt=null;
//        ResultSet rs = null;
//	ArrayList coleccion = new ArrayList();
//        try
//        {
//                pstmt = conexion.prepareCall(LISTARGRUPOS);
//                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
//                pstmt.execute();
//                rs = (ResultSet)pstmt.getObject(1);
//                while (rs.next())
//                {
//                    coleccion.add(grupo(rs));
//                }
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//            try {
//                throw new GlobalException("Error al recuperar datos.\n");
//            } catch (GlobalException ex) {
//                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        finally
//        {
//            try
//            {
//                if (rs != null)
//                {
//                    rs.close();
//                }
//                if (pstmt != null)
//                {
//                    pstmt.close();
//                }
//                desconectar();
//            }
//            catch (SQLException e)
//            {
//                try {
//                    throw new GlobalException("Estatutos invalidos o nulos.");
//                } catch (GlobalException ex) {
//                    Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        if (coleccion == null || coleccion.isEmpty())
//        {
//            try {
//                throw new GlobalException("No hay datos.");
//            } catch (GlobalException ex) {
//                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return coleccion;
//        
//    }//final de Listar Grupos
    
    //Eliminar Grupo
        public boolean eliminarGrupo(String codigo) throws GlobalException,SQLException  	
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.");
        }
        CallableStatement pstmt=null;
        
        try 
        {
            pstmt = conexion.prepareCall(ELIMINARGRUPO);
            pstmt.setString(1,codigo);
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el ciclo.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new GlobalException("Número de identificación duplicado.");
        } 
        finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                desconectar();
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Error al desconectar.");
            }
            return true;
        }     
    }

//    public void eliminarGrupo(String codigo) throws GlobalException, NoDataException {
//        try {
//            conectar();
//        } catch(ClassNotFoundException e) {
//            throw  new GlobalException("No se ha localizado el driver");
//        } catch (SQLException e) {
//            throw  new NoDataException("La base de datos no se encuentra disponible");
//        }
//        PreparedStatement pstmt = null;
//        try{
//            pstmt = conexion.prepareCall(ELIMINARGRUPO);
//
//            int resultado = pstmt.executeUpdate();
//            if (resultado != 0){
//                System.out.println("\nEliminación Satisfactoria!");
//            }else{
//                throw new NoDataException("No se realizo el borrado");
//
//            }
//        }catch(SQLException e){
//            throw new GlobalException("Sentencia no valida");
//        }
//        finally{
//            try{
//                if(pstmt != null){
//                    pstmt.close();
//                }
//                desconectar();
//            }catch(SQLException e){
//                throw new GlobalException("Estatutos invalidos o nulos");        
//            }
//        }
//    }//final de eliminar grupo
        
           //Insertar grupo
    public void insertarGrupo_(Grupo grupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        CallableStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARGRUPO);
            pstmt.setString(1,grupo.getCodigo());
            
            String codCurso = "";
            codCurso+=grupo.getCurso().getCodigo();
            pstmt.setString(2, codCurso);
            
            String cedProf = "";
            cedProf+=grupo.getProfesor().getCedula();
            pstmt.setString(3,cedProf);
            
            pstmt.setString(4,grupo.getHorario());
            
            boolean resultado = pstmt.execute();
            if(resultado == true){
                throw  new NoDataException("No se realizo la insercion del Grupo");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                desconectar();
            } catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }//FINAL DE INSERTAR GRUPO
    
    //Buscar Grupo
    public Collection buscarGrupo_(String codigo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(BUSCARGRUPO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, codigo);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            while(rs.next()){
                coleccion.add(grupo(rs));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no valida");
        }
        finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
            }catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if(coleccion == null || coleccion.size()==0){
            throw new NoDataException("No hay datos");        
        }
        return coleccion;    
    }//FINAL DE BUSCAR GRUPO

    //Modificar Grupo
    public void modificarGrupo_(Grupo elgrupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try{
            pstmt = conexion.prepareStatement(MODIFICARGRUPO);
            pstmt.setString(1, elgrupo.getCodigo());
            
            String codCurso = "";
            codCurso += elgrupo.getCurso().getCodigo();
            pstmt.setString(2, codCurso);
            
            String cedProf = "";
            cedProf += elgrupo.getProfesor().getCedula();
            pstmt.setString(3, cedProf);
            
            pstmt.setString(4, elgrupo.getHorario());
            
            int resultado = pstmt.executeUpdate();
            
            if (resultado != 0){
                System.out.println("\nModificación Satisfactoria!");
            }
            else{
                throw new NoDataException("No se realizo la actualización");
            }
            
        }catch(SQLException e){
            throw new GlobalException("Sentencia no valida");
        }finally{
            try{
                if(pstmt!=null){
                    pstmt.close();
                }
                desconectar();
            }catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }//final modificar grupo

    //Listar Grupo
    public Collection listarGrupos_() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(LISTARGRUPOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while(rs.next()){
                coleccion.add(grupo(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia no valida");
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                desconectar();
            }catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0){
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }//final de Listar Grupos
    
    //Eliminar Grupo
    public void eliminarGrupo_(String codigo, String curso, String profe) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(ELIMINARGRUPO);
            pstmt.setString(1, codigo);
            pstmt.setString(2, curso);
            pstmt.setString(3, profe);
            int resultado = pstmt.executeUpdate();
            if (resultado != 0){
                System.out.println("\nEliminación Satisfactoria!");
            }else{
                throw new NoDataException("No se realizo el borrado");

            }
        }catch(SQLException e){
            throw new GlobalException("Sentencia no valida");
        }
        finally{
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                desconectar();
            }catch(SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");        
            }
        }
    }//final de eliminar grupo

}
