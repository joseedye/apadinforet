/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.DocumentoJpaController;
import DTO.Documento;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class QueryDocuments extends HttpServlet {

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
     
          Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            DocumentoJpaController docDao = new DocumentoJpaController(emf);

            List<Documento> listDocumentos = docDao.findDocumentoEntities();

//            if (user.get("TipoUsuario").equals("Docente")) {
//                List<Documento> listTemp = new ArrayList<>();
//                for (Documento documento : listDocumentos) {
//                    if (documento.getIsPublic()) 
//                        listTemp.add(documento);                                    }
//                listDocumentos = listTemp;
//            }
//
//            //Consulta por idTipoDocumento
//            if (idTipoDocumento != null) {                
//                int idTipoDoc = Integer.valueOf(idTipoDocumento);
//                TipoDocumento tipoSelected = tipoDao.findTipoDocumento(idTipoDoc);
//                List<Documento> listTemp = new ArrayList<>();
//                        
//                for (Documento documento : listDocumentos) {
//                    if (documento.getIdTipoDoc().equals(tipoSelected)) 
//                        listTemp.add(documento);                   
//                }
//                listDocumentos = listTemp;
//                req.getSession().setAttribute("tipoDesc", tipoSelected.getDescripcionTipoDoc());   
//            } 

            //Mapa de documentos
            Map<String, Object> mapDocumentos = new HashMap<>();
            for (int i = 0; i < listDocumentos.size(); i++) {
                mapDocumentos.put(i + "", Utileria.documentoToMap(listDocumentos.get(i)));
            }
            
//            req.getSession().setAttribute("tipoDocumentos", mapTipos);
            req.getSession().setAttribute("documentos", mapDocumentos);
            res.sendRedirect(user.get("TipoUsuario") + "/consultadoc");

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al consultar Documentos");
            res.sendRedirect(user.get("TipoUsuario") + "/consultadoc");
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
