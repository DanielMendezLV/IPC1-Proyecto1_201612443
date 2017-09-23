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
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.DenominacionBillete;

/**
 *
 * @author danie
 */
public class VentanaRetiroEfectivo extends JFrame{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    
     public VentanaRetiroEfectivo(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
        
    }
    
    public VentanaRetiroEfectivo(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
       this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(500,300);
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
        JLabel  lblF= new JLabel("Fecha : ", JLabel.CENTER);
        
        TextField   lblFechaYHora= new TextField (10);
        lblFechaYHora.setText(dateFormat.format(date));
        //lblFechaYHora.setColumns(5);
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
        
        JLabel  lblDenominacion= new JLabel("Cantidad : ", JLabel.CENTER);
        lblDenominacion.setBorder(emptyBorderLabel);
        
        TextField   txtCantidad= new TextField (15);
        
        JPanel panelHorizontal = new JPanel();
        panelHorizontal.setSize(100,100);
        BoxLayout boxLayoutTrans = new BoxLayout(panelHorizontal, BoxLayout.X_AXIS);
        panelHorizontal.setLayout(boxLayoutTrans);
        
        
        panelHorizontal.add(lblDenominacion);
        panelHorizontal.add(txtCantidad);
         
        
        JButton btnAgregar = new JButton();     
        btnAgregar.setText("Extraer dinero");
        
        JPanel panelHorizontalBtn = new JPanel();
        panelHorizontalBtn.setSize(100,100);
        Border emptyBorderBt = BorderFactory.createEmptyBorder(10, 0, 0, 0);
        panelHorizontalBtn.setBorder(emptyBorderBt);
        panelHorizontalBtn.add(btnAgregar);
        
        contentPane.add(panelHorizontalBtn, BorderLayout.LINE_END);
        panelMedio.add(panelHorizontal);
        contentPane.add(panelMedio, BorderLayout.LINE_START);
        
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularExtraccion(Double.parseDouble(txtCantidad.getText()),"");
            }
        });
        
    }
    
    public void calcularExtraccion(Double numeroCalcular, String listaCalculo){
        DenominacionBillete[] lsDeno = mDenominacion.getListaDenominacion();
        
        
        for(int s=0; s<lsDeno.length;s++){
            //Encuentra un divisor exacto
            if(numeroCalcular%lsDeno[s].getValor()==0){
                //Hay en existencia
                Double resultadoDivision = numeroCalcular/lsDeno[s].getValor();
                
                if(resultadoDivision<lsDeno[s].getCantidad()){
                    if(listaCalculo.isEmpty()){
                        listaCalculo = resultadoDivision + " Billetes de: "+ lsDeno[s].getValor();
                    }else{
                        listaCalculo = listaCalculo+ ","+ resultadoDivision + "-Billetes de-"+ lsDeno[s].getValor();
                    }
                    String val = resultadoDivision.toString().replace(".0", "");
                    Integer valor = Integer.parseInt(val);
                    lsDeno[s].setCantidad(lsDeno[s].getCantidad()-valor);
                }else{
                    
                }
            }else{
                
            }
        }
        
        String path = Const.crearPantallaRetiroEfectivo(listaCalculo.split(","));
                   

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
