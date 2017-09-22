/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author danie
 */
public class Operacion {
    private Cliente usuarioRealizo;
    private Cliente estudianteQueSeLeTransfirio;
    private Double saldoActual;
    private Double cantidadTransferida;
    private String fechaRealizo;
    private String codBanco;

    public String getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public Cliente getUsuarioRealizo() {
        return usuarioRealizo;
    }

    public void setUsuarioRealizo(Cliente usuarioRealizo) {
        this.usuarioRealizo = usuarioRealizo;
    }

    public Cliente getEstudianteQueSeLeTransfirio() {
        return estudianteQueSeLeTransfirio;
    }

    public void setEstudianteQueSeLeTransfirio(Cliente estudianteQueSeLeTransfirio) {
        this.estudianteQueSeLeTransfirio = estudianteQueSeLeTransfirio;
    }
    
    

   

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Double getCantidadTransferida() {
        return cantidadTransferida;
    }

    public void setCantidadTransferida(Double cantidadTransferida) {
        this.cantidadTransferida = cantidadTransferida;
    }

    public String getFechaRealizo() {
        return fechaRealizo;
    }

    public void setFechaRealizo(String fechaRealizo) {
        this.fechaRealizo = fechaRealizo;
    }
    
    
}
