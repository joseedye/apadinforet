/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.EstatusSolicitudJpaController;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.EstatusSolicitud;
import DTO.Solicitud;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
public class assign extends HttpServlet {

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
        SolicitudJpaController solicitudJpa = new SolicitudJpaController(emf);
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(emf);
        EstatusSolicitudJpaController estsolicitudJpa = new EstatusSolicitudJpaController(emf);

        //id empleado
        int id = Integer.parseInt(request.getParameter("cod"));
        //id solicitud
        int ids = Integer.parseInt(request.getParameter("ids"));
        //id cliente
        int idus = Integer.parseInt(request.getParameter("idus"));

        Solicitud s = solicitudJpa.findSolicitud(ids);
        s.setIdSolucionador(usuarioJpa.findUsuario(id));
        s.setEstatus(estsolicitudJpa.findEstatusSolicitud(2));
        try {
            solicitudJpa.edit(s);
        } catch (Exception e) {

        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("SeeApplication.do?idUserQuery="+idus);
        return;
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
