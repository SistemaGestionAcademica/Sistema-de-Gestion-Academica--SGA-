/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Control.Control;
import Controller.AlumnoController;
import Controller.CarreraController;
import Controller.CicloController;
import Controller.CursoController;
import Controller.GrupoController;
import Controller.ProfesorController;
import Entidades.Alumno;
import Entidades.Profesor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Usuario1
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Control control = new Control();
        ProfesorController pro = new ProfesorController(control);
        GrupoController gru = new GrupoController(control);
        AlumnoController alu = new AlumnoController(control);
        CarreraController car = new CarreraController(control);
        CicloController cic = new CicloController(control);
        CursoController cur = new CursoController(control);
        try{
            
            boolean selec = true;
            while(selec == true){
                System.out.println("Laboratorio 01-02");
                System.out.println("-----------------------------------");
                System.out.println("1- Módulo de Profesores");
                System.out.println("2- Módulo de Alumnos");
                System.out.println("3- Módulo de Carreras");
                System.out.println("4- Módulo de Ciclos");
                System.out.println("5- Módulo de Cursos");
                System.out.println("6- Módulo de Grupos");
                System.out.println("7- Salir");
                System.out.print("Opcion: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try{
                    int opcion = Integer.parseInt(br.readLine());
                    switch(opcion){
                        case 1:
                            pro.MenuProfesores();
                            break;
                        case 2:
                            alu.MenuAlumnos();
                            break;
                        case 3:
                            car.MenuCarreras();
                            break;
                        case 4:
                            cic.MenuCiclos();
                            break;
                        case 5:
                            cur.MenuCursos();
                            break;
                        case 6:
                            gru.MenuGrupos();
                            break;
                        case 7://Salir
                            selec = false;
                            System.out.println("Ha salido exitosamente");
                            System.exit(0);
                            break;
                        default:
                            System.exit(0);
                    } 
                }catch(IOException e){
                    System.out.println("Error: "+e);
                    System.exit(1);
                }
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
}
