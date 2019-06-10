/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Control.Control;
import Entidades.Ciclo;
import Main.FrontEndDesktop;
import Model.CicloModel;
import Model.CiclosModel;
import View.CicloView;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CicloController {
    Control control;
    CicloView view;
    CicloModel model;
    
    public CicloController(CicloView view, CicloModel model, Control control) 
    {
        model.init();
        this.control= control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Control getControl() {
        return control;
    }
    
    
     public void guardar(){
        CiclosModel ciclosModel = FrontEndDesktop.CICLOS_VIEW.getModel();

        Ciclo nueva = new Ciclo();
        model.clearErrors();
        
        nueva.setCodigo(view.codigoFld.getText());
        if (view.codigoFld.getText().length()==0){
            model.getErrores().put("Codigo", "Codigo requerido");
        }
        nueva.setAno(view.anoFld.getText());
        if (view.anoFld.getText().length()==0){
            model.getErrores().put("Ano", "Ano requerido");
        }
        
        if(view.RBActivo.isSelected()){
            nueva.setEstado('1');
        }else{
            nueva.setEstado('0');
        }
        
        if(view.RBPrimero.isSelected()){
            nueva.setNumero('1');
        }else{
            if(view.RBSegundo.isSelected()){
                nueva.setNumero('2');
            }else{
                nueva.setNumero('3');
            }
        }
        
        nueva.setFecha_inicio(view.getDateFromDatePickerFI()); 
        nueva.setFecha_finalizacion(view.getDateFromDatePickerFF());

        
       // nueva.setTipoInstrumento((TipoInstrumento) view.tipoCmbBox.getSelectedItem());
        
        List<Ciclo> ciclos;
        if (model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                    case FrontEndDesktop.MODO_AGREGAR:
                        control.insertarCiclo(nueva);
                        model.setMensaje("CICLO AGREGADA");
                        model.setCurrent(new Ciclo());                       
                        ciclos = (List<Ciclo>) control.listarCiclos();
                        ciclosModel.setCiclos(ciclos);
                       // carreras = control.buscarCarrera(carrerasModel.getFilter());
                       // carrerasModel.setCarreras(carreras);   
                        view.setVisible(false);
                        break;
                    
                    case FrontEndDesktop.MODO_EDITAR:
                        control.modificarCiclo(nueva);
                        model.setMensaje("CICLO MODIFICADO");
                        model.setCurrent(nueva);
                        ciclos = (List<Ciclo>) control.listarCiclos();
                        ciclosModel.setCiclos(ciclos);
                        //instrumento = domainModel.searchInstrumento(instrumentosModel.getFilter());
                        //instrumentosModel.setInstrumentos(instrumento);
                        view.setVisible(false);
                        break;
                }
            }
            catch(Exception e){
                model.getErrores().put("codigo", "CICLO YA EXISTE");
                model.setMensaje("CICLO YA EXISTE");
                model.setCurrent(nueva);
            }
        }
        else{
            model.setMensaje("ESPACIOS INCOMPLETOS.");
            model.setCurrent(nueva);
        }
    }
    
}
