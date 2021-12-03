/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Util.Utileria;

/**
 *
 * @author rozo
 */
public class testCorreo { 
    
    
    public static void main(String[] args) {
         String dominio = "https://inforet.com/";
            String titulo = "Nuevo Usuario - inforet";
            String cuerpo = "Bienvenido a inforet, sus datos para "
                    + "iniciar sesión son:\n *Usuario: " + "pruebascorreojava@gmail.com" + "\n *Contraseña: " + "103"
                    + "\n \n Visita " + dominio;
            Utileria.enviarCorreo("pruebascorreojava@gmail.com", titulo, cuerpo);
    }
    
}
