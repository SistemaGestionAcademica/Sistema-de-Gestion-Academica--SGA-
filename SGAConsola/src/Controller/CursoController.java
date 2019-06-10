/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Control.Control;
import Entidades.Carrera;
import Entidades.Ciclo;
import Entidades.Curso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CursoController {
    private Control control;
    private Curso curso;
    private Ciclo ciclo;
    private Carrera carrera;
    
    private List<Curso> cursoList;
    private List<Carrera> carreraList;
    private List<Ciclo> cicloList;
    
    public CursoController(Control control){
        this.control = control;
        
        this.carrera = new Carrera();
        this.ciclo = new Ciclo();
        this.curso = new Curso();

        this.carreraList = new ArrayList<Carrera>();
        this.cicloList = new ArrayList<Ciclo>();
        this.cursoList = new ArrayList<Curso>();
    }
    
    public void MenuCursos()throws Exception{
        boolean selec = true;
        while(selec == true){
            System.out.println("Módulo de Cursos");
            System.out.println("-----------------------------------");
            System.out.println("1- Ingresar un Curso nuevo");
            System.out.println("2- Modificar un Curso");
            System.out.println("3- Eliminar un Curso");
            System.out.println("4- Lista de Cursos");
            System.out.println("5- Buscar cursos por código");
            System.out.println("6- Salir del Módulo de Cursos");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 1://Ingresar un Curso nuevo
                        InsertarCursoController();
                        break;
                    case 2://Modificar un Curso
                        ModificarCursoController();
                        break;
                    case 3://Eliminar un Curso
                        EliminarCursoController();
                        break;
                    case 4://Lista de Curso
                        ListarCursosController();
                        break;
                    case 5://Buscar Curso por código
                        BuscarCursoController();
                        break;
                    case 6://Salir
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
    
    public void InsertarCursoController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            carreraList = new ArrayList<Carrera>();
            cicloList = new ArrayList<Ciclo>();
            String codigo = "";/////////
            int codCiclo = 0;
            int codCarrera = 0;
            String nombre = "";
            int creditos = 0;/////////
            String horas = "";////////
            curso = new Curso();
            carrera = new Carrera();
            ciclo = new Ciclo();
            
            System.out.println("----------Digite los Datos del Nuevo Curso----------");
            System.out.print("Digite el código del curso: ");
            codigo = br.readLine();
            curso.setCodigo(codigo);
            
            /////////////////CICLOS//////////////////////
            cicloList = control.listarCiclos();
            System.out.println("Lista de Ciclos");
            for(int i = 0; i < cicloList.size(); i++){
                System.out.println("Ciclo número ("+i+")"+"\n"+
                                    "Codigo del Ciclo: "+cicloList.get(i).getCodigo()+"\n");
            }
            System.out.print("Seleccione el número de Ciclo: ");
            codCiclo = Integer.parseInt(br.readLine());
            ciclo = cicloList.get(codCiclo);
            curso.setCodigo_ciclo(ciclo);
            /////////////////CARRERAS////////////////////
            carreraList = control.listarCarreras();
            System.out.println("Lista de Carreras");
            for(int j = 0; j < carreraList.size(); j++){
                System.out.println("Carrera número ("+j+")"+"\n"+
                                    "Codigo del Carrera: "+carreraList.get(j).getNombre()+"\n"+
                                    "Nombre de la Carrera: "+carreraList.get(j).getNombre());
            }
            System.out.print("Seleccione el número de la Carrera: ");
            codCarrera = Integer.parseInt(br.readLine());
            carrera = carreraList.get(codCarrera);
            curso.setCodigo_carrera(carrera);

            System.out.print("Digite el nombre del curso: ");
            nombre = br.readLine();
            curso.setNombre(nombre);
            
            System.out.print("Digite la cantidad de créditos del curso: ");
            creditos = Integer.parseInt(br.readLine());
            curso.setCreditos(creditos);   
            
            System.out.print("Digite la cantidad de horas que dura el curso: ");
            horas = br.readLine();
            curso.setHoras(horas);
            
            try{
                control.insertarCurso(curso);
                System.out.println("Exito. Se ha creado un Nuevo profesor");
            }catch(Exception e){
                System.err.println("Error: "+e);
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void ModificarCursoController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            carreraList = new ArrayList<Carrera>();
            cursoList = new ArrayList<Curso>();
            cicloList = new ArrayList<Ciclo>();
            int codigoSelec = 0;
            String codigo = "";/////////
            int codCiclo = 0;
            int codCarrera = 0;
            String nombre = "";
            int creditos = 0;/////////
            String horas = "";////////
            carrera = new Carrera();
            curso = new Curso();
            ciclo = new Ciclo();
            
            cursoList = control.listarCursos();
            for(int i = 0; i < cursoList.size(); i++){
                System.out.println("Curso número ("+i+")"+"\n"+
                                    "\tCódigo del Curso: "+ cursoList.get(i).getCodigo()+"\n"+
                                    "\tCiclo: "+ cursoList.get(i).getCodigo_ciclo().getCodigo()+"\n"+
                                    "\tCarrera: "+ cursoList.get(i).getCodigo_carrera().getNombre()+"\n"+
                                    "\tNombre del Curso: "+ cursoList.get(i).getNombre()+"\n"+
                                    "\tCantidad de Créditos: "+ cursoList.get(i).getCreditos()+"\n"+
                                    "\tCantidad de Horas: "+ cursoList.get(i).getHoras()+"\n");
            }
            
            System.out.println("Seleccione el número del curso para modificarlo: ");
            codigoSelec = Integer.parseInt(br.readLine());
            codigo = cursoList.get(codigoSelec).getCodigo();
            
            if((codigo != null) && (codigo == cursoList.get(codigoSelec).getCodigo())){
                /*-_-_-_-_-_-_-_-_-_-_-_-CICLOS-_-_-_-_-_-_-_-_-_-_-_-*/
                
                cicloList = control.listarCiclos();
                System.out.println("Lista de Ciclos");
                for(int numCic = 0; numCic < cicloList.size(); numCic++){
                    System.out.println("Ciclo número ("+numCic+")"+"\n"+
                                    "Codigo del Ciclo: "+cicloList.get(numCic).getCodigo()+"\n");
                }
                System.out.print("Seleccione el número del Ciclo: ");
                codCiclo = Integer.parseInt(br.readLine());
                ciclo = cicloList.get(codCiclo);
                cursoList.get(codigoSelec).setCodigo_ciclo(ciclo);

                /*-_-_-_-_-_-_-_-_-_-_-_-CARRERAS-_-_-_-_-_-_-_-_-_-_-_-*/
                
                carreraList = control.listarCarreras();
                System.out.println("Lista de Carreras");
                for(int numCar = 0; numCar < carreraList.size(); numCar++){
                System.out.println("Carrera número ("+numCar+")"+"\n"+
                                    "Codigo del Carrera: "+carreraList.get(numCar).getCodigo()+"\n"+
                                    "Nombre de la Carrera: "+carreraList.get(numCar).getNombre());
                }

                System.out.print("Seleccione el número de la Carrera: ");
                codCarrera = Integer.parseInt(br.readLine());
                carrera = carreraList.get(codCarrera);
                cursoList.get(codigoSelec).setCodigo_carrera(carrera);

                System.out.print("Digite el nombre del curso: ");
                nombre = br.readLine();
                cursoList.get(codigoSelec).setNombre(nombre);
            
                System.out.print("Digite la cantidad de créditos del curso: ");
                creditos = Integer.parseInt(br.readLine());
                cursoList.get(codigoSelec).setCreditos(creditos);   
            
                System.out.print("Digite la cantidad de horas que dura el curso: ");
                horas = br.readLine();
                cursoList.get(codigoSelec).setHoras(horas);


                try{
                    control.modificarCurso(cursoList.get(codigoSelec));
                    System.out.println("Exito. Se ha un Modificado en el Profesor");
                }catch(Exception e){
                    System.err.println("Error: "+e);
                }
            }else{
                System.out.println("Error");
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void EliminarCursoController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int seleccion;
        String codDelete="";
        String carDelete="";
        String cicDelete="";
        cursoList = new ArrayList<Curso>();
        try{
            cursoList = control.listarCursos();
            for(int i = 0; i < cursoList.size(); i++){
                System.out.println("Curso número ("+i+") \n"+
                                                cursoList.get(i).getCodigo()+"\n"+
                                                cursoList.get(i).getCodigo_carrera().getNombre()+"\n"+
                                                cursoList.get(i).getCodigo_ciclo().getCodigo()+"\n");
            }
            System.out.print("Seleccione el número del grupo para poder eliminarlo: ");
            seleccion = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            codDelete = cursoList.get(seleccion).getCodigo(); // p.get(cedula)
            cicDelete = cursoList.get(seleccion).getCodigo_ciclo().getCodigo();
            carDelete = cursoList.get(seleccion).getCodigo_carrera().getCodigo();

            try{
                control.eliminarCurso(codDelete);
            }catch(Exception e){
                System.err.println("Error: "+e);            
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void ListarCursosController()throws Exception{
        cursoList = new ArrayList<Curso>();
        try{
            cursoList = control.listarCursos();
            for(int i = 0; i < cursoList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Curso: "+cursoList.get(i).getCodigo());
                System.out.println("Carrera: "+cursoList.get(i).getCodigo_carrera().getNombre()+" Código: "+cursoList.get(i).getCodigo_carrera().getCodigo());
                System.out.println("Ciclo: "+cursoList.get(i).getCodigo_ciclo().getCodigo());
                System.out.println("Cantidad de Créditos: "+ cursoList.get(i).getCreditos());
                System.out.println("Cantidad de Horas: "+ cursoList.get(i).getHoras());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarCursoController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cursoList = new ArrayList<Curso>();
        String codigo = "";
        try{
            System.out.println("Digite la Código del Curso: ");
            codigo = br.readLine();
            cursoList = control.buscarCurso(codigo);
            for(int i = 0; i < cursoList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Curso: "+cursoList.get(i).getCodigo());
                System.out.println("Carrera: "+cursoList.get(i).getCodigo_carrera().getNombre()+" Código: "+cursoList.get(i).getCodigo_carrera().getCodigo());
                System.out.println("Ciclo: "+cursoList.get(i).getCodigo_ciclo().getCodigo());
                System.out.println("Cantidad de Créditos: "+ cursoList.get(i).getCreditos());
                System.out.println("Cantidad de Horas: "+ cursoList.get(i).getHoras());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }


}
