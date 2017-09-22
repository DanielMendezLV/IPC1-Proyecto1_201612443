/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consts;

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
}
