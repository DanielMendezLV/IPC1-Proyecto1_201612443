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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import manejador.ManejadorAsignacionPermisoTransferencia;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import pojo.Cliente;

/**
 *
 * @author danie
 */


public class VentanaAsignacionPermisoTransferencia extends JFrame implements ActionListener, ItemListener{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    JComboBox listaElegirParaAsignar;
    JComboBox listaPosiblesAsignacion;
    String usuarioAplicar ="";
    String itemAnterior = "";
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

    public JComboBox getListaElegirParaAsignar() {
        return listaElegirParaAsignar;
    }

    public void setListaElegirParaAsignar(JComboBox listaElegirParaAsignar) {
        this.listaElegirParaAsignar = listaElegirParaAsignar;
    }

    public JComboBox getListaPosiblesAsignacion() {
        return listaPosiblesAsignacion;
    }

    public void setListaPosiblesAsignacion(JComboBox listaPosiblesAsignacion) {
        this.listaPosiblesAsignacion = listaPosiblesAsignacion;
    }
    
    
    
    
    
    public VentanaAsignacionPermisoTransferencia(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
        
    }
    
    public VentanaAsignacionPermisoTransferencia(){
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
        
        
        Cliente[] lsCliente = mCliente.getListaClientes();

        
        

        String[] petStrings = obtenerListaSplit(lsCliente);
        
        JLabel  lblNombre= new JLabel("Elija un usuario para asignar : ", JLabel.LEFT);
        listaElegirParaAsignar = new JComboBox(petStrings);
        
        listaElegirParaAsignar.setSelectedIndex(0);
        listaElegirParaAsignar.addActionListener(this);

        
        JLabel  lblUsuario= new JLabel("Elija un usuario para agregar (previamente elija el de asignar) : ", JLabel.LEFT);
        listaPosiblesAsignacion = new JComboBox(petStrings);
        listaPosiblesAsignacion.setSelectedIndex(0);
        
        
        
        
//        listaElegirParaAsignar.addActionListener(this);

        JButton btnAgregar = new JButton();     
        btnAgregar.setSize(50, 50);
        btnAgregar.setText("Asignar");
        
        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        panelMedio.setSize(150,150);
        pnlV.setSize(150,150);
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(listaElegirParaAsignar);
        pnlV.add(lblUsuario);
        pnlV.add(listaPosiblesAsignacion);
        pnlV.add(btnAgregar);
        
        
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String lsA = String.valueOf(listaPosiblesAsignacion.getSelectedItem());
                String usuarioAsignar = String.valueOf(listaElegirParaAsignar.getSelectedItem());
                if(!lsA.equals("Seleccionar")){
                    Cliente clienteAAsignarPersonaParaTransferencia = mCliente.obtenerClienteXUsername(usuarioAsignar);
                    if(clienteAAsignarPersonaParaTransferencia.getListaUsuariosPosiblesTransferir()!=null && 
                            clienteAAsignarPersonaParaTransferencia.getListaUsuariosPosiblesTransferir()[0]==null){
                        mCliente.agregarClienteALista(mCliente.obtenerClienteXUsername(lsA), clienteAAsignarPersonaParaTransferencia);
                        pintarTabla(contentPane);
                    }else{
                        Cliente[] list = clienteAAsignarPersonaParaTransferencia.getListaUsuariosPosiblesTransferir();
                        for(int o=0; o<list.length ; o++){
                            if(list[o].getUsuario().equals((lsA))){
                                JOptionPane.showMessageDialog(null, "El usuario ya se encuentra asignado");
                                return;
                            }
                        }
                        mCliente.agregarClienteALista(mCliente.obtenerClienteXUsername(lsA), clienteAAsignarPersonaParaTransferencia);
                        pintarTabla(contentPane);

                    }
                }
            }}
        );
        
        
        panelMedio.add(pnlV);
        contentPane.add(panelMedio, BorderLayout.LINE_START);
          
        this.pintarTabla(this.getContentPane());
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
    
    public void pintarTabla(Container contentPane){
        //<editor-fold defaultstate="collapsed" desc="Mostrar tabla de usuarios ">
        String[] columns = new String[] {
            "Nombre Usuario", "Nombre" , "Banco"
        };
        
        Cliente[] lsCliente;
        Object[][] infoClientes;
        if(!usuarioAplicar.isEmpty()){
            lsCliente = mCliente.obtenerClienteXUsername(usuarioAplicar).getListaUsuariosPosiblesTransferir();
            
            if(lsCliente[0]!=null){
                try{
                    infoClientes = new Object[lsCliente.length][1];
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);

                    for(int fil = 0; fil < lsCliente.length ; fil++){
                        if(lsCliente[fil]!=null){
                            infoClientes[fil][0] = lsCliente[fil].getUsuario()!=null?lsCliente[fil].getUsuario():"";
                            model.addRow(new Object[]{lsCliente[fil].getUsuario(), lsCliente[fil].getNombre(), lsCliente[fil].getCodigoEmpresaAtender()});
                        }
                    }
                }catch(Exception e){
                    System.out.println("e");
                }
                
                
            } 
        }else{
            infoClientes = new Object[1][1];
            infoClientes[0][0] = "";
            DefaultTableModel model = new DefaultTableModel(infoClientes,columns);
            table = new JTable(model);
            jspane = new JScrollPane(table);
            
            contentPane.add(jspane, BorderLayout.CENTER);
    //</editor-fold>
        }
        
        
       
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String item = (String) listaElegirParaAsignar.getSelectedItem();
        
        this.usuarioAplicar = item;
        if(!item.equals("Seleccionar")){
            if(itemAnterior.isEmpty()){
                listaPosiblesAsignacion.removeItemAt(listaElegirParaAsignar.getSelectedIndex());
                this.pintarTabla(rootPane);
                
                this.itemAnterior = "Entro";
            }else{
                this.limpiarTabla(false);
                listaPosiblesAsignacion.removeItemAt(listaElegirParaAsignar.getSelectedIndex());
                this.pintarTabla(rootPane);
            }
        }else{
           this.usuarioAplicar = "";
           this.itemAnterior = "";
           this.limpiarTabla(true);
        }
        
    }
    
    public void limpiarTabla(Boolean limpiarTodos){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String[] petStrings = obtenerListaSplit(mCliente.getListaClientes());
        if(limpiarTodos){
            listaPosiblesAsignacion.removeAllItems();
            listaElegirParaAsignar.removeAllItems();
        }else{
            listaPosiblesAsignacion.removeAllItems();
        }
        
        
        
        for(int o=0; o <petStrings.length ; o++){
            if(limpiarTodos){
                listaElegirParaAsignar.addItem(petStrings[o]);
                listaPosiblesAsignacion.addItem(petStrings[o]);
            }else{
                listaPosiblesAsignacion.addItem(petStrings[o]);
            }
        }
    
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
