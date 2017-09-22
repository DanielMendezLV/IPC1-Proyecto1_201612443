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
import javax.swing.ImageIcon;
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

/**
 *
 * @author danie
 */
public class VentanaAdministrativa extends JFrame{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    JTable table;
    JScrollPane jspane;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
    
    
    
    public VentanaAdministrativa(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaAdministrativa(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(800,500);
        setVisible(true);
        setResizable(true);
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
        JLabel  lblContrasena= new JLabel("Contrasena : ", JLabel.LEFT);
        JLabel  lblUsuario= new JLabel("Usuario : ", JLabel.LEFT);
        JLabel  lblSaldoInicial= new JLabel("Saldo Inicial : ", JLabel.LEFT);
        JLabel  lblEmpresa= new JLabel("Empresa (pisto,cash): ", JLabel.LEFT);
        JLabel  lblMonto= new JLabel("Monto Maximo : ", JLabel.LEFT);
        
        JTextField txtNombre = new JTextField(6);
        JTextField txtContrasena = new JTextField(6);
        JTextField txtUsuario = new JTextField(6);
        JTextField txtSaldoInicial = new JTextField(6);
        JTextField txtEmpresa = new JTextField(6);
        JTextField txtMonto = new JTextField(6);
        
        JButton btnAgregar = new JButton();     
        btnAgregar.setSize(50, 50);
        btnAgregar.setText("Agregar Cliente");
      
        
        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        
        pnlV.setSize(300,300);
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(txtNombre);
        pnlV.add(lblContrasena);
        pnlV.add(txtContrasena);
        pnlV.add(lblUsuario);
        pnlV.add(txtUsuario);
        pnlV.add(lblSaldoInicial);
        pnlV.add(txtSaldoInicial);
        pnlV.add(lblEmpresa);
        pnlV.add(txtEmpresa);
        pnlV.add(lblMonto);
        pnlV.add(txtMonto);
        pnlV.add(btnAgregar);
        
        panelMedio.add(pnlV);
        contentPane.add(panelMedio, BorderLayout.LINE_START);
          
        this.pintarTabla(this.getContentPane());
        
    
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(!mCliente.verifircarUsuarioSiYaExiste(txtUsuario.getText())){
                    if(txtEmpresa.getText().equals(Const.CODBANCOCASH) || txtEmpresa.getText().equals(Const.CODBANCOPISTO)){
                        Boolean sonNumeros = true;
                        try{
                            Float.parseFloat(txtSaldoInicial.getText());
                        }catch(Exception e){
                            sonNumeros = false;
                           
                        }
                        
                        try{
                            Float.parseFloat(txtMonto.getText());
                        }catch(Exception e){
                            sonNumeros = false;
                        }
                        
                        if(sonNumeros){
                            Cliente cl = new Cliente();
                            cl.setNombre(txtNombre.getText());
                            cl.setContrasena(txtContrasena.getText());
                            cl.setUsuario(txtUsuario.getText());
                            cl.setSaldoInicial(Double.parseDouble(txtSaldoInicial.getText()));
                            cl.setCodigoEmpresaAtender(txtEmpresa.getText());
                            cl.setMontoMaximo(Double.parseDouble(txtMonto.getText()));
                            cl.setTipoUsuario(Const.USR);
                            mCliente.agregarCliente(cl);
                            setVisible(false); //you can't see me!
                            dispose(); //Destroy the JFrame object
                            VentanaAdministrativa vA = new VentanaAdministrativa(mCliente, mDenominacion);
                            JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");
                        }else{
                             JOptionPane.showMessageDialog(null, "Ingrese un saldo inicial o monto maximo numerico valido");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ingrese un banco existente");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ingrese un usuario que no exista");
                }
            }
        });
        
    }
    
    
    
    public void pintarTabla(Container contentPane){
        //<editor-fold defaultstate="collapsed" desc="Mostrar tabla de usuarios ">
        String[] columns = new String[] {
            "Nombre Usuario", "Contrasena",
            "Usuario", "Saldo Inicial",
            "Empresa", "Monto Maximo"
        };
        
        Cliente[] lsCliente = mCliente.getListaClientes();
        
        Integer contadorNoNulos = 0;
        
        for(int i=0; i<lsCliente.length; i++){
            if(!lsCliente[i].getTipoUsuario().equals(Const.ADM)){
                contadorNoNulos++;
            }
        }
        
        Cliente[] listaNoAdmin = new Cliente[contadorNoNulos];
        Integer contadorAgregaciones = 0;
        
        for(int i=0; i<lsCliente.length; i++){
            if(!lsCliente[i].getTipoUsuario().equals(Const.ADM)){
                listaNoAdmin[contadorAgregaciones] = lsCliente[i];
                contadorAgregaciones++;
            }
        }
        
        
        Object[][] infoClientes = new Object[listaNoAdmin.length][6];
        for(int fil = 0; fil < listaNoAdmin.length ; fil++){
//                for(int col=0; col < lsCliente.length)
            if(listaNoAdmin[fil]!=null){
                infoClientes[fil][0] = listaNoAdmin[fil].getNombre()!=null?listaNoAdmin[fil].getNombre():"";
                infoClientes[fil][1] = listaNoAdmin[fil].getContrasena()!=null?listaNoAdmin[fil].getContrasena():"";
                infoClientes[fil][2] = listaNoAdmin[fil].getUsuario()!=null?listaNoAdmin[fil].getUsuario():"";
                infoClientes[fil][3] = listaNoAdmin[fil].getSaldoInicial()!=null?listaNoAdmin[fil].getSaldoInicial():"";
                infoClientes[fil][4] = listaNoAdmin[fil].getCodigoEmpresaAtender()!=null?listaNoAdmin[fil].getCodigoEmpresaAtender():"";
                infoClientes[fil][5] = listaNoAdmin[fil].getMontoMaximo()!=null?listaNoAdmin[fil].getMontoMaximo():"";
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
