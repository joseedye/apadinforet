/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.InfoBancariaJpaController;
import DTO.InfoBancaria;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rozo
 */
public class BankInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        EntityManagerFactory emf = Conexion.getConexion().getBd();
        InfoBancariaJpaController infoJpa = new InfoBancariaJpaController(emf);
        
        try {
            
        
        
        String numerocuenta = request.getParameter("numerocuenta");
        String tipocuenta = request.getParameter("tipocuenta");
        String codigobaco = request.getParameter("codigobaco");
        String sucursal = request.getParameter("sucursal");
        String ciudad = request.getParameter("ciudad");
        String banco = request.getParameter("banco");
        String forma = request.getParameter("forma");
        String pais = request.getParameter("pais");
        
        
        InfoBancaria info = new InfoBancaria();
        
        info.setIdUsuario(Integer.parseInt(user.get("idUsuario")));
        info.setNumeroCuenta(numerocuenta);
        info.setTipoCuenta(tipocuenta);
        info.setCodBanco(codigobaco);
        info.setSucursal(sucursal);
        info.setFormaPago(forma); 
        info.setCiudad(ciudad);
        info.setBanco(banco); 
        info.setPais(pais);
        
        if(infoJpa.findInfoBancaria(Integer.parseInt(user.get("idUsuario")))!=null){
           infoJpa.edit(info);
           
          
                    InfoBancaria banca = new InfoBancaria();
                    banca=infoJpa.findInfoBancaria(Integer.parseInt(user.get("idUsuario")));
                     Map<String, String> BancaMap = Utileria.bancaToMap(banca);
                     request.getSession().setAttribute("banca", BancaMap);
        }else{
            infoJpa.create(info);
        }
        
        request.getSession().setAttribute("msg", "informacion guardada correctamente!");
        response.sendRedirect("Proveedor/perfil");
      
        } catch (Exception e) {
            
            request.getSession().setAttribute("msg", "Error,  intente nuevamente!");
            response.sendRedirect("/Error/errorRedir");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
