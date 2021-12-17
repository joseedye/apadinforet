/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.TipoSolicitudJpaController;
import DAO.TipoUsuarioJpaController;
import DAO.UsuarioJpaController;
import DTO.TipoUsuario;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AssignEmployes extends HttpServlet {

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
        Map<String, Object> mapUsuarios = new HashMap<>();
        UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
        TipoUsuarioJpaController tipou = new TipoUsuarioJpaController(emf);
        String msg = "";

        String iden = request.getParameter("cod");
        switch (iden) {

            case "1":
                //asignar
                String idu = request.getParameter("idu");
                String idt = request.getParameter("idt");

                Usuario usuario = usuarioDao.findUsuario(Integer.parseInt(idu));
                TipoUsuario tipo = tipou.findTipoUsuario(Integer.parseInt(idt));

                usuario.setIdTipoUsuario(tipo);
                try {
                    usuarioDao.edit(usuario);
                    msg = "Se ha asignado con exito";
                } catch (Exception e) {
                    msg = "No se ha podido asignar el usuario";
                }

                break;

            case "2":

                break;

        }

        List<Usuario> usuarios = usuarioDao.findUsuarioEntities();
        int i = 0;
        for (Usuario usuario : usuarios) {

            if (usuario.getIdTipoUsuario().getIdTipoUsuario() != 3 && usuario.getIdTipoUsuario().getIdTipoUsuario() != 4) {
                mapUsuarios.put(i++ + "", Utileria.usuarioToMap(usuario));
            }
        }

        if (!"".equals(msg)) {
            request.getSession().setAttribute("msg", msg);
        }

        request.getSession().setAttribute("usuarios", mapUsuarios);
        response.sendRedirect("Administrador/asignar.jsp");

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
