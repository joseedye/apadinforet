/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.Plus.UsuarioJpaControllerPlus;
import DAO.UsuarioJpaController;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
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
public class UpdatePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");

            String anterior = req.getParameter("ContraAnt");
            String nueva = req.getParameter("ContraNueva");

            String contraguardada = user.get("contra");
            String usuarioo = user.get("user");

            UsuarioJpaControllerPlus usuarioDaoPlus = new UsuarioJpaControllerPlus(emf);

            if (anterior.equals(contraguardada)) {
                Usuario usuario = usuarioDaoPlus.findUsuario(usuarioo);
                usuario.setPassword(nueva);
                usuarioDaoPlus.edit(usuario);
                user = Utileria.usuarioToMap(usuarioDaoPlus.findUsuario(usuarioo));
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("msg", "se ha cambiado exitosamente su contraseña");
                if (Integer.parseInt(user.get("idtipou")) >= 5) {

                    res.sendRedirect(user.get("TipoUsuario").substring(0, 8) + "/perfil");
                } else {

                    res.sendRedirect(user.get("TipoUsuario") + "/perfil");
                }
            } else {
                req.getSession().setAttribute("msg", "Error,no se ha podido cambiar la contraseña");
                if (Integer.parseInt(user.get("idtipou")) >= 5) {

                    res.sendRedirect(user.get("TipoUsuario").substring(0, 8) + "/perfil");
                } else {

                    res.sendRedirect(user.get("TipoUsuario") + "/perfil");
                }

            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error,no se ha podido cambiar la contraseña");
            res.sendRedirect("/Error/errorRedir");

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
