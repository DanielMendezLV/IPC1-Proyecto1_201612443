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
        
        
        JFreeChart barra = null;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String taxi1 = "Taxi 1";
        String taxi2 = "Taxi 2";

        String dia1 = "-";
        String dia2 = "Día 2";
        String dia3 = "Día 3";
        String dia4 = "Día 4";

        dataset.addValue(18, arg1[0],dia1);
        dataset.addValue(15, arg1[1],dia1);
        dataset.addValue(16, arg1[2],dia1);
        dataset.addValue(12, arg1[3],dia1);
        dataset.addValue(12, arg1[4],dia1);
        dataset.addValue(12, arg1[5],dia1);
        dataset.addValue(12, arg1[6],dia1);
        dataset.addValue(12, arg1[7],dia1);
        dataset.addValue(12, arg1[8],dia1);
        dataset.addValue(12, arg1[9],dia1);
        dataset.addValue(12, arg1[10],dia1);
        dataset.addValue(12, arg1[11],dia1);
        
        

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
