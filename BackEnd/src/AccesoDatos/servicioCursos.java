/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Carrera;
import Entidades.Ciclo;
import Entidades.Curso;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author david
 */
public class servicioCursos extends Servicio{

    private static final String INSERTARCURSO= "{call insertarCurso(?,?,?,?,?,?)}";
    private static final String BUSCARCODIGO = "{?=call buscarCodigoCurso(?)}";
    private static final String BUSCARNOMBRE = "{?=call buscarNombreCurso(?)}";
    private static final String BUSCARCARRERA = "{?=call buscarCarreraCurso(?)}";
    private static final String MODIFICARCURSO = "{call modificarCursoU(?,?,?,?,?,?)}";
    private static final String LISTAR = "{?=call listarCursos()}";
    private static final String ELIMINARCURSO = "{call eliminarCurso(?)}";
    private static final String BUSCARCURSO = "{?=call buscarCurso(?)}";    

       
      public Curso buscarCurso(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el driver");
        }catch (SQLException ex){
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Curso curso = null;

        CallableStatement pstmt= null;
        try {
            
                pstmt = conexion.prepareCall(BUSCARCURSO);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);  
                pstmt.setString(2,id);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next()) {
                    curso = new Curso(
                                        rs.getString("CODIGO"),
                                        ciclo(rs),
                                        carrera(rs),
                                        rs.getString("NOMBRE"),
                                        rs.getInt("CREDITOS"),
                                        rs.getString("HORAS")
                            
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
        return curso;
    
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
    
//    public void insertarCurso(Curso curso) throws GlobalException, NoDataException{
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
//            pstmt = conexion.prepareCall(INSERTARCURSO);
//            pstmt.setString(1,curso.getCodigo());
//            String aux="";
//            aux+=curso.getCodigo_ciclo().getCodigo();
//            pstmt.setString(2,aux);
//            String aux2="";
//            aux2+=curso.getCodigo_carrera().getCodigo();
//            pstmt.setString(3,aux2);
//            pstmt.setString(4,curso.getNombre());
//            pstmt.setInt(5,curso.getCreditos());
//            pstmt.setString(6,curso.getHoras());
//            
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
    
        public void insertarCurso(Curso curso) throws GlobalException, NoDataException, ParseException{

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
                pstmt = conexion.prepareCall(INSERTARCURSO);
                pstmt.setString(1,curso.getCodigo());
                String aux="";
                aux+=curso.getCodigo_ciclo().getCodigo();
                pstmt.setString(2,aux);
                String aux2="";
                aux2+=curso.getCodigo_carrera().getCodigo();
                pstmt.setString(3,aux2);
                pstmt.setString(4,curso.getNombre());
                pstmt.setInt(5,curso.getCreditos());
                pstmt.setString(6,curso.getHoras());
            

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
    
//    public Collection listarCursos() throws GlobalException, NoDataException
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
//		ArrayList coleccion = new ArrayList();
//		//Curso c = new Curso();
//		CallableStatement pstmt = null;
//		try{
//			pstmt = conexion.prepareCall(LISTAR);
//			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
//			pstmt.execute();
//			rs = (ResultSet)pstmt.getObject(1);
//			while (rs.next()){                            
//				
//				coleccion.add(curso(rs));
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
//    
    
    public List listarCursos() 	throws GlobalException, NoDataException
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
	//Curso curso = null;
        try
        {
            
                pstmt = conexion.prepareCall(LISTAR);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                    coleccion.add(curso(rs));
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
    
    public Collection buscarCursoCodigo(String codigo) throws GlobalException, NoDataException{

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
		//Curso elcurso = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARCODIGO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                                coleccion.add(curso(rs));
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
    
        public Collection buscarCursoNombre(String nombre) throws GlobalException, NoDataException{

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
		//Curso elcurso = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARNOMBRE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, nombre);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                                coleccion.add(curso(rs));
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
        
        public Collection buscarCursoCarrera(String carrera) throws GlobalException, NoDataException{

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
		//Curso elcurso = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARCARRERA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, carrera);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                                coleccion.add(curso(rs));
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
    
    
//       public void modificarCurso(Curso curso) throws GlobalException, NoDataException{
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
//			pstmt = conexion.prepareStatement(MODIFICARCURSO);
//                        pstmt.setString(1,curso.getCodigo());
//                        String aux="";
//                        aux+=curso.getCodigo_ciclo();
//                        pstmt.setString(2,aux);
//                        String aux2="";
//                        aux2+=curso.getCodigo_carrera();
//                        pstmt.setString(3,aux2);
//                        pstmt.setString(4,curso.getNombre());
//                        pstmt.setInt(5,curso.getCreditos());
//                        pstmt.setString(6,curso.getHoras());
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

    public boolean modificarCurso(Curso curso) throws GlobalException,SQLException
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
            pstmt = conexion.prepareCall(MODIFICARCURSO);
                        pstmt.setString(1,curso.getCodigo());
                        String aux="";
                        aux+=curso.getCodigo_ciclo().getCodigo();
                        pstmt.setString(2,aux);
                        String aux2="";
                        aux2+=curso.getCodigo_carrera().getCodigo();
                        pstmt.setString(3,aux2);
                        pstmt.setString(4,curso.getNombre());
                        pstmt.setInt(5,curso.getCreditos());
                        pstmt.setString(6,curso.getHoras());
          
            
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
    
    
    public boolean eliminarCurso(String codigo) throws GlobalException,SQLException  	
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
            pstmt = conexion.prepareCall(ELIMINARCURSO);
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

//    public void eliminarCurso(String codigo) throws GlobalException, NoDataException
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
//			pstmt = conexion.prepareStatement(ELIMINARCURSO);
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
    
      public void insertarCurso_(Curso curso) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARCURSO);
            pstmt.setString(1,curso.getCodigo());
            String aux="";
            aux+=curso.getCodigo_ciclo().getCodigo();
            pstmt.setString(2,aux);
            String aux2="";
            aux2+=curso.getCodigo_carrera().getCodigo();
            pstmt.setString(3,aux2);
            pstmt.setString(4,curso.getNombre());
            pstmt.setInt(5,curso.getCreditos());
            pstmt.setString(6,curso.getHoras());
            
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
    
    public Collection listarCursos_() throws GlobalException, NoDataException
    {
        try{
                conectar();
        }
        catch (ClassNotFoundException ex){
                throw new GlobalException("No se ha localizado el Driver");
        }
        catch (SQLException e){
                throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        //Curso c = new Curso();
        CallableStatement pstmt = null;
        try{
                pstmt = conexion.prepareCall(LISTAR);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next()){                            

                        coleccion.add(curso(rs));
                }
        }catch (SQLException e){
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
    
    
    public Collection buscarCursoCodigo_(String codigo) throws GlobalException, NoDataException{

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
		//Curso elcurso = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARCODIGO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                                coleccion.add(curso(rs));
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
    
    public Collection buscarCursoNombre_(String codigo) throws GlobalException, NoDataException{

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
		//Curso elcurso = null;
		CallableStatement pstmt = null;
		try{
			pstmt = conexion.prepareCall(BUSCARNOMBRE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next()){
                                coleccion.add(curso(rs));
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
    
    
       public void modificarCurso_(Curso curso) throws GlobalException, NoDataException{
		try{
			conectar();
		}
		catch (ClassNotFoundException e){
			throw new GlobalException("No se ha localizado el driver");
		}
                catch (SQLException e){
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		PreparedStatement pstmt = null;
		try{
			pstmt = conexion.prepareStatement(MODIFICARCURSO);
                        pstmt.setString(1,curso.getCodigo());
                        String aux="";
                        aux+=curso.getCodigo_ciclo();
                        pstmt.setString(2,aux);
                        String aux2="";
                        aux2+=curso.getCodigo_carrera();
                        pstmt.setString(3,aux2);
                        pstmt.setString(4,curso.getNombre());
                        pstmt.setInt(5,curso.getCreditos());
                        pstmt.setString(6,curso.getHoras());
                        
			int resultado = pstmt.executeUpdate();

			//si es diferente de 0 es porq si afecto un registro o mas
			if (resultado != 0){
				throw new NoDataException("No se realizo la actualización");
			}
			else{
				System.out.println("\nModificación Satisfactoria!");
			}
		}
		catch (SQLException e){
			throw new GlobalException("Sentencia no valida");
		}
		finally{
			try{
				if (pstmt != null){
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e){
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
	}

    
    public void eliminarCurso_(String codigo) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException e)
		{
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareStatement(ELIMINARCURSO);
			pstmt.setString(1, codigo);

			int resultado = pstmt.executeUpdate();

			if (resultado != 0)
			{
				throw new NoDataException("No se realizo el borrado");
			}
			else
			{
				System.out.println("\nEliminación Satisfactoria!");
			}
		}
		catch (SQLException e)
		{
			throw new GlobalException("Sentencia no valida");
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
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
	}
    
    
    
}
