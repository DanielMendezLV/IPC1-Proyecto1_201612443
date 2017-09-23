/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import consts.Const;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.Cliente;
import pojo.DenominacionBillete;
import pojo.Operacion;

public class VentanaMenus extends JFrame{
    ManejadorCliente mCliente;
    ManejadorDenominacion mDenominacion;
    Const mConst = new Const();
    String color = "DARK_GRAY";
    
    public VentanaMenus(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        agregarComponentesPanel(this.getContentPane());
        
        setSize(800,525);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ventana Menus");
        setLocationRelativeTo(null);
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Image miIcono = miPantalla.getImage("src/imagenes/icono1.png");
        setIconImage(miIcono);
    }
    
    public  void agregarComponentesPanel(Container contentPane) {
        contentPane.setLayout(new BorderLayout(5,5));
        
        JPanel pnlPrincipal = new JPanel();
        BoxLayout boxlayoutPrincipal = new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS);
                
        JPanel pnlBotones = new JPanel();
        pnlBotones.setSize(200,200);
        FlowLayout layoutBotones = new FlowLayout();
        layoutBotones.setHgap(5);
        layoutBotones.setVgap(10);
        
        JPanel pnlCuadricula3x3 = new JPanel();
        pnlCuadricula3x3.setSize(100,100);
        GridLayout layout3x3 = new GridLayout(4, 3);
        layout3x3.setHgap(10);
        layout3x3.setVgap(10);
        
        JButton btnSaldoCuenta = new JButton();
        ImageIcon iconoSaldoCuenta= new ImageIcon("src/imagenes/saldoCuenta.png");
        btnSaldoCuenta.setIcon(iconoSaldoCuenta);
        btnSaldoCuenta.setText("Saldo de Cuenta");
                
        JButton btnTransferencia = new JButton();
        ImageIcon iconoTransferencia= new ImageIcon("src/imagenes/ts.png");
        btnTransferencia.setIcon(iconoTransferencia);
        btnTransferencia.setText("Transferencia de Saldo");
         
        JButton btnTransferenciaLibre = new JButton();
        ImageIcon iconoTransferenciaLibre = new ImageIcon("src/imagenes/tl.png");
        btnTransferenciaLibre.setIcon(iconoTransferenciaLibre);
        btnTransferenciaLibre.setText("Transferencia libre de Saldo");
        
        JButton btnSistemaReImpresion = new JButton();
        ImageIcon iconoS = new ImageIcon("src/imagenes/sri.png");
        btnSistemaReImpresion.setIcon(iconoS);
        btnSistemaReImpresion.setText("Sistema de re-impresion");
        
        JButton btnGraficasPie = new JButton();
        ImageIcon iconoGP = new ImageIcon("src/imagenes/gp.png");
        btnGraficasPie.setIcon(iconoGP);
        btnGraficasPie.setText("Gráficas de pie");
        
        JButton btnGraficasBarras = new JButton();
        ImageIcon iconoGB = new ImageIcon("src/imagenes/gb.png");
        btnGraficasBarras.setIcon(iconoGB);
        btnGraficasBarras.setText("Gráficas de barras");
        
        JButton btnRetiroEfectivo = new JButton();
        ImageIcon iconoRE = new ImageIcon("src/imagenes/re.png");
        btnRetiroEfectivo.setIcon(iconoRE);
        btnRetiroEfectivo.setText("Retiro de Efectivo");        
        
        JButton btnLogO = new JButton();
        ImageIcon iconoLO = new ImageIcon("src/imagenes/lo.png");
        btnLogO.setIcon(iconoLO);
        btnLogO.setText("Cerrar sesion");
        
        JButton btnModuloAdmon = new JButton();
        ImageIcon iconoMO = new ImageIcon("src/imagenes/ma.png");
        btnModuloAdmon.setIcon(iconoMO);
        btnModuloAdmon.setText("Modulo de Administracion");
        
        JButton btnAsignarListaAlumnos = new JButton();
        ImageIcon iconoALA = new ImageIcon("src/imagenes/ala.png");
        btnAsignarListaAlumnos.setIcon(iconoALA);
        btnAsignarListaAlumnos.setText("Asignar lista de alumnos");
        
        JButton btnAsignarDenominaciones = new JButton();
        ImageIcon iconoAD = new ImageIcon("src/imagenes/ad.png");
        btnAsignarDenominaciones.setIcon(iconoAD);
        btnAsignarDenominaciones.setText("Asignar Denominaciones");
        
        
        JButton btnVerExistencia = new JButton();
        ImageIcon iconoADs = new ImageIcon("src/imagenes/ad.png");
        btnVerExistencia.setIcon(iconoADs);
        btnVerExistencia.setText("Existencia Billetes");

        
        if(mCliente.getClienteLogueado().getTipoUsuario().equals(Const.USR)){
            float[] hsb = null;
            float[] hsbP = null;
            float hP; 
            float sP;
            float bP;
            if(mCliente.getClienteLogueado().getCodigoEmpresaAtender().equals(Const.CODBANCOCASH)){
                hsb = Color.RGBtoHSB(154, 205, 50, hsb);
                hsbP = Color.RGBtoHSB(154, 154, 154, hsbP);
                hP = hsb[0]; 
                sP = hsb[1];
                bP = hsb[2];
                this.getContentPane().setBackground(Color.getHSBColor(hP, sP, bP));

                //pnlPrincipal.setBackground(new Color (154,50,225));
                
            }else{
                hsb = Color.RGBtoHSB(211, 211, 211, hsb);
                hsbP = Color.RGBtoHSB(211, 154, 154, hsbP);
                hP = hsb[0]; 
                sP = hsb[1];
                bP = hsb[2];
                this.getContentPane().setBackground(Color.getHSBColor(hP, sP, bP));
            }
            float h = hsb[0]; 
            float s = hsb[1];
            float b = hsb[2];
            btnSaldoCuenta.setBackground(Color.getHSBColor(h, s, b));
            btnTransferencia.setBackground(Color.getHSBColor(h, s, b));
            btnTransferenciaLibre.setBackground(Color.getHSBColor(h, s, b));
            btnSistemaReImpresion.setBackground(Color.getHSBColor(h, s, b));
            btnGraficasPie.setBackground(Color.getHSBColor(h, s, b));
            btnGraficasBarras.setBackground(Color.getHSBColor(h, s, b));
            btnRetiroEfectivo.setBackground(Color.getHSBColor(h, s, b));
            btnLogO.setBackground(Color.getHSBColor(h, s, b));
            btnModuloAdmon.setBackground(Color.getHSBColor(h, s, b));
            btnAsignarListaAlumnos.setBackground(Color.getHSBColor(h, s, b));
            btnAsignarDenominaciones.setBackground(Color.getHSBColor(h, s, b));
        }
        
        pnlCuadricula3x3.setLayout(layout3x3);
        
        if(mCliente.getClienteLogueado().getTipoUsuario().equals(Const.USR)){
            pnlCuadricula3x3.add(btnSaldoCuenta);
            pnlCuadricula3x3.add(btnTransferencia);
            pnlCuadricula3x3.add(btnTransferenciaLibre);
            pnlCuadricula3x3.add(btnSistemaReImpresion);
            pnlCuadricula3x3.add(btnGraficasPie);
            pnlCuadricula3x3.add(btnGraficasBarras);
            pnlCuadricula3x3.add(btnRetiroEfectivo);
        }
        
        if(mCliente.getClienteLogueado().getTipoUsuario().equalsIgnoreCase(Const.ADM)){
            pnlCuadricula3x3.add(btnModuloAdmon);
            pnlCuadricula3x3.add(btnAsignarListaAlumnos);
            pnlCuadricula3x3.add(btnAsignarDenominaciones);
            pnlCuadricula3x3.add(btnVerExistencia);
        }
        
        pnlCuadricula3x3.add(btnLogO);
        
        pnlBotones.setLayout(layoutBotones);
        pnlBotones.add(pnlCuadricula3x3);

        pnlPrincipal.setLayout(boxlayoutPrincipal);
        
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
        
        pnlPrincipal.add(pnlBotones);
        
        //Agregando a Panel principal
        contentPane.add(pnlPrincipal);
        
        btnLogO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCliente.setClienteLogueado(null);
                VentanaLogin vm = new VentanaLogin(mCliente,mDenominacion);
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
            }
        });
        
        btnModuloAdmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaAdministrativa va = new VentanaAdministrativa(mCliente,mDenominacion);
//                setVisible(false); //you can't see me!
//                dispose(); //Destroy the JFrame object   
            }
        });
        
        btnAsignarDenominaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaAsignacionDenominacion va = new VentanaAsignacionDenominacion(mCliente,mDenominacion);
            }
        });
        
        btnAsignarListaAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaAsignacionPermisoTransferencia va = new VentanaAsignacionPermisoTransferencia(mCliente,mDenominacion);
            }
        });
        
        btnSaldoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaSaldo va = new VentanaSaldo(mCliente,mDenominacion);
            }
        });
        
        
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cliente[] lsCliente = mCliente.getClienteLogueado().getListaUsuariosPosiblesTransferir();
                DenominacionBillete[] lsBillete = mDenominacion.getListaDenominacion();

                if(lsCliente[0] != null && lsBillete[0] != null){
                    VentanaTransferenciaDeno va = new VentanaTransferenciaDeno(mCliente,mDenominacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe tener usuarios asociados y denominaciones creadas para ingresar a esta pantalla");
                }
                
            }
        }); 
       
         
        btnTransferenciaLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cliente[] lsCliente = mCliente.getClienteLogueado().getListaUsuariosPosiblesTransferir();
                DenominacionBillete[] lsBillete = mDenominacion.getListaDenominacion();

                if(lsCliente[0] != null && lsBillete[0] != null){
                    VentanaTransferenciaLibre va = new VentanaTransferenciaLibre(mCliente,mDenominacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe tener usuarios asociados y denominaciones creadas para ingresar a esta pantalla");
                }
                
                
            }
        });
        
        btnSistemaReImpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operacion[] lsOperacion = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
                if(lsOperacion[0] != null){
                    VentanaReimpresion va = new VentanaReimpresion(mCliente,mDenominacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe tener transacciones para utilizar esta pantalla");
                }
            }
        });
        
        btnGraficasPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operacion[] lsOperacion = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
                if(lsOperacion[0] != null){
                    VentanaGraficaPie va = new VentanaGraficaPie(mCliente,mDenominacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe tener transacciones para utilizar esta pantalla");
                }
            }
        });
        
        btnGraficasBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operacion[] lsOperacion = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
                if(lsOperacion[0] != null){
                    VentanaGraficaBarras va = new VentanaGraficaBarras(mCliente,mDenominacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe tener transacciones para utilizar esta pantalla");
                }
            }
        });
        
        btnVerExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaExistenciaBilletes va = new VentanaExistenciaBilletes(mCliente,mDenominacion);
            }
        });
        
        btnRetiroEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaRetiroEfectivo va = new VentanaRetiroEfectivo(mCliente,mDenominacion);
            }
        });
        
    }
}

