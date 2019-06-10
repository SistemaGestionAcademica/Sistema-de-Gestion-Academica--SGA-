package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Steven Villalobos
 */
public class Servicio {
    protected Connection conexion= null;
    
    public Servicio() 
    {
        
    }
    protected void conectar() throws SQLException,ClassNotFoundException 
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    //try {
        conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SGA","david1234");
            //conexion = getJdbcMydbsource();
       /* } catch (NamingException ex) {
            ex.printStackTrace();
        }*/    
    }
    
    protected void desconectar() throws SQLException{
        if(!conexion.isClosed())
        {
            conexion.close();
        }
    }
    
}
