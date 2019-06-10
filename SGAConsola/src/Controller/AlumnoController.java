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
import Entidades.Alumno;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class AlumnoController {
    private Control control;
    private Alumno a;
    private List<Alumno> aList;
    
    public AlumnoController(Control control){
        this.control = control;
        this.a = new Alumno();
        this.aList = new ArrayList<Alumno>();
    }
    
    public void MenuAlumnos() throws Exception, GlobalException, NoDataException{
        boolean selec = true;
        while(selec == true){
            System.out.println("Módulo de Alumnos");
            System.out.println("-----------------------------------");
            System.out.println("1- Ingresar un Alumno nuevo");
            System.out.println("2- Modificar un Alumno");
            System.out.println("3- Eliminar un Alumno");
            System.out.println("4- Lista de alumnos");
            System.out.println("5- Buscar alumno por cédula");
            System.out.println("6- Buscar alumno por nombre");
            System.out.println("7- Salir del Módulo de Alumnos");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 1://Ingresar un Alumno nuevo
                        InsertarAlumnoController();
                        break;
                    case 2://Modificar un Alumno
                        ModificarAlumnoController();
                        break;
                    case 3://Eliminar un Alumno
                        EliminarAlumnoController();
                        break;
                    case 4://Lista de Alumno
                        ListarAlumnoController();
                        break;
                    case 5://Buscar Alumno por cédula
                        BuscarAlumnoCedula();
                        break;
                    case 6://Buscar Alumno por nombre
                        BuscarAlumnoNombre();
                        break;
                    case 7://Salir
                        selec = false;
                        System.out.println("Ha salido exitosamente");
                        System.exit(0);
                        break;
                    default:
                        System.exit(0);
                }   
            } catch(IOException e){
                System.out.println("Error: "+e);
                System.exit(1);
            }
        }
    }//FINAL DE MENUALUMNOS
    
    public void InsertarAlumnoController() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String cedula = "";
            String nombre = "";
            String telefono = "";
            String email = "";
            String fecha = "";
            a = new Alumno();
            
            System.out.println("----------Digite los Datos del Nuevo Alumno----------");
            System.out.print("Digite la identificación del profesor: ");
            cedula = br.readLine();
            a.setCedula(cedula);
            
            System.out.println("Digite el nombre completo del profesor: ");
            nombre = br.readLine();
            a.setNombre(nombre);
            
            System.out.println("Digite el número telefónico del profesor: ");
            telefono = br.readLine();
            a.setTelefono(telefono);
            
            System.out.println("Digite el correo electrónico del profesor: "); 
            email = br.readLine();
            a.setEmail(email);
            
            System.out.println("Digite la fecha de nacimiento del profesor: "); 
            fecha = br.readLine();
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date date = sdf1.parse(fecha);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
            a.setFecha(sqlStartDate);
            
            try{
                control.insertarAlumno(a);
                System.out.println("Exito. Se ha creado un Nuevo profesor");
            }catch(Exception e){
                System.err.println("Error: "+e);
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void ModificarAlumnoController() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        aList = new ArrayList<Alumno>();
        String cedulaSelec = "";
        String nombre = "";
        String telefono = "";
        String email = "";
        int cedula;
        String fecha="";
        
        try{
            aList = control.listarAlumnos();
            for(int i = 0; i < aList.size(); i++){
                System.out.println("Alumno número ("+i+") "+aList.get(i).getNombre()+"-"+aList.get(i).getCedula());
            }
            System.out.print("Seleccione el número del profesor para poder modificarlo: ");
            cedula = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            cedulaSelec = aList.get(cedula).getCedula(); // p.get(cedula)
            System.out.println("Ha seleccionado el siguiente profesor:");
            System.out.println("Nombre Actual "+aList.get(cedula).getNombre()+"\n"+
                                "Cedula Actual "+aList.get(cedula).getCedula()+"\n"+
                                "Telefono Actual "+aList.get(cedula).getTelefono()+"\n"+
                                "Email Actual "+aList.get(cedula).getEmail()+"\n"+
                                "Fecha de Nacimiento "+aList.get(cedula).getFecha());
            System.out.println("--------------------------------------------------");
            if(cedulaSelec != null){
                System.out.println("Modifique el nombre completo del profesor: ");
                nombre = br.readLine();
                aList.get(cedula).setNombre(nombre);

                System.out.println("Modifique el número telefónico del profesor: ");
                telefono = br.readLine();
                aList.get(cedula).setTelefono(telefono);

                System.out.println("Modifique el correo electrónico del profesor: "); 
                email = br.readLine();
                aList.get(cedula).setEmail(email);
                
                System.out.println("Digite la fecha de nacimiento del profesor: "); 
                fecha = br.readLine();
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
                java.util.Date date = sdf1.parse(fecha);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
                aList.get(cedula).setFecha(sqlStartDate);
            
                try{
                    control.editarAlumno(aList.get(cedula));
                    System.out.print("Exito. Se ha modifado el Alumno");
                }catch(Exception e){
                    System.err.println("Error: "+e);
                }

            }else{
                System.out.println("Error");
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void EliminarAlumnoController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cedula;
        String cedDelete = "";
        aList = new ArrayList<Alumno>();
        try{
            aList = control.listarAlumnos();
            for(int i = 0; i < aList.size(); i++){
                System.out.println("Alumno número ("+i+") "+aList.get(i).getNombre()+"-"+aList.get(i).getCedula());
            }
            System.out.print("Seleccione el número del profesor para poder eliminarlo: ");
            cedula = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            cedDelete = aList.get(cedula).getCedula(); // p.get(cedula)
            try{
                control.eliminarAlumno(cedDelete);
            }catch(Exception e){
                System.err.println("Error: "+e);            
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void ListarAlumnoController()throws Exception{
        aList = new ArrayList<Alumno>();
        try{
            aList = control.listarAlumnos();
            for(int i = 0; i < aList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+aList.get(i).getNombre());
                System.out.println("Identificación: "+aList.get(i).getCedula());
                System.out.println("Telefono: "+aList.get(i).getTelefono());
                System.out.println("Correo electrónico: "+aList.get(i).getEmail());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarAlumnoCedula()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cedula = "";
        aList = new ArrayList<Alumno>();
        try{
            System.out.println("Digite la Identificación del profesor: ");
            cedula = br.readLine();
            aList = control.buscarAlumno(cedula);
            for(int i = 0; i < aList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+aList.get(i).getNombre());
                System.out.println("Identificación: "+aList.get(i).getCedula());
                System.out.println("Telefono: "+aList.get(i).getTelefono());
                System.out.println("Correo electrónico: "+aList.get(i).getEmail());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarAlumnoNombre()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombre = "";
        aList = new ArrayList<Alumno>();
        try{
            System.out.println("Digite la Nombre del profesor: ");
            nombre = br.readLine();
            aList = control.buscarAlumnoNombre(nombre);
            for(int i = 0; i < aList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+aList.get(i).getNombre());
                System.out.println("Identificación: "+aList.get(i).getCedula());
                System.out.println("Telefono: "+aList.get(i).getTelefono());
                System.out.println("Correo electrónico: "+aList.get(i).getEmail());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }

}
