package Controlador;

import DAO.Conexion;
import DAO.UsuarioJpaController;
import DTO.TipoUsuario;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);

            Usuario usuario = new Usuario();
            //obtengo los daos de el login
            String user = req.getParameter("user");
            String password = req.getParameter("pass");

            //busco que el usuario exista
            usuario = usuarioDao.findUsuario(user);
            //comparo contraseña
            if (password.equals(usuario.getPassword())) {

                //me traigo todods los atriutos de ese usuario
                Map<String, String> usuarioMap = Utileria.usuarioToMap(usuario);
                //los envio por la sesion
                req.getSession().setAttribute("user", usuarioMap);

                res.sendRedirect("Notification.do");
                

            }else{
            req.getSession().setAttribute("msg", "Error, Usuario o contraseña incorrectos!");
            res.sendRedirect("/index.jsp");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, intentar de nuevo!");
            res.sendRedirect("/index.jsp");
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
