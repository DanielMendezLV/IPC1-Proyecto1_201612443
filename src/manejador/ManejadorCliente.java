/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejador;

import consts.Const;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.table.AbstractTableModel;
import pojo.Cliente;
import pojo.Operacion;

/**
 *
 * @author danie
 */
public class ManejadorCliente{
    private Cliente[] listaClientes;
    private Integer contador = 5;
    private Cliente clienteLogueado;

    public Cliente[] getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Cliente[] listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }
    
    

    public Cliente getClienteLogueado() {
        return clienteLogueado;
    }

    public void setClienteLogueado(Cliente clienteLogueado) {
        this.clienteLogueado = clienteLogueado;
    }
    
    
    
    public ManejadorCliente(){
        Cliente cl1 = new Cliente();
        Cliente cl2 = new Cliente();
        Cliente cl3 = new Cliente();
        Cliente cl4 = new Cliente();
        Cliente cl5 = new Cliente();
        
//        cl.setContrasena("aux1");
//        cl.setUsuario("ipc1Admin");
//        cl1.setTipoUsuario("admin");

        cl1.setContrasena("dan");
        cl1.setUsuario("dan");
        cl1.setTipoUsuario(Const.ADM);
        
        cl2.setNombre("Marylin Irenia");
        cl2.setContrasena("ire");
        cl2.setUsuario("ire");
        cl2.setTipoUsuario(Const.USR);
        cl2.setSaldoInicial(1500.00);
        cl2.setSaldoActual(1500.00);
        cl2.setCodigoEmpresaAtender(Const.CODBANCOPISTO);
        cl2.setMontoMaximo(2000.00);
        
        cl3.setNombre("Jose Manuel");
        cl3.setContrasena("jm");
        cl3.setUsuario("jm");
        cl3.setTipoUsuario(Const.USR);
        cl3.setSaldoInicial(1200.00);
        cl3.setSaldoActual(1200.00);
        cl3.setCodigoEmpresaAtender(Const.CODBANCOCASH);
        cl3.setMontoMaximo(20300.00);
        
        cl4.setNombre("Joaquin");
        cl4.setContrasena("chapo");
        cl4.setUsuario("chapo");
        cl4.setTipoUsuario(Const.USR);
        cl4.setSaldoInicial(1500.00);
        cl4.setSaldoActual(1500.00);
        cl4.setCodigoEmpresaAtender(Const.CODBANCOPISTO);
        cl4.setMontoMaximo(2000.00);
        
        cl5.setNombre("Mocho");
        cl5.setContrasena("moch");
        cl5.setUsuario("moch");
        cl5.setTipoUsuario(Const.USR);
        cl5.setSaldoActual(1200.00);
        cl5.setSaldoInicial(1200.00);
        cl5.setCodigoEmpresaAtender(Const.CODBANCOCASH);
        cl5.setMontoMaximo(20300.00);
        
        listaClientes = new Cliente[contador];
        listaClientes[0] = cl1;
        listaClientes[1] = cl2;
        listaClientes[2] = cl3;
        listaClientes[3] = cl4;
        listaClientes[4] = cl5;
        
    }
    
    public Boolean verificarCliente(String username, String pass){
        for(int i=0; i<listaClientes.length; i++){
            if(listaClientes[i].getUsuario().equals(username) && listaClientes[i].getContrasena().equals(pass)){
                this.clienteLogueado = listaClientes[i];
                return true;
            }
        }
        return false;
    }
    
    public Boolean verifircarUsuarioSiYaExiste(String username){
        for(int i=0; i<listaClientes.length; i++){
            if(listaClientes[i].getUsuario().equals(username)){
                return true;
            }
        }
        return false;
    }
    
    
    public Cliente obtenerClienteXUsername(String username){
        for(int i=0; i<listaClientes.length; i++){
            if(listaClientes[i].getUsuario().equals(username)){
                return listaClientes[i];
            }
        }
        return null;
    }
    
    public void agregarCliente(Cliente cl){
        
        Cliente[] listaClientesFlotante = this.getListaClientes();
        
        
        if(listaClientesFlotante==null){
            listaClientes[contador] =  cl;
        }else{
            contador++;
            listaClientes = new Cliente[contador];

            for(int o=0;  o< (listaClientes.length-1) ; o++){
                listaClientes[o] = listaClientesFlotante[o];
            }
            listaClientes[contador-1] =  cl;
            
        }
        
        
    }
    
    
    public void agregarClienteALista(Cliente cl, Cliente clienteAsingacion){
        
        Cliente[] listaClientesFlotante = clienteAsingacion.getListaUsuariosPosiblesTransferir();
        Integer contadorAgregaciones = clienteAsingacion.getContadorUsuariosPosiblesTransferir();
        Cliente[] listaConactosPosibleTransferencia = null;
        try{
            if(listaClientesFlotante==null){
//                listaConactosPosibleTransferencia = new Cliente[contador];
//                listaConactosPosibleTransferencia[contador-1] =  cl;
            }else{
                listaConactosPosibleTransferencia = new Cliente[clienteAsingacion.getContadorUsuariosPosiblesTransferir()];
                for(int o=0;  o< (listaConactosPosibleTransferencia.length-1) ; o++){
                    listaConactosPosibleTransferencia[o] = listaClientesFlotante[o];
                }
                listaConactosPosibleTransferencia[contadorAgregaciones-1] =  cl;
            }
            clienteAsingacion.setContadorUsuariosPosiblesTransferir(contadorAgregaciones+1);
            clienteAsingacion.setListaUsuariosPosiblesTransferir(listaConactosPosibleTransferencia);
        }catch(Exception e){
            System.out.println("..");
        }
       
    }
    
    public void agregarOperacionALista(Operacion op, Cliente clienteOpero){
        
        Operacion[] listaOP = clienteOpero.getListaOperacionesRealizadas();
        Integer contadorAgregaciones = clienteOpero.getContadorOperacionesPosiblesTransferir();
        Operacion[] listaOperacionTransferencia = null;
        
        try{
            if(listaOP==null){
//                listaConactosPosibleTransferencia = new Cliente[contador];
//                listaConactosPosibleTransferencia[contador-1] =  cl;
            }else{
                listaOperacionTransferencia = new Operacion[contadorAgregaciones];
                for(int o=0;  o< (listaOperacionTransferencia.length-1) ; o++){
                    listaOperacionTransferencia[o] = listaOP[o];
                }
                listaOperacionTransferencia[contadorAgregaciones-1] =  op;
            }
            clienteOpero.setContadorOperacionesPosiblesTransferir(contadorAgregaciones+1);
            clienteOpero.setListaOperacionesRealizadas(listaOperacionTransferencia);
        }catch(Exception e){
            System.out.println("..");
        }
       
    }
    
   
    
    
}
