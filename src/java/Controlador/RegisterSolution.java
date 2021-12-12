/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.EstatusSolicitudJpaController;
import DAO.SolicitudJpaController;
import DTO.Solicitud;
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
public class RegisterSolution extends HttpServlet {

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
        EstatusSolicitudJpaController estatusJpa = new EstatusSolicitudJpaController(emf);
        String rta ="Solucion cargada con exito";
        
        String mensaje = request.getParameter("mensaje");
        Map<String, String> solicitud = (Map<String, String>) request.getSession().getAttribute("solicitud");
        Solicitud sol = solicitudJpa.findSolicitud(Integer.parseInt(solicitud.get("ids")));
        //sol.setSolucion(mensaje);
        sol.setEstatus(estatusJpa.findEstatusSolicitud(3));
        
        try {
             solicitudJpa.edit(sol);
        } catch (Exception e) {
            rta="Hubo un error Intente nuevamente.";
        }
        
      // request.getSession().setAttribute("solicitud", mapSolicitud);
        response.sendRedirect("QuerySolicitudes.do");
        
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
