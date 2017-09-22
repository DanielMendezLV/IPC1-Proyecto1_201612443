/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Daniel Mendez
 */
public class Cliente {
    private String nombre;
    private String contrasena;
    private String usuario;
    private Double saldoInicial;
    private Double saldoActual;
    private String codigoEmpresaAtender;
    private Double montoMaximo;
    private String tipoUsuario;
    private Cliente[] listaUsuariosPosiblesTransferir = new Cliente[1];
    private Operacion[] listaOperacionesRealizadas = new Operacion[1];
    private Integer contadorOperacionesPosiblesTransferir=1;
    private Integer contadorUsuariosPosiblesTransferir=1;

    public Integer getContadorUsuariosPosiblesTransferir() {
        return contadorUsuariosPosiblesTransferir;
    }

    public void setContadorUsuariosPosiblesTransferir(Integer contadorUsuariosPosiblesTransferir) {
        this.contadorUsuariosPosiblesTransferir = contadorUsuariosPosiblesTransferir;
    }
    
    

    public Cliente[] getListaUsuariosPosiblesTransferir() {
        return listaUsuariosPosiblesTransferir;
    }

    public void setListaUsuariosPosiblesTransferir(Cliente[] listaUsuariosPosiblesTransferir) {
        this.listaUsuariosPosiblesTransferir = listaUsuariosPosiblesTransferir;
    }
    
    

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getCodigoEmpresaAtender() {
        return codigoEmpresaAtender;
    }

    public void setCodigoEmpresaAtender(String codigoEmpresaAtender) {
        this.codigoEmpresaAtender = codigoEmpresaAtender;
    }

    public Double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(Double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Operacion[] getListaOperacionesRealizadas() {
        return listaOperacionesRealizadas;
    }

    public void setListaOperacionesRealizadas(Operacion[] listaOperacionesRealizadas) {
        this.listaOperacionesRealizadas = listaOperacionesRealizadas;
    }

    public Integer getContadorOperacionesPosiblesTransferir() {
        return contadorOperacionesPosiblesTransferir;
    }

    public void setContadorOperacionesPosiblesTransferir(Integer contadorOperacionesPosiblesTransferir) {
        this.contadorOperacionesPosiblesTransferir = contadorOperacionesPosiblesTransferir;
    }
    
    
    
}
