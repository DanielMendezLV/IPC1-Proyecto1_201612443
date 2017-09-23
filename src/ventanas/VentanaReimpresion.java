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
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.Cliente;
import pojo.DenominacionBillete;
import pojo.Operacion;

/**
 *
 * @author danie
 */
public class VentanaReimpresion extends JFrame implements ActionListener, ItemListener{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    JComboBox cmbDenominacion;
    JComboBox cmbAmigosAsociados;
    
    
     
    public VentanaReimpresion(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaReimpresion(){
        this.configurarPantalla();
    }
    
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(820,200);
        
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Proyecto 1");
        setLocationRelativeTo(null);
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Image miIcono = miPantalla.getImage("src/imagenes/icono1.png");
        setIconImage(miIcono);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public  void agregarComponentes(Container contentPane) {
        contentPane.setLayout(new BorderLayout(5,5));
       
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println();
       
        JLabel  lblNombre= new JLabel("Nombre : "+ mCliente.getClienteLogueado().getNombre(), JLabel.CENTER);
        JLabel  lblSaldo= new JLabel("Saldo actual : " + mCliente.getClienteLogueado().getSaldoActual(), JLabel.CENTER);
        JLabel  lblEmpresa= new JLabel("Empresa: " + mCliente.getClienteLogueado().getCodigoEmpresaAtender() , JLabel.CENTER);
        
        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        
        pnlV.setSize(300,300);
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(lblSaldo);
        pnlV.add(lblEmpresa);
        
        
        panelMedio.add(pnlV);
        
        Border emptyBorderLabel = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        
        JPanel panelHorizontal = new JPanel();
        panelHorizontal.setSize(100,100);
        BoxLayout boxLayoutTrans = new BoxLayout(panelHorizontal, BoxLayout.X_AXIS);
        panelHorizontal.setLayout(boxLayoutTrans);
        
        
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        JLabel  lblUsuariosAsociados= new JLabel("Operaciones realizadas : ", JLabel.CENTER);
        Operacion[] lsOperacion = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
        String[] clSplit = obtenerListaSplit(lsOperacion);
        
        cmbAmigosAsociados = new JComboBox(clSplit);
        cmbAmigosAsociados.setBorder(emptyBorder);
        cmbAmigosAsociados.setSelectedIndex(0);
        cmbAmigosAsociados.addActionListener(this);
        
        panelHorizontal.add(lblUsuariosAsociados);
        panelHorizontal.add(cmbAmigosAsociados);
        
        
        Border emptyBorderBt = BorderFactory.createEmptyBorder(10, 0, 0, 0);
        JButton btnAgregar = new JButton();     
        btnAgregar.setText("Generar ");
        
        JPanel panelHorizontalBtn = new JPanel();
        panelHorizontalBtn.setSize(100,100);
        panelHorizontalBtn.setBorder(emptyBorderBt);
        panelHorizontalBtn.add(btnAgregar);
        
        
        contentPane.add(panelHorizontalBtn, BorderLayout.LINE_END);
        panelMedio.add(panelHorizontal);
        contentPane.add(panelMedio, BorderLayout.LINE_START);
        
        
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {    
                String path = "";
                if(!cmbAmigosAsociados.getSelectedItem().toString().equals("Seleccionar")) {
                    Operacion[] lop = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
                    Integer index = Integer.parseInt(cmbAmigosAsociados.getSelectedItem().toString().split("-")[0]);
                
                    path = Const.crearVoucher(lop[index-1]);
                   

                    try {
                        JOptionPane.showMessageDialog(null, "Voucher generado exitosamente");
                        String url = "C:\\Users\\danie\\Desktop\\IPC1-Proyecto1_201612443\\comprobante\\"+path;
                        String otraURL = "C:\\Program Files\\Internet Explorer\\iexplore.exe \""+url+"\"";
                        Process p = Runtime.getRuntime().exec(otraURL);
                        

                    } catch (IOException ex) {
                        Logger.getLogger(VentanaTransferenciaDeno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
    }
    
    
    public String[] obtenerListaSplit(Operacion[] lsOperacion){
        String listaOP ="";
        for(int i=0; i<lsOperacion.length; i++){
            if(lsOperacion[i]!=null){
                if(listaOP.isEmpty()){
                    listaOP = "Seleccionar," + (i+1) + "-" + lsOperacion[i].getEstudianteQueSeLeTransfirio().getNombre() + "-"+lsOperacion[i].getCantidadTransferida();
                }else{
                    listaOP = listaOP + "," + (i+1) + "-" + lsOperacion[i].getEstudianteQueSeLeTransfirio().getNombre() + "-"+lsOperacion[i].getCantidadTransferida();
                }
                
            }
        }
        return listaOP.split(",");
    }
    
}
