/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DAO.Conexion;
import DAO.PersonaJpaController;
import Util.Utileria;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo
 */
public class TestConexion {
    
    
    
    public static void main(String[] args) {
//        EntityManagerFactory emf = Conexion.getConexion().getBd();
//        PersonaJpaController p = new PersonaJpaController(emf);
//        System.out.println(p.findPersona("10910000001").getNombres());
        
        Utileria.getPaises().toString();
        
        
    }
}
