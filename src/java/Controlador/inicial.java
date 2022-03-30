/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.ImagenJpaController;
import DAO.TextosJpaController;
import DTO.Imagen;
import DTO.Textos;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
public class inicial extends HttpServlet {

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
        EntityManagerFactory emf = Conexion.getConexion().getBd();
        TextosJpaController textos = new TextosJpaController(emf);
        Map<String, String> maptextos = new HashMap<>();
        List<Textos> lista = textos.findTextosEntities();
        int i = 0;
        for (Textos lista1 : lista) {
            maptextos.put(i++ + "", lista1.getDescripcion());
        }
        ImagenJpaController urlimagenes = new ImagenJpaController(emf);
        List<Imagen> imagenes = urlimagenes.findImagenEntities();
        i = 0;
        Map<String, String> mapimagenes = new HashMap<>();
        for (Imagen img1 : imagenes) {
            mapimagenes.put(img1.getIdImagen() + "", img1.getUrl());
        }
        request.getSession().setAttribute("textos", maptextos);
        request.getSession().setAttribute("imagenes", mapimagenes);
        response.sendRedirect("/index.jsp");

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
