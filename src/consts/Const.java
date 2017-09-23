/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import pojo.Operacion;

/**
 *
 * @author danie
 */
public class Const {
    public static String CODBANCOCASH = "cash";
    public static String CODBANCOPISTO= "pisto";
    public static String ADM = "admin";
    public static String USR = "usuario";
    
    public static String[] obtenerPaletaColoresXBanco(String codBanco){
        String armandoPaleta = "";
        if(codBanco.equals(CODBANCOCASH)){
                            // Principal, Secundario , Complementario
            armandoPaleta = "#3B8686,#79BD9A,#0B486B";
        }else{
            armandoPaleta = "#8C92AC,#7FBFFF,#423F43";
            //Propisto
        }
        return armandoPaleta.split(",");
    }
    
    
     public static String crearVoucher(Operacion op){
        String nombre="";
        String dtFecha = op.getFechaRealizo().replace(" ", "");
        dtFecha = dtFecha.replace("/", "");
        dtFecha = dtFecha.replace(":", "");
        
        try{
           
            
            nombre = "C://Users//danie//Desktop//IPC1-Proyecto1_201612443//comprobante//cm-"+ dtFecha+".html";
            File file = new File(nombre);
  
            //Create the file
            if (file.createNewFile()){
                 //Write Content
                FileWriter writer = new FileWriter(file);
                writer.write("<body bgcolor=\"#A7DBD8\">");
                
                writer.write("<h1 style=\"font-family:verdana;\"> Voucher de pago </h1>");
                writer.write("<h2 style=\"font-family:verdana;\"> Banco:  " + op.getCodBanco() +  "</h2>");
                writer.write("<h2 style=\"font-family:verdana;\"> Fecha:  " + op.getFechaRealizo()+  "</h2>" );
                writer.write("<h3 style=\"font-family:verdana;\"> Saldo Actual: "+ op.getSaldoActual() +  "</h3>");
                writer.write("<h3 style=\"font-family:verdana;\"> Cantidad: "+ op.getCantidadTransferida()+  "</h3>");
                writer.write("<h3 style=\"font-family:verdana;\"> Cuenta de: "+ op.getUsuarioRealizo().getNombre()+  "</h3>");
                writer.write("<h3 style=\"font-family:verdana;\"> Para cuenta de: "+ op.getEstudianteQueSeLeTransfirio().getNombre()+  "</h3>");
                writer.write("</body>");
                writer.close();
                
            }else{
                System.out.println("File already exists.");
            }
            
           
        } catch (IOException e) {
            System.out.println("Fallo");
           // do something
        }
        return "cm-"+ dtFecha+".html";
    }
     
}
