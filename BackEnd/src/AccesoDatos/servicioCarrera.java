/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Carrera;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author david
 */
public class servicioCarrera extends Servicio{
    
    private static final String INSERTARCARRERA= "{call insertarCarrera(?,?,?)}";
    private static final String BUSCARCARRERA = "{?=call buscarCarrera(?)}";
    private static final String BUSCARCODIGO = "{?=call buscarCodigoCarreraOP(?)}";
    private static final String BUSCARNOMBRE = "{?=call buscarNombreCarreraOP(?)}";
        private static final String BUSCARCARRERAPORNOMBRE = "{?=call buscarCarreraCodigoPorNombre(?)}";
    private static final String MODIFICARCARRERA = "{call modificarCarrera (?,?,?)}";
    private static final String LISTAR = "{?=call listarCarreras()}";
    private static final String ELIMINARCARRERA = "{call eliminarCarrera(?)}";
    
    public servicioCarrera() {
        
    }
    
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
     
//    public void insertarCarrera(Carrera carrera) throws GlobalException, NoDataException{
//        try {
//            conectar();
//        } catch (ClassNotFoundException e) {
//            throw new GlobalException("No se ha localizado el driver");
//        } catch (SQLException e) {
//            throw new NoDataException("La base de datos no se encuentra disponible");
//        }
//        CallableStatement pstmt=null;
//        
//        try {
//            pstmt = conexion.prepareCall(INSERTARCARRERA);
//            pstmt.setString(1,carrera.getCodigo());
//            pstmt.setString(2,carrera.getNombre());
//            pstmt.setString(3,carrera.getTitulo());
//            boolean resultado = pstmt.execute();
//            if (resultado == true) {
//                throw new NoDataException("No se realizo la insercion");
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new GlobalException("Llave duplicada");
//        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                desconectar();
//            } catch (SQLException e) {
//                throw new GlobalException("Estatutos invalidos o nulos");
//            }
//        }
//    }
    
    public void insertarCarrera(Carrera carrera) throws GlobalException, NoDataException, ParseException{

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARCARRERA);
            pstmt.setString(1,carrera.getCodigo());
            pstmt.setString(2,carrera.getNombre());
            pstmt.setString(3,carrera.getTitulo());
            
            
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }
            
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException("Llave duplicada");
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    desconectar();
                } catch (SQLException e) {
                    throw new GlobalException("Estatutos invalidos o nulos");
                }
            }
        }
     
//    public Collection<Carrera> listarCarreras() throws GlobalException, NoDataException
//	{
//		try{
//			conectar();
//		}
//		catch (ClassNotFoundException ex){
//			throw new GlobalException("No se ha localizado el Driver");
//		}
//		catch (SQLException e){
//			throw new NoDataException("La base de datos no se encuentra disponible");
//		}
//                
//		ResultSet rs = null;
//                Vector<Carrera> coleccion=new Vector<Carrera>();
//		//ArrayList coleccion = new ArrayList();
//		Carrera lacarrera = null;
//		CallableStatement pstmt = null;
//		try{
//			pstmt = conexion.prepareCall(LISTAR);
//			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
//			pstmt.execute();
//			rs = (ResultSet)pstmt.getObject(1);
//			while (rs.next()){
//				lacarrera = new Carrera(
//                                        rs.getString("COD_CARRERA"),
//                                        rs.getString("NOMBRE_CARRERA"),
//                                        rs.getString("TITULO"));
//				coleccion.add(lacarrera);
//			}
//		}catch (SQLException e){
//			e.printStackTrace();
//
//			throw new GlobalException("Sentencia no valida");
//		}
//		finally{
//			try{
//				if (rs != null){
//					rs.close();
//				}
//				if (pstmt != null){
//					pstmt.close();
//				}
//				desconectar();
//			}
//			catch (SQLException e){
//				throw new GlobalException("Estatutos invalidos o nulos");
//			}
//		}
//		if (coleccion == null || coleccion.size() == 0){
//			throw new NoDataException("No hay datos");
//		}
//		return coleccion;
//	}
    
    public List listarCarreras() 	
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
            Carrera lacarrera = null;
        try
        {
                pstmt = conexion.prepareCall(LISTAR);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                    lacarrera = new Carrera(
                        rs.getString("COD_CARRERA"),
                        rs.getString("NOMBRE_CARRERA"),
                        rs.getString("TITULO"));
                    coleccion.add(lacarrera);
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
    
    public Carrera buscarCarrera(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el driver");
        }catch (SQLException ex){
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Carrera lacarrera = null;

        CallableStatement pstmt= null;
        try {
            
                pstmt = conexion.prepareCall(BUSCARCARRERA);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);  
                pstmt.setString(2,id);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
				lacarrera = new Carrera(
                                        rs.getString("COD_CARRERA"),
                                        rs.getString("NOMBRE_CARRERA"),
                                        rs.getString("TITULO"));
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
        return lacarrera;
    
    }
    
    public Carrera buscarCarreraPorNombre(String nombre) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el driver");
        }catch (SQLException ex){
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Carrera lacarrera = null;

        CallableStatement pstmt= null;
        try {
            
                pstmt = conexion.prepareCall(BUSCARCARRERAPORNOMBRE);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);  
                pstmt.setString(2,nombre);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
				lacarrera = new Carrera(
                                        rs.getString("COD_CARRERA"),
                                        rs.getString("NOMBRE_CARRERA"),
                                        rs.getString("TITULO"));
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
        return lacarrera;
    
    }
    
    public Collection buscarCarreraCodigo(String codigo) throws GlobalException, NoDataException{

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
		Carrera lacarrera = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARCODIGO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
				lacarrera = new Carrera(
                                        rs.getString("COD_CARRERA"),
                                        rs.getString("NOMBRE_CARRERA"),
                                        rs.getString("TITULO"));
				coleccion.add(lacarrera);
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
    
       public Collection buscarCarreraNombre(String nombre) throws GlobalException, NoDataException{

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
		Carrera lacarrera = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARNOMBRE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, nombre);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
				lacarrera = new Carrera(
                                        rs.getString("COD_CARRERA"),
                                        rs.getString("NOMBRE_CARRERA"),
                                        rs.getString("TITULO"));
				coleccion.add(lacarrera);
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
    
//    public void modificarCarrera(Carrera lacarrera) throws GlobalException, NoDataException{
//		try{
//			conectar();
//		}
//		catch (ClassNotFoundException e){
//			throw new GlobalException("No se ha localizado el driver");
//		}
//                catch (SQLException e){
//			throw new NoDataException("La base de datos no se encuentra disponible");
//		}
//		PreparedStatement pstmt = null;
//		try{
//			pstmt = conexion.prepareStatement(MODIFICARCARRERA);
//			pstmt.setString(1, lacarrera.getCodigo());
//			pstmt.setString(2, lacarrera.getNombre());
//			pstmt.setString(3, lacarrera.getTitulo());
//                        
//			int resultado = pstmt.executeUpdate();
//
//			//si es diferente de 0 es porq si afecto un registro o mas
//			if (resultado != 0){
//				throw new NoDataException("No se realizo la actualización");
//			}
//			else{
//				System.out.println("\nModificación Satisfactoria!");
//			}
//		}
//		catch (SQLException e){
//			throw new GlobalException("Sentencia no valida");
//		}
//		finally{
//			try{
//				if (pstmt != null){
//					pstmt.close();
//				}
//				desconectar();
//			}
//			catch (SQLException e){
//				throw new GlobalException("Estatutos invalidos o nulos");
//			}
//		}
//	}
       
   public boolean modificarCarrera(Carrera lacarrera) throws GlobalException,SQLException
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
            pstmt = conexion.prepareCall(MODIFICARCARRERA);
            pstmt.setString(1, lacarrera.getCodigo());
            pstmt.setString(2, lacarrera.getNombre());
            pstmt.setString(3, lacarrera.getTitulo());
          
            
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

//    
//    public void eliminarCarrera(String codigo) throws GlobalException, NoDataException
//	{
//		try
//		{
//			conectar();
//		}
//		catch (ClassNotFoundException e)
//		{
//			throw new GlobalException("No se ha localizado el driver");
//		}
//		catch (SQLException e)
//		{
//			throw new NoDataException("La base de datos no se encuentra disponible");
//		}
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = conexion.prepareStatement(ELIMINARCARRERA);
//			pstmt.setString(1, codigo);
//
//			int resultado = pstmt.executeUpdate();
//
//			if (resultado != 0)
//			{
//				throw new NoDataException("No se realizo el borrado");
//			}
//			else
//			{
//				System.out.println("\nEliminación Satisfactoria!");
//			}
//		}
//		catch (SQLException e)
//		{
//			throw new GlobalException("Sentencia no valida");
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//				{
//					pstmt.close();
//				}
//				desconectar();
//			}
//			catch (SQLException e)
//			{
//				throw new GlobalException("Estatutos invalidos o nulos");
//			}
//		}
//	}
   
    public boolean eliminarCarrera(String codigo) throws GlobalException,SQLException  	
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
            pstmt = conexion.prepareCall(ELIMINARCARRERA);
            pstmt.setString(1,codigo);
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar la carrera.");
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
    
       public void insertarCarrera_(Carrera carrera) throws GlobalException, NoDataException, ParseException{

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARCARRERA);
            pstmt.setString(1,carrera.getCodigo());
            pstmt.setString(2,carrera.getNombre());
            pstmt.setString(3,carrera.getTitulo());
            
            
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }
            
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException("Llave duplicada");
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    desconectar();
                } catch (SQLException e) {
                    throw new GlobalException("Estatutos invalidos o nulos");
                }
            }
        }
     

    
    public List listarCarreras_() 	
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
            Carrera lacarrera = null;
        try
        {
                pstmt = conexion.prepareCall(LISTAR);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                    lacarrera = new Carrera(
                        rs.getString("COD_CARRERA"),
                        rs.getString("NOMBRE_CARRERA"),
                        rs.getString("TITULO"));
                    coleccion.add(lacarrera);
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
    
    
    
    public Collection buscarCarreraCodigo_(String codigo) throws GlobalException, NoDataException{

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
		Carrera lacarrera = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARCODIGO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
				lacarrera = new Carrera(
                                        rs.getString("COD_CARRERA"),
                                        rs.getString("NOMBRE_CARRERA"),
                                        rs.getString("TITULO"));
				coleccion.add(lacarrera);
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
    
       public Collection buscarCarreraNombre_(String nombre) throws GlobalException, NoDataException{

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
		Carrera lacarrera = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARNOMBRE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, nombre);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
				lacarrera = new Carrera(
                                        rs.getString("COD_CARRERA"),
                                        rs.getString("NOMBRE_CARRERA"),
                                        rs.getString("TITULO"));
				coleccion.add(lacarrera);
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
    

       
   public boolean modificarCarrera_(Carrera lacarrera) throws GlobalException,SQLException
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
            pstmt = conexion.prepareCall(MODIFICARCARRERA);
            pstmt.setString(1, lacarrera.getCodigo());
            pstmt.setString(2, lacarrera.getNombre());
            pstmt.setString(3, lacarrera.getTitulo());
          
            
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


   
    public boolean eliminarCarrera_(String codigo) throws GlobalException,SQLException  	
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
            pstmt = conexion.prepareCall(ELIMINARCARRERA);
            pstmt.setString(1,codigo);
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar la carrera.");
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
    
}
