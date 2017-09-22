/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejador;

import consts.Const;
import pojo.Cliente;
import pojo.DenominacionBillete;

/**
 *
 * @author danie
 */
public class ManejadorDenominacion {
    private DenominacionBillete[] listaDenominacion;
    private Integer contador = 1;

    public ManejadorDenominacion(){
        DenominacionBillete cl1 = new DenominacionBillete();
        cl1.setCantidad(2000);
        cl1.setNombre("Doscientos");
        cl1.setValor(200.00);
        listaDenominacion = new DenominacionBillete[contador];
        listaDenominacion[0] = cl1;
    }
    
    public DenominacionBillete[] getListaDenominacion() {
        return listaDenominacion;
    }

    public void setListaDenominacion(DenominacionBillete[] listaDenominacion) {
        this.listaDenominacion = listaDenominacion;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }
    
    public void agregarDenominacion(DenominacionBillete cl){
        contador++;
        DenominacionBillete[] listaDenoFlotante = this.getListaDenominacion();
        listaDenominacion = new DenominacionBillete[contador];
        
        for(int o=0;  o< (listaDenominacion.length-1) ; o++){
            listaDenominacion[o] = listaDenoFlotante[o];
        }
        listaDenominacion[contador-1] =  cl;
        
    }
    
    public DenominacionBillete obtenerDenominacionXNombre(String nombre){
        for(int i=0; i<listaDenominacion.length; i++){
            if(listaDenominacion[i].getNombre().equals(nombre)){
                return listaDenominacion[i];
            }
        }
        return null;
    }
    
    
    
}
