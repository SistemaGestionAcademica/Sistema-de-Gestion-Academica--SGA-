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
import Entidades.Carrera;
import Entidades.Carrera;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CarreraController {
    private Control control;
    private Carrera c;
    private List<Carrera> cList;
    
    public CarreraController(Control control){
        this.control = control;
        this.c = new Carrera();
        this.cList = new ArrayList<Carrera>();
    }
    
    public void MenuCarreras()throws Exception, GlobalException, NoDataException{
        boolean selec = true;
        while(selec == true){
            System.out.println("Módulo de Carreras");
            System.out.println("-----------------------------------");
            System.out.println("1- Ingresar un Carrera nuevo");
            System.out.println("2- Modificar un Carrera");
            System.out.println("3- Eliminar un Carrera");
            System.out.println("4- Lista de Carrera");
            System.out.println("5- Buscar carrera por codigo");
            System.out.println("6- Buscar carrera por nombre");
            System.out.println("7- Salir del Módulo de Carrera");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 1://Ingresar un Carrera nuevo
                        InsertarCarreraController();
                        break;
                    case 2://Modificar un Carrera
                        ModificarCarreraController();
                        break;
                    case 3://Eliminar un Carrera
                        EliminarCarreraController();
                        break;
                    case 4://Lista de Carreras
                        ListarCarreraController();
                        break;
                    case 5://Buscar Carrera por cédula
                        BuscarCarreraCedula();
                        break;
                    case 6://Buscar Carrera por nombre
                        BuscarCarreraNombre();
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
    
    public void InsertarCarreraController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String codigo = "";
            String nombre = "";
            String titulo = "";
            c = new Carrera();
            
            System.out.println("----------Digite los Datos del Nuevo Carrera----------");
            System.out.print("Digite el codigo de la carrera: ");
            codigo = br.readLine();
            c.setCodigo(codigo);
            
            System.out.println("Digite el nombre de la carrera: ");
            nombre = br.readLine();
            c.setNombre(nombre);
            
            System.out.println("Digite el título de la carrera: ");
            titulo = br.readLine();
            c.setTitulo(titulo);

            try{
                control.insertarCarrera(c);
                System.out.println("Exito. Se ha creado un Nuevo carrera");
            }catch(Exception e){
                System.err.println("Error: "+e);
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void ModificarCarreraController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cList = new ArrayList<Carrera>();
        String codigoSelec = "";
        String nombre = "";
        String titulo = "";
        int codigo;
        
        try{
            cList = control.listarCarreras();
            for(int i = 0; i < cList.size(); i++){
                System.out.println("Carrera número ("+i+") "+cList.get(i).getNombre()+" "+cList.get(i).getCodigo());
            }
            System.out.print("Seleccione el número de la carrera para poder modificarlo: ");
            codigo = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            codigoSelec = cList.get(codigo).getCodigo(); // p.get(codigo)
            System.out.println("Ha seleccionado la siguiente carrera:");
            System.out.println("Nombre Actual "+cList.get(codigo).getNombre()+"\n"+
                                "Codigo Actual "+cList.get(codigo).getCodigo()+"\n"+
                                "Título Actual "+cList.get(codigo).getTitulo());
            System.out.println("--------------------------------------------------");
            if(codigoSelec != null){
                System.out.println("Modifique el nombre de la carrera: ");
                nombre = br.readLine();
                cList.get(codigo).setNombre(nombre);

                System.out.println("Modifique el título de la carrera: ");
                titulo = br.readLine();
                cList.get(codigo).setTitulo(titulo);

                try{
                    control.modificarCarrera(cList.get(codigo));
                    System.out.print("Exito. Se ha modificado la Carrera");
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
    
    public void EliminarCarreraController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int codigo;
        String codDelete = "";
        cList = new ArrayList<Carrera>();
        try{
            cList = control.listarCarreras();
            for(int i = 0; i < cList.size(); i++){
                System.out.println("Carrera número ("+i+") "+cList.get(i).getNombre()+"     "+cList.get(i).getCodigo());
            }
            System.out.print("Seleccione el número de la carrera para poder eliminarlo: ");
            codigo = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            codDelete = cList.get(codigo).getCodigo(); // p.get(codigo)
            try{
                control.eliminarCarrera(codDelete);
            }catch(Exception e){
                System.err.println("Error: "+e);            
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void ListarCarreraController()throws Exception{
        cList = new ArrayList<Carrera>();
        try{
            cList = control.listarCarreras();
            for(int i = 0; i < cList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+cList.get(i).getNombre());
                System.out.println("Código: "+cList.get(i).getCodigo());
                System.out.println("Título: "+cList.get(i).getTitulo());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarCarreraCedula()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String codigo = "";
        cList = new ArrayList<Carrera>();
        try{
            System.out.println("Digite el Código de la carrera: ");
            codigo = br.readLine();
            cList = control.buscarCarreraCodigo(codigo);
            for(int i = 0; i < cList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+cList.get(i).getNombre());
                System.out.println("Código: "+cList.get(i).getCodigo());
                System.out.println("Título: "+cList.get(i).getTitulo());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarCarreraNombre()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombre = "";
        cList = new ArrayList<Carrera>();
        try{
            System.out.println("Digite la Nombre de la carrera: ");
            nombre = br.readLine();
            cList = control.buscarCarreraNombre(nombre);
            for(int i = 0; i < cList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Nombre: "+cList.get(i).getNombre());
                System.out.println("Código: "+cList.get(i).getCodigo());
                System.out.println("Título: "+cList.get(i).getTitulo());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }

}
