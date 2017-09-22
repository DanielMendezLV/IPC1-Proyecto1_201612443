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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.Cliente;
import pojo.DenominacionBillete;

/**
 *
 * @author danie
 */
public class VentanaTransferenciaLibre extends JFrame implements ActionListener, ItemListener{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    JComboBox cmbDenominacion;
    JComboBox cmbAmigosAsociados;

    public JComboBox getCmbAmigosAsociados() {
        return cmbAmigosAsociados;
    }

    public void setCmbAmigosAsociados(JComboBox cmbAmigosAsociados) {
        this.cmbAmigosAsociados = cmbAmigosAsociados;
    }
    
    

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

    public JComboBox getCmbDenominacion() {
        return cmbDenominacion;
    }

    public void setCmbDenominacion(JComboBox cmbDenominacion) {
        this.cmbDenominacion = cmbDenominacion;
    }
    
    
    
    
    
    public VentanaTransferenciaLibre(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaTransferenciaLibre(){
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
        
        JLabel  lblDenominacion= new JLabel("Nombre : ", JLabel.CENTER);
        JLabel  lblUsuariosAsociados= new JLabel("Amigos : ", JLabel.CENTER);
        
        
        Cliente[] lsCliente = mCliente.getClienteLogueado().getListaUsuariosPosiblesTransferir();
        String[] clSplit = obtenerListaSplit(lsCliente);
        cmbAmigosAsociados = new JComboBox(clSplit);
        cmbAmigosAsociados.setSelectedIndex(0);
        cmbAmigosAsociados.addActionListener(this);
        
        DenominacionBillete[] lsBillete = mDenominacion.getListaDenominacion();
        String[] denoSplit = obtenerListaSplit(lsBillete);
        cmbDenominacion = new JComboBox(clSplit);
        cmbDenominacion.setSelectedIndex(0);
        cmbDenominacion.addActionListener(this);
        
        BoxLayout boxLayoutTrans = new BoxLayout(pnlV, BoxLayout.X_AXIS);
        JPanel panelHorizontal = new JPanel();
        panelHorizontal.setLayout(boxLayoutTrans);
        
        panelHorizontal.add(lblUsuariosAsociados);
        panelHorizontal.add(cmbAmigosAsociados);
        panelHorizontal.add(lblDenominacion);
        panelHorizontal.add(cmbDenominacion);
           
        JPanel panelSMedio = new JPanel();
        
        contentPane.add(panelSMedio.add(panelHorizontal), BorderLayout.CENTER);
//        JPanel pnlPrincipal = new JPanel();
//        JLabel logo = new JLabel();
//        ImageIcon iconoEmpresa;
//        if(mCliente.getClienteLogueado().getTipoUsuario().equals(Const.USR)){
//            if(mCliente.getClienteLogueado().getCodigoEmpresaAtender().equals(Const.CODBANCOCASH)){
//                iconoEmpresa= new ImageIcon("src/imagenes/cash.png");
//            }else{
//                iconoEmpresa= new ImageIcon("src/imagenes/pisto.png");
//            }
//            logo.setIcon(iconoEmpresa);
//            pnlPrincipal.add(logo);
//        }
//        
//        contentPane.add(pnlPrincipal, BorderLayout.LINE_START);
        contentPane.add(panelMedio, BorderLayout.PAGE_START);
    }    
    
    public String[] obtenerListaSplit(Cliente[] lsCliente){
        String listaUsuarios ="";
        for(int i=0; i<lsCliente.length; i++){
            if(!lsCliente[i].getTipoUsuario().equals(Const.ADM)){
                if(listaUsuarios.isEmpty()){
                    listaUsuarios = "Seleccionar," + lsCliente[i].getUsuario();
                }else{
                    listaUsuarios = listaUsuarios + "," + lsCliente[i].getUsuario();
                }
                
            }
        }
        return listaUsuarios.split(",");
    }
    
    public String[] obtenerListaSplit(DenominacionBillete[] lsBillete){
        String listaBillete ="";
        for(int i=0; i<lsBillete.length; i++){
            if(listaBillete.isEmpty()){
                listaBillete = "Seleccionar," + lsBillete[i].getNombre();
            }else{
                listaBillete = listaBillete + "," + lsBillete[i].getNombre();
            }
        }
        return listaBillete.split(",");
    }
    
    
    
     @Override
    public void actionPerformed(ActionEvent e) {
//        String item = (String) listaElegirParaAsignar.getSelectedItem();
//        
//        this.usuarioAplicar = item;
//        if(!item.equals("Seleccionar")){
//            if(itemAnterior.isEmpty()){
//                listaPosiblesAsignacion.removeItemAt(listaElegirParaAsignar.getSelectedIndex());
//                this.pintarTabla(rootPane);
//                
//                this.itemAnterior = "Entro";
//            }else{
//                this.limpiarTabla(false);
//                listaPosiblesAsignacion.removeItemAt(listaElegirParaAsignar.getSelectedIndex());
//                this.pintarTabla(rootPane);
//            }
//        }else{
//           this.usuarioAplicar = "";
//           this.itemAnterior = "";
//           this.limpiarTabla(true);
//        }
//        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
//            if (e.getSource() == mainComboBox) {
//                if (mainComboBox.getSelectedIndex() != 0) {
//                    FirstDialog firstDialog = new FirstDialog(ComboBoxTwo.this,
//                            mainComboBox.getSelectedItem().toString(), "Please wait,  Searching for ..... ");
//                }
//            } 
        }
    }
    
}
