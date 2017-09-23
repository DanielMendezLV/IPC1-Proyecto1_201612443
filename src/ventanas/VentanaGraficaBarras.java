/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import manejador.ManejadorCliente;
import manejador.ManejadorDenominacion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import pojo.Operacion;

/**
 *
 * @author danie
 */
public class VentanaGraficaBarras extends JFrame{
    ManejadorCliente mCliente = new ManejadorCliente();
    ManejadorDenominacion mDenominacion = new ManejadorDenominacion();
    
    public VentanaGraficaBarras(ManejadorCliente mCliente, ManejadorDenominacion mDenominacion){
        this.mCliente = mCliente;
        this.mDenominacion = mDenominacion;
        this.configurarPantalla();
    }
    
    public VentanaGraficaBarras(){
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
        String[] arg1 = {"Enero","Febrero","Marzo","Abril","Mayo","Junio", "Julio", "Agosto", "Septiembre", "Octubre" , "Noviembre" , "Diciembre"};
        Double[] fltArreglo = new Double[12];
        
        Operacion[] listaOperacion = mCliente.getClienteLogueado().getListaOperacionesRealizadas();
        
        for(Integer s = 0; s < fltArreglo.length ; s++){
            fltArreglo[s]=0.00;
        }
         
        for(Integer op = 0; op < listaOperacion.length ; op++){
            String obtuviendoMes = listaOperacion[op].getFechaRealizo().split(" ")[0].split("/")[1];
            fltArreglo[Integer.parseInt(obtuviendoMes)-1]  = fltArreglo[Integer.parseInt(obtuviendoMes)-1] + listaOperacion[op].getCantidadTransferida();
        }
        
        
        JFreeChart barra = null;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        String dia1 = "-";
        

        dataset.addValue(fltArreglo[0], arg1[0],dia1);
        dataset.addValue(fltArreglo[1], arg1[1],dia1);
        dataset.addValue(fltArreglo[2], arg1[2],dia1);
        dataset.addValue(fltArreglo[3], arg1[3],dia1);
        dataset.addValue(fltArreglo[4], arg1[4],dia1);
        dataset.addValue(fltArreglo[5], arg1[5],dia1);
        dataset.addValue(fltArreglo[6], arg1[6],dia1);
        dataset.addValue(fltArreglo[7], arg1[7],dia1);
        dataset.addValue(fltArreglo[8], arg1[8],dia1);
        dataset.addValue(fltArreglo[9], arg1[9],dia1);
        dataset.addValue(fltArreglo[10], arg1[10],dia1);
        dataset.addValue(fltArreglo[11], arg1[11],dia1);
        
        

        JFreeChart graficoBarras = ChartFactory.createBarChart(
                 "Manejo transacciones por mes",        //Título de la gráfica
                 "Meses",           //leyenda Eje horizontal
                 "Porcentaje",      //leyenda Eje vertical
                 dataset,                   //datos
                 PlotOrientation.VERTICAL,  //orientación
                 true,                      //incluir leyendas
                 true,                      //mostrar tooltips
                 true);                   

        graficoBarras.setBackgroundPaint(Color.LIGHT_GRAY);

        CategoryPlot plot =(CategoryPlot) graficoBarras.getPlot();
        //plot.setBackgroundPaint(Color.CYAN); //fondo del grafico
        plot.setDomainGridlinesVisible(true);//lineas de rangos, visibles
        plot.setRangeGridlinePaint(Color.BLACK);//color de las lineas de rangos
        
        JFreeChart chart = graficoBarras;
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);
        
       
    }   
}
