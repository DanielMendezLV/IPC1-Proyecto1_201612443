/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import pojo.Cliente;
import pojo.Operacion;

/**
 *
 * @author danie
 */
public class VentanaGraficaPie extends JFrame {
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    
    public VentanaGraficaPie(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaGraficaPie(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(820,500);
        
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
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, "Dinero transferido a contactos");
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);
        
    }   
    
    public PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        Cliente[] listaClientesAsociados = mCliente.getClienteLogueado().getListaUsuariosPosiblesTransferir();
        Operacion[] listaOperacion = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
        
        for(Integer cl = 0; cl < listaClientesAsociados.length ; cl++){
            listaClientesAsociados[cl].setCuantoGastadoEnMi(0.00);
        }
        
        for(Integer cl = 0; cl < listaClientesAsociados.length ; cl++){
            for(Integer op = 0; op < listaOperacion.length ; op++){
                if(listaClientesAsociados[cl].getUsuario().equals(listaOperacion[op].getEstudianteQueSeLeTransfirio().getUsuario())){
                    listaClientesAsociados[cl].setCuantoGastadoEnMi(listaClientesAsociados[cl].getCuantoGastadoEnMi()+ listaOperacion[op].getCantidadTransferida());
                }
            }
        }
        
        Double porcentajeTotal = 100.00;
        for(Integer cl = 0; cl < listaClientesAsociados.length ; cl++){
            if(listaClientesAsociados[cl].getCuantoGastadoEnMi()>0){
                Double porcentajeGastadoEnMi = (100*listaClientesAsociados[cl].getCuantoGastadoEnMi());
                porcentajeGastadoEnMi = porcentajeGastadoEnMi / mCliente.getClienteLogueado().getSaldoInicial();
                result.setValue(listaClientesAsociados[cl].getNombre(), porcentajeGastadoEnMi); 
                porcentajeTotal = porcentajeTotal - porcentajeGastadoEnMi;
            }
        }
        result.setValue("No usado", porcentajeTotal);
        return result;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
            title,                  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
}
