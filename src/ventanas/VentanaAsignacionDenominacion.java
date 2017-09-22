/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import consts.Const;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.Cliente;
import pojo.DenominacionBillete;

/**
 *
 * @author danie
 */
public class VentanaAsignacionDenominacion extends JFrame{
    ManejadorCliente mCliente;
    ManejadorDenominacion mDenominacion;
    JTable table;
    JScrollPane jspane;

    public ManejadorCliente getmCliente() {
        return mCliente;
    }

    public void setmCliente(ManejadorCliente mCliente) {
        this.mCliente = mCliente;
    }

    public ManejadorDenominacion getmDenominacion() {
        return mDenominacion;
    }

    public void setmDenominacion(ManejadorDenominacion mDenominacion) {
        this.mDenominacion = mDenominacion;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getJspane() {
        return jspane;
    }

    public void setJspane(JScrollPane jspane) {
        this.jspane = jspane;
    }
    
    
    
    public VentanaAsignacionDenominacion(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
        
    }
    
    public VentanaAsignacionDenominacion(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(540,320);
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
        JLabel  lblNombre= new JLabel("Nombre : ", JLabel.LEFT);
        JLabel  lblCantidad= new JLabel("Cantidad : ", JLabel.LEFT);
        JLabel  lblValor = new JLabel("Valor : ", JLabel.LEFT);
        
        JTextField txtNombre = new JTextField(6);
        JTextField txtCantidad = new JTextField(6);
        JTextField txtValor = new JTextField(6);
        
        JButton btnAgregar = new JButton();     
        btnAgregar.setSize(50, 50);
        btnAgregar.setText("Agregar Denominacion");
      
        
        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        
        pnlV.setSize(300,300);
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(txtNombre);
        pnlV.add(lblCantidad);
        pnlV.add(txtCantidad);
        pnlV.add(lblValor);
        pnlV.add(txtValor);
        pnlV.add(btnAgregar);
        
        panelMedio.add(pnlV);
        contentPane.add(panelMedio, BorderLayout.LINE_START);
          
        this.pintarTabla(this.getContentPane());
        
        
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                Boolean sonNumeros = true;
                try{
                    Float.parseFloat(txtValor.getText());
                }catch(Exception e){
                    sonNumeros = false;

                }

                try{
                    Float.parseFloat(txtCantidad.getText());
                }catch(Exception e){
                    sonNumeros = false;
                }

                if(sonNumeros){
                    DenominacionBillete cl = new DenominacionBillete();
                    cl.setNombre(txtNombre.getText());
                    cl.setValor(Double.parseDouble(txtValor.getText()));
                    cl.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    mDenominacion.agregarDenominacion(cl);
                    setVisible(false); //you can't see me!
                    dispose(); //Destroy the JFrame object
                    VentanaAsignacionDenominacion vA = new VentanaAsignacionDenominacion(mCliente, mDenominacion);
                    JOptionPane.showMessageDialog(null, "Denominacion agregada exitosamente");
                }else{
                     JOptionPane.showMessageDialog(null, "Ingrese un valor o cantidad de manera que sea numerico");
                }
                
            }
        });
    }
    
    
     public void pintarTabla(Container contentPane){
        //<editor-fold defaultstate="collapsed" desc="Mostrar tabla de usuarios ">
        String[] columns = new String[] {
            "Nombre", "Valor",
            "Cantidad"
        };
        
      
        DenominacionBillete[] listaNoAdmin = mDenominacion.getListaDenominacion();
        
        
        
        Object[][] infoClientes = new Object[listaNoAdmin.length][6];
        for(int fil = 0; fil < listaNoAdmin.length ; fil++){
//                for(int col=0; col < lsCliente.length)
            if(listaNoAdmin[fil]!=null){
                infoClientes[fil][0] = listaNoAdmin[fil].getNombre()!=null?listaNoAdmin[fil].getNombre():"";
                infoClientes[fil][1] = listaNoAdmin[fil].getValor()!=null?listaNoAdmin[fil].getValor():"";
                infoClientes[fil][2] = listaNoAdmin[fil].getCantidad()!=null?listaNoAdmin[fil].getCantidad():"";
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
