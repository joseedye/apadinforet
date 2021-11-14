/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.DocumentoPropioJpaController;
import DAO.DocumentoSolicitudJpaController;
import DAO.exceptions.NonexistentEntityException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rozo
 */
public class DeleteDoc extends HttpServlet {

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

        int id = Integer.parseInt(request.getParameter("idDoc"));
        String soy = request.getParameter("soy");
        String iddueno = request.getParameter("idDue");
        String ruta = request.getParameter("ruta");
        File archivo;
        String rutatot = getServletContext().getRealPath("/");
        try {
            switch (soy) {

                case "propio":
                    DocumentoPropioJpaController docDao = new DocumentoPropioJpaController(emf);
                    archivo = new File(rutatot+"/"+ruta);
                    archivo.delete();
                    docDao.destroy(id);

                    break;

                case "solicitud":
                    DocumentoSolicitudJpaController docSol = new DocumentoSolicitudJpaController(emf);
                    archivo = new File(rutatot+""+ruta);
                    archivo.delete();
                    docSol.destroy(id);
                    break;
            }
            request.getSession().setAttribute("msg", "¡¡se ha eliminado el documento!!");
            response.sendRedirect("/SeeDocuments.do?idUserQuery=" + iddueno);

        } catch (NonexistentEntityException ex) {

            request.getSession().setAttribute("msg", "¡¡error al eliminar el documento intente de nuevo!!");
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
