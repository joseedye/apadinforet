/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.UsuarioJpaController;
import DTO.Usuario;
import java.io.IOException;
import java.util.Iterator;
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
public class Notification extends HttpServlet {

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

        Map<String, String> usersesion = (Map<String, String>) request.getSession().getAttribute("user");

        EntityManagerFactory emf = Conexion.getConexion().getBd();
        UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);

        String tipoUser = usersesion.get("TipoUsuario");

        if (tipoUser.equals("Administrador")) {

            try {
                String id = request.getParameter("id");

                if (id != null) {
                    int idotro = Integer.parseInt(id);
                    String redireccion = request.getParameter("redir");
                    Usuario user = usuarioDao.findUsuario(idotro);

                    switch (redireccion) {

                        //acepto
                        case "1":

                            user.setActivo("1");
                            usuarioDao.edit(user);
                            // modificar el usuario como aprovado   
                            break;
                        case "2":

                            //pendiente
                            String comentario = request.getParameter("com");
                            //        envio comentario
                            user.setActivo("2");
                            user.getIdPersona().setComentario(comentario);
                            usuarioDao.edit(user);
                            break;

                    }

                }
            } catch (Exception e) {
                //borrar esto despues
                String nombre = "entro en el catch ";
                String idnuevo = "6";
                request.getSession().setAttribute("msg3", nombre);
                request.getSession().setAttribute("id3", idnuevo);
                response.sendRedirect("Administrador/perfil");

            }

            int flag = 0;
            List<Usuario> lista = usuarioDao.findUsuarioEntities();

            //buscar nuevas solicitudes de usuario nuevo
            for (Iterator<Usuario> iterator = lista.iterator(); iterator.hasNext();) {
                Usuario next = iterator.next();
                if (next.getActivo().equals("3")) {
                    //reditigir a perfil de administrador

                    request.getSession().setAttribute("userImg", "/img/admin.png");
                    request.getSession().setAttribute("msg3", next.getIdPersona().getNombres());
                    request.getSession().setAttribute("id3", next.getIdUsuario() + "");
                    response.sendRedirect("Administrador/perfil");
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                request.getSession().setAttribute("userImg", "/img/admin.png");
                request.getSession().removeAttribute("msg3");
                request.getSession().removeAttribute("id3");
                response.sendRedirect("Administrador/perfil");
            }

        }

        if (tipoUser.equals("Cliente")) {

            //valido que este activo
            String activo = usersesion.get("activo");

            switch (activo) {
                //aceptado
                case "1":
                    //request.getSession().setAttribute("msg", "");
                    response.sendRedirect("Cliente/perfil");
                    request.getSession().setAttribute("i", "1");
                    break;
                //pendiente
                case "2":
                    request.getSession().setAttribute("msg2", usersesion.get("comentario"));
                    request.getSession().setAttribute("i", "2");
                    response.sendRedirect("Cliente/perfil");
                    break;

                //no aprovado
                case "3":
                    request.getSession().setAttribute("msg", "usuario desactivado comuniquese con el administrador");
                    response.sendRedirect("Error/errorRedir");
                    break;

            }

        }

        if (tipoUser.equals("Proveedor")) {

            //valido que este activo
            String activo = usersesion.get("activo");

            switch (activo) {
                //aceptado
                case "1":
                    //request.getSession().setAttribute("msg", "");
                    response.sendRedirect("Proveedor/perfil");
                    request.getSession().setAttribute("i", "1");
                    break;
                //pendiente
                case "2":
                    request.getSession().setAttribute("msg2", usersesion.get("comentario"));
                    request.getSession().setAttribute("i", "2");
                    response.sendRedirect("Proveedor/perfil");
                    break;

                //no aprovado
                case "3":
                    request.getSession().setAttribute("msg", "usuario aun no ha sido aprobado comuniquese con el administrador");
                    response.sendRedirect("Error/errorRedir");
                    break;

            }

        }

        if (tipoUser.equals("Empleado_administrativoyfinao")) {

            //valido que este activo
            String activo = usersesion.get("activo");

            switch (activo) {
                //aceptado
                case "1":
                    //request.getSession().setAttribute("msg", "");
                    response.sendRedirect("Empleado/perfil");
                    request.getSession().setAttribute("i", "1");
                    break;

                //pendiente
                case "2":
                    request.getSession().setAttribute("msg2", usersesion.get("comentario"));
                    request.getSession().setAttribute("i", "2");
                    response.sendRedirect("Empleado/perfil");
                    break;

            }

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
