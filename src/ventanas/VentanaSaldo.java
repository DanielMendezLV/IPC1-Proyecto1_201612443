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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;

/**
 *
 * @author daniel
 */
public class VentanaSaldo extends  JFrame{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    
    public VentanaSaldo(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaSaldo(){
        this.configurarPantalla();
    }
    
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(800,525);
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
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println();
       
        JLabel  lblNombre= new JLabel("Nombre : "+ mCliente.getClienteLogueado().getNombre(), JLabel.CENTER);
        JLabel  lblSaldo= new JLabel("Saldo actual : " + mCliente.getClienteLogueado().getSaldoActual(), JLabel.CENTER);
        JLabel  lblFechaYHora= new JLabel("Fecha y hora : "+ dateFormat.format(date), JLabel.CENTER);
        JLabel  lblEmpresa= new JLabel("Empresa: " + mCliente.getClienteLogueado().getCodigoEmpresaAtender() , JLabel.CENTER);
        
        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        
        pnlV.setSize(300,300);
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(lblSaldo);
        pnlV.add(lblFechaYHora);
        pnlV.add(lblEmpresa);
        
        
        panelMedio.add(pnlV);
        
        
        JPanel pnlPrincipal = new JPanel();
        JLabel logo = new JLabel();
        ImageIcon iconoEmpresa;
        if(mCliente.getClienteLogueado().getTipoUsuario().equals(Const.USR)){
            if(mCliente.getClienteLogueado().getCodigoEmpresaAtender().equals(Const.CODBANCOCASH)){
                iconoEmpresa= new ImageIcon("src/imagenes/cash.png");
            }else{
                iconoEmpresa= new ImageIcon("src/imagenes/pisto.png");
            }
            logo.setIcon(iconoEmpresa);
            pnlPrincipal.add(logo);
        }
        
        contentPane.add(pnlPrincipal, BorderLayout.LINE_START);
        contentPane.add(panelMedio, BorderLayout.CENTER);
    }    

    
    
}
