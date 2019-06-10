/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Control.Control;
import Entidades.Ciclo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CicloController {
    /*
    private String cod_ciclo;
    private String anno;
    private char estado;
    private char numero;
    private Date fecha_inicio;
    private Date fecha_finalizacion;
    */
    private Control control;
    private Ciclo c;
    private List<Ciclo> cList;
    
    public CicloController(Control control){
        this.control = control;
        this.c = new Ciclo();
        this.cList = new ArrayList<Ciclo>();
    }
    
    public void MenuCiclos()throws Exception, GlobalException, NoDataException{
        boolean selec = true;
        while(selec == true){
            System.out.println("Módulo de Ciclo");
            System.out.println("-----------------------------------");
            System.out.println("1- Ingresar un Ciclo nuevo");
            System.out.println("2- Modificar un Ciclo");
            System.out.println("3- Eliminar un Ciclo");
            System.out.println("4- Lista de Ciclo");
            System.out.println("5- Buscar Ciclo por codigo");
            System.out.println("6- Buscar Ciclo por año");
            System.out.println("7- Salir del Módulo de Ciclo");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 1://Ingresar un Ciclo nuevo
                        InsertarCicloController();
                        break;
                    case 2://Modificar un Ciclo
                        ModificarCicloController();
                        break;
                    case 3://Eliminar un Ciclo
                        EliminarCicloController();
                        break;
                    case 4://Lista de Ciclo
                        ListarCicloController();
                        break;
                    case 5://Buscar Ciclo por cédula
                        BuscarCicloCodigo();
                        break;
                    case 6://Buscar Ciclo por anno
                        BuscarCicloAno();
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
    
    public void InsertarCicloController() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{    
            String cod_ciclo = "";
            String anno = "";
            String estado = "";
            String numero = "";    
            String fecha_inicio = "";
            String fecha_finalizacion = "";
            c = new Ciclo();
            
            System.out.println("----------Digite los Datos del Nuevo Ciclo----------");
            System.out.print("Digite el codigo del ciclo: ");
            cod_ciclo = br.readLine();
            c.setCodigo(cod_ciclo);
            
            System.out.println("Digite el año del ciclo: ");
            anno = br.readLine();
            c.setAno(anno);
            
            System.out.println("Digite el estado del ciclo (1: Activo | 0:Inactivo): ");
            estado = br.readLine();
            c.setEstado(estado.charAt(0));
            
            System.out.println("Digite el numero del ciclo (1: Primer Ciclo | 2: Segundo Ciclo): ");
            numero = br.readLine();
            c.setNumero(numero.charAt(0));
            
            System.out.println("Digite el la fecha de inicio del ciclo: ");
            System.out.print("Formato de Fecha DD/MM/YYYY: ");
            fecha_inicio = br.readLine();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date date = sdf1.parse(fecha_inicio);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
            c.setFecha_inicio(sqlDate);
            
            System.out.println("Digite el la fecha de finalizacion del ciclo: ");
            System.out.print("Formato de Fecha DD/MM/YYYY: ");
            fecha_finalizacion = br.readLine();
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date date2 = sdf2.parse(fecha_finalizacion);
            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime()); 
            c.setFecha_finalizacion(sqlDate2);

            try{
                control.insertarCiclo(c);
                System.out.println("Exito. Se ha creado un Nuevo carrera");
            }catch(Exception e){
                System.err.println("Error: "+e);
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }
    }
    
    public void ModificarCicloController() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cList = new ArrayList<Ciclo>();
        String cod_cicloSelect = "";
        String anno = "";
        String estado = "";
        String numero = "";    
        String fecha_inicio = "";
        String fecha_finalizacion = "";
        int codigo;
        
        try{
            cList = control.listarCiclos();
            for(int i = 0; i < cList.size(); i++){
                System.out.println("Ciclo número ("+i+") "+cList.get(i).getCodigo()+" - "+cList.get(i).getAno());
            }
            System.out.print("Seleccione el número del profesor para poder modificarlo: ");
            codigo = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            cod_cicloSelect = cList.get(codigo).getCodigo(); // p.get(codigo)
            System.out.println("Ha seleccionado el siguiente profesor:");
            System.out.println("Codigo Actual del Ciclo: "+cList.get(codigo).getCodigo()+"\n"+
                                "Ano Actual del Ciclo: "+cList.get(codigo).getAno()+"\n"+
                                "Estado Actual del Ciclo: "+cList.get(codigo).getEstado()+"\n"+
                                "Numero del Ciclo: "+cList.get(codigo).getNumero()+"\n"+
                                "Fecha de Inicio: "+cList.get(codigo).getFecha_inicio()+"\n"+
                                "Fecha de Finalizacion: "+cList.get(codigo).getFecha_finalizacion());
            System.out.println("--------------------------------------------------");
            if(cod_cicloSelect != null){
                System.out.println("Digite el año del ciclo: ");
                anno = br.readLine();
                cList.get(codigo).setAno(anno);

                System.out.println("Digite el estado del ciclo (1: Activo | 0:Inactivo): ");
                estado = br.readLine();
                cList.get(codigo).setEstado(estado.charAt(0));

                System.out.println("Digite el numero del ciclo (1: Primer Ciclo | 2: Segundo Ciclo): ");
                numero = br.readLine();
                cList.get(codigo).setNumero(numero.charAt(0));

                System.out.println("Digite el la fecha de inicio del ciclo: ");
                System.out.print("Formato de Fecha DD/MM/YYYY: ");
                fecha_inicio = br.readLine();
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
                java.util.Date date = sdf1.parse(fecha_inicio);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
                cList.get(codigo).setFecha_inicio(sqlDate);

                System.out.println("Digite el la fecha de finalizacion del ciclo: ");
                System.out.print("Formato de Fecha DD/MM/YYYY: ");
                fecha_finalizacion = br.readLine();
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy");
                java.util.Date date2 = sdf2.parse(fecha_finalizacion);
                java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime()); 
                cList.get(codigo).setFecha_finalizacion(sqlDate2);
            
                try{
                    control.modificarCiclo(cList.get(codigo));
                    System.out.print("Exito. Se ha modifado el Ciclo");
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
    
    public void EliminarCicloController() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int codigo;
        String codDelete = "";
        cList = new ArrayList<Ciclo>();
        try{
            cList = control.listarCiclos();
            for(int i = 0; i < cList.size(); i++){
                System.out.println("Ciclo número ("+i+") "+cList.get(i).getCodigo()+"     "+cList.get(i).getAno());
            }
            System.out.print("Seleccione el número de la carrera para poder eliminarlo: ");
            codigo = Integer.parseInt(br.readLine()); //lee numero que quiere compa
            codDelete = cList.get(codigo).getCodigo(); // p.get(codigo)
            try{
                control.eliminarCiclo(codDelete);
            }catch(Exception e){
                System.err.println("Error: "+e);            
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
        /*
    private String cod_ciclo;
    private String anno;
    private char estado;
    private char numero;
    private Date fecha_inicio;
    private Date fecha_finalizacion;
    */
    public void ListarCicloController() throws Exception {
        cList = new ArrayList<Ciclo>();
        try{
            cList = control.listarCiclos();
            for(int i = 0; i < cList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Ciclo: "+cList.get(i).getCodigo());
                System.out.println("Anno del Ciclo: "+cList.get(i).getAno());
                System.out.println("Estado del Ciclo: "+cList.get(i).getEstado());
                System.out.println("Número del Ciclo: "+cList.get(i).getNumero());
                System.out.println("Fecha de Inicio: "+cList.get(i).getFecha_inicio());
                System.out.println("Fecha de Finalizacion: "+cList.get(i).getFecha_finalizacion());
                System.out.println("-----------------------------------");
            }        
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarCicloCodigo() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String codigo = "";
        cList = new ArrayList<Ciclo>();
        try{
            System.out.println("Digite el Código de la carrera: ");
            codigo = br.readLine();
            cList = control.buscarCiclo(codigo);
            for(int i = 0; i < cList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Ciclo: "+cList.get(i).getCodigo());
                System.out.println("Anno del Ciclo: "+cList.get(i).getAno());
                System.out.println("Estado del Ciclo: "+cList.get(i).getEstado());
                System.out.println("Número del Ciclo: "+cList.get(i).getNumero());
                System.out.println("Fecha de Inicio: "+cList.get(i).getFecha_inicio());
                System.out.println("Fecha de Finalizacion: "+cList.get(i).getFecha_finalizacion());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public void BuscarCicloAno() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String codigo = "";
        cList = new ArrayList<Ciclo>();
        try{
            System.out.println("Digite el Año de la carrera: ");
            codigo = br.readLine();
            cList = control.buscarCicloAno(codigo);
            for(int i = 0; i < cList.size(); i++){
                System.out.println("-----------------------------------");
                System.out.println("Código del Ciclo: "+cList.get(i).getCodigo());
                System.out.println("Anno del Ciclo: "+cList.get(i).getAno());
                System.out.println("Estado del Ciclo: "+cList.get(i).getEstado());
                System.out.println("Número del Ciclo: "+cList.get(i).getNumero());
                System.out.println("Fecha de Inicio: "+cList.get(i).getFecha_inicio());
                System.out.println("Fecha de Finalizacion: "+cList.get(i).getFecha_finalizacion());
                System.out.println("-----------------------------------");
            } 
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }

}
