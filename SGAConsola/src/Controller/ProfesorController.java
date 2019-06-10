/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Profesor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Usuario1
 */
public class ProfesorController {
    private Control control;
    private Profesor p;
    private List<Profesor> pList;
    
    public ProfesorController(Control control){
        this.p = new Profesor();
        this.control = control;
        this.pList = new ArrayList<Profesor>();
    }
    
    public void MenuProfesores()throws Exception, GlobalException, NoDataException{
        boolean selec = true;
        while(selec == true){
            System.out.println("Módulo de Profesores");
            System.out.println("-----------------------------------");
            System.out.println("1- Ingresar un Profesor nuevo");
            System.out.println("2- Modificar un Profesor");
            System.out.println("3- Eliminar un Profesor");
            System.out.println("4- Lista de profesores");
            System.out.println("5- Buscar profesor por cédula");
            System.out.println("6- Buscar profesor por nombre");
            System.out.println("7- Salir del Módulo de Profesores");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 1://Ingresar un Profesor nuevo
                        InsertarProfesorController();
                        break;
                    case 2://Modificar un Profesor
                        ModificarProfesorController();
                        break;
                    case 3://Eliminar un Profesor
                        EliminarProfesorController();
                        break;
                    case 4://Lista de profesores
                        ListarProfesoresController();
                        break;
                    case 5://Buscar profesor por cédula
                        BuscarProfesorCedula();
                        break;
                    case 6://Buscar profesor por nombre
                        BuscarProfesorNombre();
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
    }
    
    public void InsertarProfesorController() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String cedula = "";
            String nombre = "";
            String telefono = "";
            String email = "";
            p = new Profesor();
            
            System.out.println("----------Digite los Datos del Nuevo Profesor----------");
            System.out.print("Digite la identificación del profesor: ");
            cedula = br.readLine();
            p.setCedula(cedula);
            
            System.out.println("Digite el nombre completo del profesor: ");
            nombre = br.readLine();
            p.setNombre(nombre);
            
            System.out.println("Digite el número telefónico del profesor: ");
            telefono = br.readLine();
            p.setTelefono(telefono);
            
            System.out.println("Digite el correo electrónico del profesor: "); 
            email = br.readLine();
            p.setEmail(email);
            try{
                control.insertarProfesor(p);
                System.out.println("Exito. Se ha creado un Nuevo profesor");
            }catch(Exception e){
                System.err.println("Error: "+e);
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void ModificarProfesorController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pList = new ArrayList<Profesor>();
        String cedulaSelec = "";
        String nombre = "";
        String telefono = "";
        String email = "";
        int cedula;
        
        try{
            pList = control.listaProfesores();
            for(int i = 0; i < pList.size(); i++){
                System.out.println("Profesor número ("+i+") "+pList.get(i).getNombre()+"-"+pList.get(i).getCedula());
            }
            System.out.print("Seleccione el número del profesor para poder modificarlo: ");
            cedula = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            cedulaSelec = pList.get(cedula).getCedula(); // p.get(cedula)
            System.out.println("Ha seleccionado el siguiente profesor:");
            System.out.println("Nombre Actual "+pList.get(cedula).getNombre()+"\n"+
                                "Cedula Actual "+pList.get(cedula).getCedula()+"\n"+
                                "Telefono Actual "+pList.get(cedula).getTelefono()+"\n"+
                                "Email Actual "+pList.get(cedula).getEmail());
            System.out.println("--------------------------------------------------");
            if(cedulaSelec != null){
                System.out.println("Modifique el nombre completo del profesor: ");
                nombre = br.readLine();
                pList.get(cedula).setNombre(nombre);

                System.out.println("Modifique el número telefónico del profesor: ");
                telefono = br.readLine();
                pList.get(cedula).setTelefono(telefono);

                System.out.println("Modifique el correo electrónico del profesor: "); 
                email = br.readLine();
                pList.get(cedula).setEmail(email);
                
                try{
                    control.editarProfesor(pList.get(cedula));
                    System.out.print("Exito. Se ha modifado el Profesor");
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
    
    public void EliminarProfesorController() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cedula;
        String cedDelete = "";
        pList = new ArrayList<Profesor>();
        try{
            pList = control.listaProfesores();
            for(int i = 0; i < pList.size(); i++){
                System.out.println("Profesor número ("+i+") "+pList.get(i).getNombre()+"  "+pList.get(i).getCedula());
            }
            System.out.print("Seleccione el número del profesor para poder eliminarlo: ");
            cedula = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            cedDelete = pList.get(cedula).getCedula(); // p.get(cedula)
            try{
                control.eliminarProfesor(cedDelete);
            }catch(Exception e){
                System.err.println("Error: "+e);            
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void ListarProfesoresController() throws Exception{
        pList = new ArrayList<Profesor>();
        try{
            pList = control.listaProfesores();
            for(int i = 0; i < pList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+pList.get(i).getNombre());
                System.out.println("Identificación: "+pList.get(i).getCedula());
                System.out.println("Telefono: "+pList.get(i).getTelefono());
                System.out.println("Correo electrónico: "+pList.get(i).getEmail());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarProfesorCedula(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cedula = "";
        pList = new ArrayList<Profesor>();
        try{
            System.out.println("Digite la Identificación del profesor: ");
            cedula = br.readLine();
            pList = control.buscarProfesor(cedula);
            for(int i = 0; i < pList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+pList.get(i).getNombre());
                System.out.println("Identificación: "+pList.get(i).getCedula());
                System.out.println("Telefono: "+pList.get(i).getTelefono());
                System.out.println("Correo electrónico: "+pList.get(i).getEmail());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarProfesorNombre(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombre = "";
        pList = new ArrayList<Profesor>();
        try{
            System.out.println("Digite la Nombre del profesor: ");
            nombre = br.readLine();
            pList = control.buscarProfesorNombre(nombre);
            for(int i = 0; i < pList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+pList.get(i).getNombre());
                System.out.println("Identificación: "+pList.get(i).getCedula());
                System.out.println("Telefono: "+pList.get(i).getTelefono());
                System.out.println("Correo electrónico: "+pList.get(i).getEmail());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
