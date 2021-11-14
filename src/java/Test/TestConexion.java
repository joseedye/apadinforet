/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DAO.Conexion;
import DAO.PersonaJpaController;
import Util.Utileria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        
   //     Utileria.getPaises().toString();
         List<String> list1,list2;
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");
        for(int i=0;i<list1.size();i++)
        {
            System.out.print(list1.get(i)+" ");
        }
        System.out.println();
        for(int i=0;i<list2.size();i++)
        {
            System.out.print(list2.get(i)+" ");
        }

        System.out.println();
        //first Solution
        List<String> resultList1 = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
        //first Solution Prints
        System.out.println("first Solution Prints");
        for(int i=0;i<resultList1.size();i++)
        {
            System.out.print(resultList1.get(i)+" ");
        }

        System.out.println();
    }
        
    
}
