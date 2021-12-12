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
import DTO.Solicitud;
import DTO.Usuario;
import Util.Utileria;
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
            DocumentoPropioJpaController docDao = new DocumentoPropioJpaController(emf);
            DocumentoSolicitudJpaController docSol = new DocumentoSolicitudJpaController(emf);
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);

            Map<String, Object> mapUsuarios = new HashMap<>();
            Map<String, String> mapcantidad = new HashMap<>();

            int i = 0;
            //por cada usuario

            String cliente = req.getParameter("cliente");
            String proveedor = req.getParameter("proveedor");
            String gerente = req.getParameter("gerente");
            String empleado = req.getParameter("empleado");
            if ((cliente == null || proveedor == null || gerente == null || empleado == null) || ("false".equals(cliente) && "false".equals(proveedor) && "false".equals(gerente) && "false".equals(empleado))) {

                if (user.get("TipoUsuario").equals("Administrador")) {
                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {

                        //agrego el usuario al mapa
                        mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));

                        //numero de total de documetos 
                        mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");

                    }
                    req.getSession().setAttribute("usuarios", mapUsuarios);
                    req.getSession().setAttribute("cantidad", mapcantidad);
                    res.sendRedirect(user.get("TipoUsuario") + "/consultadoc");
                }
                
                 if (user.get("TipoUsuario").equals("Gerente")) {
                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {

                        //agrego el usuario al mapa
                        mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));

                        //numero de total de documetos 
                        mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");

                    }
                    req.getSession().setAttribute("usuarios", mapUsuarios);
                    req.getSession().setAttribute("cantidad", mapcantidad);
                    res.sendRedirect(user.get("TipoUsuario") + "/consultadoc");
                }
                
                 if (user.get("TipoUsuario").equals("3")) {
                     //see documents
                     Usuario u= usuarioDao.findUsuario(user.get("idUsuario"));
                   mapUsuarios.put(i+ "", Utileria.usuarioToMap(u));  
                   mapcantidad.put(i++ + "", Utileria.cantidadDocumento(u) + "");  
                 }
                 
                 if (Integer.parseInt(user.get("idtipou")) >= 5) {
                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {

                        //agrego el usuario al mapa
                        mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));

                        //numero de total de documetos 
                        mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");

                    }
                    req.getSession().setAttribute("usuarios", mapUsuarios);
                    req.getSession().setAttribute("cantidad", mapcantidad);
                    res.sendRedirect("Empleado/consultadoc");
                }

            } else {

                if ("true".equals(cliente)) {

                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {
                        int tipo = strp.getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 3) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));
                            mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");
                        }
                    }

                }
                if ("true".equals(proveedor)) {

                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {
                        int tipo = strp.getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 4) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));
                            mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");
                        }
                    }

                }
                if ("true".equals(gerente)) {

                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {
                        int tipo = strp.getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 2) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));
                            mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");
                        }
                    }

                }
                if ("true".equals(empleado)) {

                    for (Usuario strp : usuarioDao.findUsuarioEntities()) {
                        int tipo = strp.getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 5) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(strp));
                            mapcantidad.put(i++ + "", Utileria.cantidadDocumento(strp) + "");
                        }
                    }
                }

                req.getSession().setAttribute("cliente", cliente);
                req.getSession().setAttribute("proveedor", proveedor);
                req.getSession().setAttribute("gerente", gerente);
                req.getSession().setAttribute("empleado", empleado);
                req.getSession().setAttribute("usuarios", mapUsuarios);
                req.getSession().setAttribute("cantidad", mapcantidad);
                res.sendRedirect(user.get("TipoUsuario") + "/consultadoc");
            }
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
