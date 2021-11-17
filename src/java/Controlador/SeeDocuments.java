/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.DocumentoPropioJpaController;
import DAO.DocumentoSolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.DocumentoPropio;
import DTO.DocumentoSolicitud;
import DTO.Solicitud;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.util.HashMap;
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
public class SeeDocuments extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        Map<String, String> usersesion = (Map<String, String>) req.getSession().getAttribute("user");
        EntityManagerFactory emf = Conexion.getConexion().getBd();
        DocumentoPropioJpaController docDao = new DocumentoPropioJpaController(emf);
        DocumentoSolicitudJpaController docSol = new DocumentoSolicitudJpaController(emf);
        UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
        
        Map<String, Object> mapDocumento = new HashMap<>();
        Map<String, String> mapUsuario = new HashMap<>();
        int usuario = Integer.parseInt(req.getParameter("idUserQuery"));
        Usuario usuariobuscar = usuarioDao.findUsuario(usuario);
        int i = 0;
        
        mapUsuario = Utileria.usuarioToMap(usuariobuscar);
        //documentos propios
        for (DocumentoPropio str : usuariobuscar.getDocumentoPropioList()) {
            
            mapDocumento.put(i++ + "", Utileria.documentoToMap(str));
            
        }

        //documentos como solucionaodor
        for (Solicitud str : usuariobuscar.getSolicitudList1()) {
            
            for (DocumentoSolicitud strr : str.getDocumentoSolicitudList()) {
                
                mapDocumento.put(i++ + "", Utileria.documentoToMap(strr));
                
            }
            
        }
        
        for (Solicitud str : usuariobuscar.getSolicitudList()) {
            
            for (DocumentoSolicitud strr : str.getDocumentoSolicitudList()) {
                
                mapDocumento.put(i++ + "", Utileria.documentoToMap(strr));
                
            }
            
        }
        req.getSession().setAttribute("usuariobuscado", mapUsuario);        
        req.getSession().setAttribute("documentos", mapDocumento);
        res.sendRedirect(usersesion.get("TipoUsuario") + "/ver_docs");
        
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
