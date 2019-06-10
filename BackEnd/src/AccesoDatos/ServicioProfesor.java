/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

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
public class ServicioProfesor extends Servicio {
    private static final String INSERTARPROFESOR = "{call INSERTARPROFESOR(?,?,?,?)}";
    private static final String ELIMINARPROFESOR = "{call ELIMINARPROFESOR(?)}";
    private static final String MODIFICARPROFESOR = "{call MODIFICARPROFESOR(?,?,?,?)}";
    private static final String LISTARPROFESORES = "{?=call LISTARPROFESOR()}";
    private static final String BUSCARPROFESOR = "{?=call buscarProfesor(?)}";
    private static final String BUSCARPROFESORCEDULA = "{?=call buscarProfesorCedula(?)}";
    private static final String BUSCARPROFESORNOMBRE = "{?=call buscarProfesorNombre(?)}";
    
   public Profesor buscarProfesor(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el driver");
        }catch (SQLException ex){
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Profesor profesor = null;

        CallableStatement pstmt= null;
        try {
            
                pstmt = conexion.prepareCall(BUSCARPROFESOR);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);  
                pstmt.setString(2,id);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next()) {
                    profesor = new Profesor(
                                        rs.getString("CEDULA"),
                                        rs.getString("NOMBRE_PROFESOR"),
                                        rs.getString("TELEFONO"),
                                        rs.getString("EMAIL")
                                    );
                }

        }
        catch (SQLException ex) {
            throw new GlobalException("Sentencia no valida");
        }
        finally {
            try {
                if (rs!=null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            }
            catch(SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return profesor;
    
    }
    
    public boolean insertarProfesor(Profesor profesor) throws GlobalException, SQLException
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.\n");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.\n");
        }
        CallableStatement pstmt=null;
        try 
        {
            pstmt = conexion.prepareCall(INSERTARPROFESOR);
            pstmt.setString(1,profesor.getCedula());
            pstmt.setString(2,profesor.getNombre());
            pstmt.setString(3,profesor.getTelefono());
            pstmt.setString(4,profesor.getEmail());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.\n");
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
    public boolean editarProfesor(Profesor profesor) throws GlobalException,SQLException
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
            pstmt = conexion.prepareCall(MODIFICARPROFESOR);
            pstmt.setString(1,profesor.getCedula());
            pstmt.setString(2,profesor.getNombre());
            pstmt.setString(3,profesor.getTelefono());
            pstmt.setString(4,profesor.getEmail());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.");
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
    public List listaProfesores() 	
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
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (SQLException e) 
        {
            try {
                throw new GlobalException("La base de datos no se encuentra disponible.");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        CallableStatement pstmt=null;
        ResultSet rs = null;
	ArrayList coleccion = new ArrayList();
	Profesor profesor = null;
        try
        {
                pstmt = conexion.prepareCall(LISTARPROFESORES);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                        profesor = new Profesor(rs.getString("cedula"),
                                            rs.getString("NOMBRE_PROFESOR"),
                                            rs.getString("telefono"),
                                            rs.getString("email")
                        );
                        coleccion.add(profesor);
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
    public boolean eliminarProfesor(String cedula) throws GlobalException,SQLException  	
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
            pstmt = conexion.prepareCall(ELIMINARPROFESOR);
            pstmt.setString(1,cedula);
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.");
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


    public List buscarProfesorCedula(String cedula) throws GlobalException, NoDataException{

		try{
			conectar();
		}catch (ClassNotFoundException e){
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e){
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                Profesor profesor = null;  
		//Ciclo elciclo = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARPROFESORCEDULA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, cedula);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                        profesor = new Profesor(
                                          rs.getString("cedula"),
                                          rs.getString("NOMBRE_PROFESOR"),
                                          rs.getString("telefono"),
                                          rs.getString("email")
                        );
                        coleccion.add(profesor);
                            
			}
		}
		catch (SQLException e){
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally{
			try{
				if (rs != null){
					rs.close();
				}
				if (pstmt != null){
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e){
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
		if (coleccion == null || coleccion.size() == 0){
			throw new NoDataException("No hay datos");
		}
		return coleccion;
	}
    
        public List buscarProfesorNombre(String nombre) throws GlobalException, NoDataException{

		try{
			conectar();
		}catch (ClassNotFoundException e){
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e){
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                Profesor profesor = null;  
		//Ciclo elciclo = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARPROFESORNOMBRE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, nombre);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                        profesor = new Profesor(
                                          rs.getString("cedula"),
                                          rs.getString("NOMBRE_PROFESOR"),
                                          rs.getString("telefono"),
                                          rs.getString("email")
                        );
                        coleccion.add(profesor);
                            
			}
		}
		catch (SQLException e){
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally{
			try{
				if (rs != null){
					rs.close();
				}
				if (pstmt != null){
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e){
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
		if (coleccion == null || coleccion.size() == 0){
			throw new NoDataException("No hay datos");
		}
		return coleccion;
	}
        
                    //INSERTAR PROFESOR
    public void insertarProfesor_(Profesor profesor) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        CallableStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARPROFESOR);
            pstmt.setString(1,profesor.getCedula());
            pstmt.setString(2,profesor.getNombre());
            pstmt.setString(3,profesor.getTelefono());
            pstmt.setString(4,profesor.getEmail());
            boolean resultado = pstmt.execute();
            if(resultado == true){
                throw  new NoDataException("No se realizo la insercion del Profesor");
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
    }//Final Insertar Profesor
    
    //BUSCAR PROFESOR POR CEDULA
    public Collection buscarProfesor_(String cedula)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elprofesor = null;
        CallableStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(BUSCARPROFESORCEDULA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, cedula);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            while(rs.next()){
                elprofesor = new Profesor(
                        rs.getString("CEDULA"), 
                        rs.getString("NOMBRE_PROFESOR"), 
                        rs.getString("TELEFONO"), 
                        rs.getString("EMAIL"));
                coleccion.add(elprofesor);
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
    }//Final BUSCAR PROFESOR POR CEDULA
    
    //BUSCAR PROFESOR POR Nombre
    public Collection buscarProfesorNombre_(String nombre)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elprofesor = null;
        CallableStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(BUSCARPROFESORNOMBRE);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            while(rs.next()){
                elprofesor = new Profesor(
                        rs.getString("CEDULA"), 
                        rs.getString("NOMBRE_PROFESOR"), 
                        rs.getString("TELEFONO"), 
                        rs.getString("EMAIL"));
                coleccion.add(elprofesor);
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
    }//Final BUSCAR PROFESOR POR NOMBRE
    
    //MODIFICAR PROFESOR 
    public void modificarProfesor_(Profesor elprofesor) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try{
            pstmt = conexion.prepareStatement(MODIFICARPROFESOR);
            pstmt.setString(1, elprofesor.getCedula());
            pstmt.setString(2, elprofesor.getNombre());
            pstmt.setString(3, elprofesor.getTelefono());
            pstmt.setString(4, elprofesor.getEmail());
            int resultado = pstmt.executeUpdate();
            
            if ((resultado != 0) && (resultado != -1)){
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
    }//final MODIFICAR PROFESOR
    
    //Listar profesores
    public Collection listarProfesores_() throws GlobalException, NoDataException{
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elprofesor = null;
        CallableStatement pstmt = null;
        
        try{
            pstmt = conexion.prepareCall(LISTARPROFESORES);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while(rs.next()){
                elprofesor = new Profesor(
                        rs.getString("CEDULA"), 
                        rs.getString("NOMBRE_PROFESOR"), 
                        rs.getString("TELEFONO"), 
                        rs.getString("EMAIL"));
                coleccion.add(elprofesor);
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
    }//final Listar profesores
    
    //ELIMINAR PROFESOR
    public void eliminarProfesor_(String cedula)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch(ClassNotFoundException e) {
            throw  new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw  new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(ELIMINARPROFESOR);
            pstmt.setString(1, cedula);
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
    }//FINAL ELIMINAR PROFESOR
    
}
