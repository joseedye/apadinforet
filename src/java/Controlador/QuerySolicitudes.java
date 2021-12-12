/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
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
public class QuerySolicitudes extends HttpServlet {

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
        SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
        UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);

        Map<String, Object> mapUsuarios = new HashMap<>();
        Map<String, String> mapcantidad = new HashMap<>();

        int i = 0;
        //para empleados solo los que se le asignen
        if (Integer.parseInt(user.get("idtipou")) >= 5) {

            Usuario empl = usuarioDao.findUsuario(Integer.parseInt(user.get("idUsuario")));
            //traer todas las solicitudes            
            for (Solicitud strp : solicitudDao.findSolicitudEntities()) {

                //donde sea el solucionador 
               
                    
                    if (strp.getIdSolucionador() != null&&strp.getIdSolucionador().getIdUsuario() == empl.getIdUsuario()) {
                        //agrego el usuario al mapa
                        mapUsuarios.put(i + "", Utileria.usuarioToMap(strp.getIdCliente()));

                        //numero de total de solicitudes 
                        mapcantidad.put(i++ + "", strp.getTematica());
                    }
                
            }

            request.getSession().setAttribute("usuarios", mapUsuarios);
            request.getSession().setAttribute("cantidad", mapcantidad);

            response.sendRedirect(user.get("TipoUsuario").substring(0, 8) + "/consultasol");

        } else {

            for (Usuario strp : usuarioDao.findUsuarioEntities()) {

                if (strp.getIdTipoUsuario().getIdTipoUsuario() == 3) {
                    //agrego el usuario al mapa
                    mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));

                    //numero de total de documetos 
                    mapcantidad.put(i++ + "", Utileria.cantidadSolicitudes(strp) + "");
                }
            }
            request.getSession().setAttribute("usuarios", mapUsuarios);
            request.getSession().setAttribute("cantidad", mapcantidad);
            response.sendRedirect(user.get("TipoUsuario") + "/consultasol");
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
