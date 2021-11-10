/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.PersonaJpaController;
import DAO.UsuarioJpaController;
import DTO.Persona;
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

/**
 *
 * @author rozo
 */
public class UpdateProfile extends HttpServlet {

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

        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            PersonaJpaController personaDao = new PersonaJpaController(emf);

            String email = req.getParameter("Email");
            String telefono1 = req.getParameter("Tel1");
            String telefono2 = req.getParameter("Tel2");        
            String direccion = req.getParameter("Dire");

            int idUsuario = Integer.valueOf(user.get("idUsuario"));

            Usuario usuario = usuarioDao.findUsuario(idUsuario);
            Persona persona = personaDao.findPersona(usuario.getIdPersona().getNumeroDoc());


            persona.setEmail(email);
            persona.setTelefono1(telefono1);
            persona.setTelefono2(telefono2);
            persona.setDireccion(direccion);
            personaDao.edit(persona);

            user = Utileria.usuarioToMap(usuarioDao.findUsuario(idUsuario));
            
             TipoUsuario tipoUsuario = usuario.getIdTipoUsuario();

             req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("msg", "Perfil actualizado exitosamente!");
            
                res.sendRedirect("Notification.do");
                
              

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al actualizar usuario!");
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
