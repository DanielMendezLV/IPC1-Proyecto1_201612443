/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.DenominacionBillete;

/**
 *
 * @author danie
 */
public class VentanaExistenciaBilletes extends JFrame{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    JTable table;
    JScrollPane jspane;
    
    
    public VentanaExistenciaBilletes(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaExistenciaBilletes(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(500,200);
        
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Proyecto 1");
        setLocationRelativeTo(null);
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Image miIcono = miPantalla.getImage("src/imagenes/icono1.png");
        setIconImage(miIcono);
    }
    
    public  void agregarComponentes(Container contentPane) {
        contentPane.setLayout(new BorderLayout(5,5));
        pintarTabla(this.getContentPane());
    }   
    
    
    public void pintarTabla(Container contentPane){
        //<editor-fold defaultstate="collapsed" desc="Mostrar tabla de usuarios ">
        String[] columns = new String[] {
            "Nombre",
            "Cantidad"
        };
        
      
        DenominacionBillete[] listaNoAdmin = mDenominacion.getListaDenominacion();
        
        
        
        Object[][] infoClientes = new Object[listaNoAdmin.length][6];
        for(int fil = 0; fil < listaNoAdmin.length ; fil++){
//                for(int col=0; col < lsCliente.length)
            if(listaNoAdmin[fil]!=null){
                infoClientes[fil][0] = listaNoAdmin[fil].getNombre()!=null?listaNoAdmin[fil].getNombre():"";
                infoClientes[fil][1] = listaNoAdmin[fil].getCantidad()!=null?listaNoAdmin[fil].getCantidad():"";
                
            }

        }
        
        table = null;
        jspane = null;
        
        table = new JTable(infoClientes, columns);
        jspane = new JScrollPane(table);
       
        contentPane.add(jspane, BorderLayout.CENTER);
    //</editor-fold>
    
    }
     
}
