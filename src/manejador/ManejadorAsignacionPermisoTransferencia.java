/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejador;

import consts.Const;
import pojo.Cliente;

/**
 *
 * @author danie
 */
public class ManejadorAsignacionPermisoTransferencia {
    private Cliente[] listaConactosPosibleTransferencia;
    private Integer contador=0;
    private Cliente clienteAAgregarContactos;

    public Cliente[] getListaConactosPosibleTransferencia() {
        return listaConactosPosibleTransferencia;
    }

    public void setListaConactosPosibleTransferencia(Cliente[] listaConactosPosibleTransferencia) {
        this.listaConactosPosibleTransferencia = listaConactosPosibleTransferencia;
    }

    public Cliente getClienteAAgregarContactos() {
        return clienteAAgregarContactos;
    }

    public void setClienteAAgregarContactos(Cliente clienteAAgregarContactos) {
        this.clienteAAgregarContactos = clienteAAgregarContactos;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }
    
    
    public ManejadorAsignacionPermisoTransferencia(Cliente clienteSeleccionado){
        this.clienteAAgregarContactos = clienteSeleccionado;
        this.contador = clienteSeleccionado.getContadorUsuariosPosiblesTransferir();
        this.listaConactosPosibleTransferencia = clienteSeleccionado.getListaUsuariosPosiblesTransferir();
        
    }
    
   
    
    
}
