/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.PersonaJpaController;
import DAO.Plus.UsuarioJpaControllerPlus;
import DAO.TipoUsuarioJpaController;
import DAO.UsuarioJpaController;
import DTO.Persona;
import DTO.TipoUsuario;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rozo
 */
public class RegisterClientProveedor extends HttpServlet {

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

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            TipoUsuarioJpaController tipousuarioJpa = new TipoUsuarioJpaController(emf);
            PersonaJpaController personaJpa = new PersonaJpaController(emf);
            UsuarioJpaController usuarioJpa = new UsuarioJpaController(emf);
            UsuarioJpaControllerPlus usuarioJpaPlus = new UsuarioJpaControllerPlus(emf);

            String Business = request.getParameter("Business");
            String type = request.getParameter("type");
            String Nit = request.getParameter("Nit");
            String Country = request.getParameter("Country");
            String address = request.getParameter("address");
            String Code = request.getParameter("Code");
            String email = request.getParameter("email");
            String number = request.getParameter("number");
            String nombre = request.getParameter("nombre");
            String apellido1 = request.getParameter("apellido1");
            String apellido2 = request.getParameter("apellido2");
            String nacimiento = request.getParameter("nacimiento");
            String tipous = request.getParameter("tipous");
            String message = request.getParameter("message");       
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecNacimiento = formato.parse(nacimiento);

            Persona personaDTO = new Persona(Nit, nombre, apellido1, apellido2, fecNacimiento, "NIT", address, number, email);
            personaDTO.setPais(Country);
            personaDTO.setRazonSocial(Business);
            personaDTO.setTipoCliente(type);
            personaDTO.setComentario(message);
            personaDTO.setRepresentanteLegal(nombre+" "+ apellido1 +" "+ apellido2);
            //falta crearla en la bd
           //personaDTO.setCodigopostal(Code);
            
           
            try {

                personaJpa.create(personaDTO);

            } catch (Exception e) {

                personaJpa.destroy(personaDTO.getNumeroDoc());
                String cause = e.getCause().getCause().getMessage();
                request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
                response.sendRedirect("./login.jsp");
                return;
            }

            //crear el usuario            
            Date fecCreacion = new Date();
            String urlFoto = ("customer".equals(tipous)) ? "/img/perfil-cliente.jpg" : "/img/perfil-proveedor.png";
            Usuario usuarioDto = new Usuario(null, email, Nit, fecCreacion, "1", urlFoto);
            usuarioDto.setPassword(Nit);
            //crear el tipo usuario
            TipoUsuario tipousuario;
 
            tipousuario = ("customer".equals(tipous)) ? tipousuarioJpa.findTipoUsuario(3) : tipousuarioJpa.findTipoUsuario(4);

            usuarioDto.setIdTipoUsuario(tipousuario);
            usuarioDto.setIdPersona(personaDTO);
            usuarioDto.setIdUsuario(usuarioJpaPlus.getUsuarioLast().getIdUsuario() + 1);
            
            try {

                usuarioJpa.create(usuarioDto);

            } catch (Exception e) {

                personaJpa.destroy(personaDTO.getNumeroDoc());
                String cause = e.getCause().getCause().getMessage();
                request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
                response.sendRedirect("./login.jsp");
                return;
            }

//Send Mail with credentials
//            String dominio = "https://inforet.com/";
//            String titulo = "Nuevo Usuario - inforet";
//            String cuerpo = "Bienvenido a inforet, sus datos para "
//                    + "iniciar sesión son:\n *Usuario: " + email + "\n *Contraseña: " + Nit
//                    + "\n \n Visita " + dominio;
//            Utileria.enviarCorreo(email, titulo, cuerpo);

            request.getSession().setAttribute("msg", "Usuario registrado exitosamente!");
            response.sendRedirect("/login.jsp");
        } catch (Exception e) {
            String cause = e.getCause().getCause().getMessage();
            request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
            response.sendRedirect("/Error/errorRedir");
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
