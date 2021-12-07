/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.ExtraProveedorJpaController;
import DAO.TipoUsuarioJpaController;
import DTO.ExtraProveedor;
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
public class Extra_provider extends HttpServlet {

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
        ExtraProveedorJpaController extraJpa = new ExtraProveedorJpaController(emf);
        String mensaje="informacion guardada correctamente!";
        try {
            String fechag = request.getParameter("fechag");
            String resolg = request.getParameter("resolg");
            String ica = request.getParameter("ica");
            String resola = request.getParameter("resola");
            String fechaa = request.getParameter("fechaa");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecg = (fechag == "")||(fechag == null) ? null : formato.parse(fechag);
            Date feca = (fechaa == "")||(fechaa == null) ? null : formato.parse(fechaa);

            ExtraProveedor extra = new ExtraProveedor();
            extra.setIdProveedor(Integer.parseInt(user.get("idUsuario")));
            extra.setFechaGranContr(fecg);
            extra.setResolucionGranContri(resolg);
            extra.setIca(ica);

            extra.setFechaRetenedor(feca);
            extra.setResolucionRetenedor(resola);

//            if (extraJpa.findExtraProveedor(Integer.parseInt(user.get("idUsuario"))) != null) {
//                extraJpa.edit(extra);
//            } else {
                extraJpa.create(extra);
//            }

            ExtraProveedor extraDTO = extraJpa.findExtraProveedor(Integer.parseInt(user.get("idUsuario")));
            if (extraDTO != null) {
                Map<String, String> ExtraMap = Utileria.extraToMap(extraDTO);
                request.getSession().setAttribute("extra", ExtraMap);
                
            }

        } catch (Exception e) {
           
           mensaje = "Error, intente nuevamente!";
            
        }
        request.getSession().setAttribute("msg", mensaje);
        response.sendRedirect("Proveedor/perfil");

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
