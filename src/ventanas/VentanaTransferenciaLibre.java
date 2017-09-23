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
import javax.swing.ImageIcon;
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
    
    
    public  void agregarComponentes(Container contentPane) {
        contentPane.setLayout(new BorderLayout(5,5));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
//        pnlV.add(lblF);
//        pnlV.add(lblFechaYHora);
//        
        
        panelMedio.add(pnlV);
        
        Border emptyBorderLabel = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        
        JLabel  lblDenominacion= new JLabel("Cantidad : ", JLabel.CENTER);
        lblDenominacion.setBorder(emptyBorderLabel);
        
        JLabel  lblUsuariosAsociados= new JLabel("Amigos : ", JLabel.CENTER);
        lblUsuariosAsociados.setBorder(emptyBorderLabel);
        
        
        
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        
        Cliente[] lsCliente = mCliente.getClienteLogueado().getListaUsuariosPosiblesTransferir();
        DenominacionBillete[] lsBillete = mDenominacion.getListaDenominacion();

        String[] clSplit = obtenerListaSplit(lsCliente);
        cmbAmigosAsociados = new JComboBox(clSplit);
        cmbAmigosAsociados.setBorder(emptyBorder);
        cmbAmigosAsociados.setSelectedIndex(0);
        cmbAmigosAsociados.addActionListener(this);
        
        TextField   txtCantidad= new TextField (15);
        
        

//        String[] denoSplit = obtenerListaSplit(lsBillete);
//        cmbDenominacion = new JComboBox(denoSplit);
//        cmbDenominacion.setBorder(emptyBorder);
//        cmbDenominacion.setSelectedIndex(0);
//        cmbDenominacion.addActionListener(this);

        
        JPanel panelHorizontal = new JPanel();
        panelHorizontal.setSize(100,100);
        BoxLayout boxLayoutTrans = new BoxLayout(panelHorizontal, BoxLayout.X_AXIS);
        panelHorizontal.setLayout(boxLayoutTrans);

        panelHorizontal.add(lblUsuariosAsociados);
        panelHorizontal.add(cmbAmigosAsociados);
        panelHorizontal.add(lblDenominacion);
        panelHorizontal.add(txtCantidad);
        
        Border emptyBorderBt = BorderFactory.createEmptyBorder(10, 0, 0, 0);
        JButton btnAgregar = new JButton();     
        btnAgregar.setText("Transferir");
        
        JPanel panelHorizontalBtn = new JPanel();
        panelHorizontalBtn.setSize(100,100);
        panelHorizontalBtn.setBorder(emptyBorderBt);
//        BoxLayout boxLayoutBtn = new BoxLayout(panelHorizontalBtn, BoxLayout.Y_AXIS);
//        panelHorizontalBtn.setLayout(boxLayoutBtn);
        panelHorizontalBtn.add(btnAgregar);
        panelHorizontalBtn.add(lblF);
        panelHorizontalBtn.add(lblFechaYHora);
        
        
//        JPanel panelHorizontalFecha = new JPanel();
//        panelHorizontalFecha.setSize(100,100);
////        panelHorizontalFecha.setBorder(emptyBorderBt);
////        BoxLayout boxLayoutBtn = new BoxLayout(panelHorizontalBtn, BoxLayout.Y_AXIS);
////        panelHorizontalBtn.setLayout(boxLayoutBtn);
//        panelHorizontalBtn.add(lblF);
//        panelHorizontalBtn.add(lblFechaYHora);
        
        
//        btnAgregar.setBorder(emptyBorderBt);
        //btnAgregar.setMargin(new Insets(40, 0, 0, 0));
        //p//anelHorizontal.add(btnAgregar);
        
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                if(!cmbAmigosAsociados.getSelectedItem().toString().equals("Seleccionar") ){
                    Double valorTrans = 0.00;
                    Boolean esNumero = false;
                    try{
                        valorTrans = Double.parseDouble(txtCantidad.getText());
                        esNumero = true;
                    }catch(Exception e){
                        
                    }
                    
                    if(esNumero){
                        
                        Cliente clTransfiere = mCliente.getClienteLogueado();
                        Cliente clATransferirDinero = mCliente.obtenerClienteXUsername(cmbAmigosAsociados.getSelectedItem().toString());
                        DenominacionBillete clDenominacion = new DenominacionBillete();
                        clDenominacion.setValor(valorTrans);

                        if(clTransfiere.getSaldoActual() > clDenominacion.getValor()){
                            Double montoT = clATransferirDinero.getSaldoActual() + clDenominacion.getValor();
                            if(montoT < (clATransferirDinero.getMontoMaximo())){
                                clTransfiere.setSaldoActual(clTransfiere.getSaldoActual()-clDenominacion.getValor());
                                clATransferirDinero.setSaldoActual(clATransferirDinero.getSaldoActual()+clDenominacion.getValor());
                                lblSaldo.setText("Saldo actual : " + mCliente.getClienteLogueado().getSaldoActual());
                                Operacion op = new Operacion();

                                op.setCantidadTransferida(clDenominacion.getValor());
                                op.setEstudianteQueSeLeTransfirio(clATransferirDinero);
                                op.setUsuarioRealizo(clTransfiere);
                                op.setSaldoActual(mCliente.getClienteLogueado().getSaldoActual());
                                op.setFechaRealizo(lblFechaYHora.getText());
                                op.setCodBanco(mCliente.getClienteLogueado().getCodigoEmpresaAtender());
                                mCliente.agregarOperacionALista(op, clTransfiere);


                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog (null, "Operación realizada exitosamente, desea generar un voucher?","Warning",dialogButton);
                                String path = "";
                                if(dialogResult == JOptionPane.YES_OPTION){
                                    path = Const.crearVoucher(op);
                                    JOptionPane.showMessageDialog(null, "Voucher generado exitosamente");
                                    
                                    try {
                                        String url = "C:\\Users\\danie\\Desktop\\IPC1-Proyecto1_201612443\\comprobante\\"+path;
                                        String otraURL = "C:\\Program Files\\Internet Explorer\\iexplore.exe \""+url+"\"";
                                        Process p = Runtime.getRuntime().exec(otraURL);
                                    } catch (IOException ex) {
                                        Logger.getLogger(VentanaTransferenciaDeno.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                }   


                                

                            }else{
                                JOptionPane.showMessageDialog(null, "Saldo maximo alcanzado a persona si realiza esta operacion");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                        }
                    
                    }else{
                        JOptionPane.showMessageDialog(null, "La cantidad colocada no es un numero");
                    }
                    
                    
                    
                }else{
                     JOptionPane.showMessageDialog(null, "Por favor seleccione un amigo asociado y una denominacion valida");
                }
                
            }
        });
        
        contentPane.add(panelHorizontalBtn, BorderLayout.LINE_END);
        panelMedio.add(panelHorizontal);
//        contentPane.add(panelHorizontalFecha, BorderLayout.PAGE_END);
        contentPane.add(panelMedio, BorderLayout.LINE_START);
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
