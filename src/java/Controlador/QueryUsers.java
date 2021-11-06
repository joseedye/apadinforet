/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.UsuarioJpaController;
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
public class QueryUsers extends HttpServlet {

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

        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        try {

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            List<Usuario> usuarios = usuarioDao.findUsuarioEntities();
            Map<String, Object> mapUsuarios = new HashMap<>();

            String cliente = request.getParameter("cliente");
            String proveedor = request.getParameter("proveedor");
            String gerente = request.getParameter("gerente");
            String empleado = request.getParameter("empleado");

            if ((cliente == null || proveedor == null || gerente == null || empleado == null) || ("false".equals(cliente) && "false".equals(proveedor) && "false".equals(gerente) && "false".equals(empleado))) {

                for (int i = 0; i < usuarios.size(); i++) {
                    mapUsuarios.put(i + "", Utileria.usuarioToMap(usuarios.get(i)));
                }

                request.getSession().setAttribute("usuarios", mapUsuarios);
                response.sendRedirect("Administrador/query");

            } else {

                if ("true".equals(cliente)) {

                    for (int i = 0; i < usuarios.size(); i++) {
                        int tipo = usuarios.get(i).getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 3) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(usuarios.get(i)));
                        }

                    }

                }
                if ("true".equals(proveedor)) {

                    for (int i = 0; i < usuarios.size(); i++) {
                        int tipo = usuarios.get(i).getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 4) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(usuarios.get(i)));
                        }

                    }

                }
                if ("true".equals(gerente)) {

                    for (int i = 0; i < usuarios.size(); i++) {
                        int tipo = usuarios.get(i).getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo == 2) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(usuarios.get(i)));
                        }

                    }

                }
                if ("true".equals(empleado)) {

                    for (int i = 0; i < usuarios.size(); i++) {
                        int tipo = usuarios.get(i).getIdTipoUsuario().getIdTipoUsuario();
                        if (tipo >= 5) {
                            mapUsuarios.put(i + "", Utileria.usuarioToMap(usuarios.get(i)));
                        }

                    }

                }

                request.getSession().setAttribute("cliente", cliente);
                request.getSession().setAttribute("proveedor", proveedor);
                request.getSession().setAttribute("gerente", gerente);
                request.getSession().setAttribute("empleado", empleado);

                request.getSession().setAttribute("usuarios", mapUsuarios);
                response.sendRedirect("Administrador/query");

            }

        } catch (Exception e) {
            request.getSession().setAttribute("msg", "Error,al consultar usuarios!");
            response.sendRedirect("Administrador/perfil.jsp");
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
