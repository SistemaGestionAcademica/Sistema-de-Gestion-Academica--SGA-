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
import Entidades.Ciclo;
import Entidades.Curso;
import Entidades.Grupo;
import Entidades.Profesor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class GrupoController {
    
    private Control control;
    private Grupo grupo;
    private Curso curso;
    private Profesor profesor;
    
    private List<Grupo> grupoList;
    private List<Curso> cursoList;
    private List<Profesor> profesorList;
    
    public GrupoController(Control control){
        this.control = control;
        this.grupo = new Grupo();
        this.profesor = new Profesor();
        this.curso = new Curso();
        
        this.grupoList = new ArrayList<Grupo>();
        this.cursoList = new ArrayList<Curso>();
        this.profesorList = new ArrayList<Profesor>();
    }
    
    public void MenuGrupos()throws Exception, GlobalException, NoDataException{
        boolean selec = true;
        while(selec == true){
            System.out.println("Módulo de Grupos");
            System.out.println("-----------------------------------");
            System.out.println("1- Ingresar un Grupo nuevo");
            System.out.println("2- Modificar un Grupo");
            System.out.println("3- Eliminar un Grupo");
            System.out.println("4- Lista de Grupo");
            System.out.println("5- Buscar grupos por código");
            System.out.println("6- Salir del Módulo de Grupos");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 1://Ingresar un Grupo nuevo
                        InsertarGrupoController();
                        break;
                    case 2://Modificar un Grupo
                        ModificarGrupoController();
                        break;
                    case 3://Eliminar un Grupo
                        EliminarGruposController();
                        break;
                    case 4://Lista de Grupo
                        ListarGruposController();
                        break;
                    case 5://Buscar Grupo por código
                        BuscarGruposCedula();
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
    
    public void InsertarGrupoController() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            cursoList = new ArrayList<Curso>();
            profesorList = new ArrayList<Profesor>();
            
            String codigo = "";
            String horario = "";
            int cursoSelec;
            int profesorSelec;
            
            grupo = new Grupo();
            curso = new Curso();
            profesor = new Profesor();
            
            System.out.println("----------Digite los Datos del Nuevo Grupo----------");
            System.out.print("Digite el código del grupo: ");
            codigo = br.readLine();
            grupo.setCodigo(codigo);
            
            /*-_-_-_-_-_-_-_-_-_-_-_-_CURSOS_-_-_-_-_-_-_-_-_-_-_-_-*/
            cursoList = control.listarCursos();
            System.out.println("Lista de Cursos");
            for(int i = 0; i < cursoList.size(); i++){
                System.out.println("Curso número ("+i+")"+"\n"+
                                    "Nombre del Curso: "+cursoList.get(i).getNombre()+"\n"+
                                    "Codigo del Curso: "+cursoList.get(i).getCodigo());
            }
            System.out.print("Seleccione el número de Curso: ");
            cursoSelec = Integer.parseInt(br.readLine());
            curso = cursoList.get(cursoSelec);
            grupo.setCurso(curso);
            
            /*-_-_-_-_-_-_-_-_-_-_-_-_PROFESORES_-_-_-_-_-_-_-_-_-_-_-_-*/
            profesorList = control.listaProfesores();
            System.out.println("Lista de Profesores");
            for(int j = 0; j < profesorList.size(); j++){
                System.out.println("Curso número ("+j+")"+"\n"+
                                    "Nombre del profesor: "+profesorList.get(j).getNombre()+"\n"+
                                    "Identificación del profesor: "+profesorList.get(j).getCedula());
            }
            System.out.print("Seleccione el número de Profesor: ");
            profesorSelec = Integer.parseInt(br.readLine());
            profesor = profesorList.get(profesorSelec);
            grupo.setProfesor(profesor);
            
            System.out.println("Digite el horario del grupo: "); 
            horario = br.readLine();
            grupo.setHorario(horario);

            try{
                control.insertarGrupo(grupo);
                System.out.println("Exito. Se ha creado un Nuevo profesor");
            }catch(Exception e){
                System.err.println("Error: "+e);
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void ModificarGrupoController()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            grupoList = new ArrayList<Grupo>();
            cursoList = new ArrayList<Curso>();
            profesorList = new ArrayList<Profesor>();
            int codigoSelec;
            String codigo = "";
            String horario = "";
            int cursoSelec;
            int profesorSelec;
            
            grupo = new Grupo();
            curso = new Curso();
            profesor = new Profesor();
            
            grupoList = control.listaGrupos();
            for(int i = 0; i < grupoList.size(); i++){
                System.out.println("Grupo número ("+i+")"+"\n"+
                                    "\tCódigo del Grupo: "+grupoList.get(i).getCodigo()+"\n"+
                                    "\tCurso: "+grupoList.get(i).getCurso().getNombre()+"\n"+
                                    "\tProfesor encargado del Grupo: "+grupoList.get(i).getProfesor().getNombre()+"\n"+
                                    "\tHorario del Grupo: "+grupoList.get(i).getHorario());
            }
            
            System.out.println("Seleccione un el número del grupo para modificarlo: ");
            codigoSelec = Integer.parseInt(br.readLine());
            codigo = grupoList.get(codigoSelec).getCodigo();
            
            if((codigo != null) && (codigo == grupoList.get(codigoSelec).getCodigo())){
                /*-_-_-_-_-_-_-_-_-_-_-_-_CURSOS_-_-_-_-_-_-_-_-_-_-_-_-*/
                
                cursoList = control.listarCursos();
                System.out.println("Lista de Cursos");
                for(int numCurso = 0; numCurso < cursoList.size(); numCurso++){
                    System.out.println("Curso número ("+numCurso+")"+"\n"+
                                        "Nombre del Curso: "+cursoList.get(numCurso).getNombre()+"\n"+
                                        "Codigo del Curso: "+cursoList.get(numCurso).getCodigo());
                }
                System.out.print("Seleccione el número de Curso: ");
                cursoSelec = Integer.parseInt(br.readLine());
                curso = cursoList.get(cursoSelec);
                grupoList.get(codigoSelec).setCurso(curso);

                /*-_-_-_-_-_-_-_-_-_-_-_-_PROFESORES_-_-_-_-_-_-_-_-_-_-_-_-*/
                
                profesorList = control.listaProfesores();
                System.out.println("Lista de Profesores");
                for(int numProfesor = 0; numProfesor < profesorList.size(); numProfesor++){
                    System.out.println("Curso número ("+numProfesor+")"+"\n"+
                                        "Nombre del profesor: "+profesorList.get(numProfesor).getNombre()+"\n"+
                                        "Identificación del profesor: "+profesorList.get(numProfesor).getCedula());
                }
                System.out.print("Seleccione el número de Profesor: ");
                profesorSelec = Integer.parseInt(br.readLine());
                profesor = profesorList.get(profesorSelec);
                grupoList.get(codigoSelec).setProfesor(profesor);

                System.out.println("Digite el horario del grupo: "); 
                horario = br.readLine();
                grupoList.get(codigoSelec).setHorario(horario);

                try{
                    control.editarGrupo(grupoList.get(codigoSelec));
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
    
    public void EliminarGruposController() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int seleccion;
        String codDelete="";
        String curDelete="";
        String proDelete="";
        grupoList = new ArrayList<Grupo>();
        try{
            grupoList = control.listaGrupos();
            for(int i = 0; i < grupoList.size(); i++){
                System.out.println("Profesor número ("+i+") "+grupoList.get(i).getCodigo()+"\n"+
                                                              grupoList.get(i).getCurso().getNombre()+"\n"+
                                                                grupoList.get(i).getProfesor().getNombre()+"\n");
            }
            System.out.print("Seleccione el número del grupo para poder eliminarlo: ");
            seleccion = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            codDelete = grupoList.get(seleccion).getCodigo(); // p.get(cedula)
            curDelete = grupoList.get(seleccion).getCurso().getCodigo();
            proDelete = grupoList.get(seleccion).getProfesor().getCedula();
            try{
                control.eliminarGrupo(codDelete, curDelete, proDelete);
            }catch(Exception e){
                System.err.println("Error: "+e);            
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void ListarGruposController() throws Exception{
        grupoList = new ArrayList<Grupo>();
        try{
            grupoList = control.listaGrupos();
            for(int i = 0; i < grupoList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Grupo: "+grupoList.get(i).getCodigo());
                System.out.println("Curso: "+grupoList.get(i).getCurso().getCodigo()+" Código: "+grupoList.get(i).getCurso().getNombre());
                System.out.println("Carrera: "+grupoList.get(i).getCurso().getCodigo_carrera().getNombre());
                System.out.println("Profesor: "+grupoList.get(i).getProfesor()+" Correo electrónico: "+grupoList.get(i).getProfesor().getEmail()+"");
                System.out.println("Horario: "+grupoList.get(i).getHorario());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarGruposCedula(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String codigo = "";
        grupoList = new ArrayList<Grupo>();
        try{
            System.out.println("Digite la Código del grupo: ");
            codigo = br.readLine();
            grupoList = control.buscarGrupo(codigo);
            for(int i = 0; i < grupoList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Grupo: "+grupoList.get(i).getCodigo());
                System.out.println("Curso: "+grupoList.get(i).getCurso().getCodigo()+" Código: "+grupoList.get(i).getCurso().getNombre());
                System.out.println("Carrera: "+grupoList.get(i).getCurso().getCodigo_carrera().getNombre());
                System.out.println("Profesor: "+grupoList.get(i).getProfesor()+" Correo electrónico: "+grupoList.get(i).getProfesor().getEmail()+"");
                System.out.println("Horario: "+grupoList.get(i).getHorario());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
