/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;

/**
 *
 * @author danie
 */
public class VentanaLogin extends JFrame{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    
    public VentanaLogin(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
        
    }
    
    public VentanaLogin(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
        this.agregarComponentesParaUsuario(this.getContentPane());
        //Configuraciones
        setSize(540,320);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Proyecto 1");
        setLocationRelativeTo(null);
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Image miIcono = miPantalla.getImage("src/imagenes/icono1.png");
        setIconImage(miIcono);
    }
    
    public void agregarComponentesParaUsuario(Container contentPane){
        contentPane.setLayout(new BorderLayout(5,5));
        JLabel headerLabel = new JLabel();      
        
        headerLabel.setText("                                            ! Bienvenido ! Ingrese sus credenciales"); 
        headerLabel.setPreferredSize(new Dimension(200, 50));
        contentPane.add(headerLabel, BorderLayout.PAGE_START);
//        
//        JPanel p = new JPanel(new SpringLayout());

        JLabel  lblNombre= new JLabel("Usuario: ", JLabel.RIGHT);
        JLabel  lblPass = new JLabel("Password: ", JLabel.CENTER);
        JTextField txtUsuario = new JTextField(6);
        JPasswordField txtPass = new JPasswordField(6);      

        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        
        pnlV.setSize(300,300);
        
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
         
        JButton loginButton = new JButton("Login");
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(txtUsuario);
        pnlV.add(lblPass);
        pnlV.add(txtPass);
        pnlV.add(loginButton);
        panelMedio.add(pnlV);

        contentPane.add(panelMedio, BorderLayout.CENTER);
        
        JButton btnLoguear = new JButton();
        btnLoguear.setText("Logueate!");
        btnLoguear.setToolTipText("Loguear usuario");
        
        JLabel logo1 = new JLabel();
        JLabel logo2 = new JLabel();        
        ImageIcon iconoEmpresa1 = new ImageIcon("src/imagenes/cash.png");
        ImageIcon iconoEmpresa2 = new ImageIcon("src/imagenes/pisto.png");
        logo1.setIcon(iconoEmpresa1);
        logo2.setIcon(iconoEmpresa2);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setSize(60,60);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(5);/////
        layout.setVgap(1);///
        
        panelBotones.setLayout(layout);
        panelBotones.add(logo1);
        panelBotones.add(logo2);
        
        contentPane.add(panelBotones, BorderLayout.PAGE_END);
        

        txtPass.setText("dan");
        txtUsuario.setText("dan");

        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(mCliente.verificarCliente(txtUsuario.getText(), txtPass.getText())){
                    VentanaMenus vm = new VentanaMenus(mCliente, mDenominacion);
                    JOptionPane.showMessageDialog(null, "Usuario logueado exitosamente");
                    setVisible(false); //you can't see me!
                    dispose(); //Destroy the JFrame object
                }else{
                    JOptionPane.showMessageDialog(null, "Verifique sus credenciales");
                }
            }
        });
    }
}
