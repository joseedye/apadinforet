/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.PersonaJpaController;
import DAO.TipoUsuarioJpaController;
import DAO.UsuarioJpaController;
import DTO.Persona;
import DTO.TipoUsuario;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
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
public class RegisterEmploye extends HttpServlet {

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
        
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            TipoUsuarioJpaController usuarioJpa = new TipoUsuarioJpaController(emf);
            
            String nombre = request.getParameter("Nom");
            String tipodoc = request.getParameter("Tipodoc");
            String documento = request.getParameter("Doc");
            String fecha = request.getParameter("Fecha");
            String apellido1 = request.getParameter("Ape1");
            String email = request.getParameter("Email");
            String telefono1 = request.getParameter("Tel1");
            String telefono2 = request.getParameter("Tel2");
            String genero = request.getParameter("Genero");
            String apellido2 = request.getParameter("Ape2");
            String direccion = request.getParameter("Dire");
            String pais = request.getParameter("pais");
            String tipo = request.getParameter("tipo");
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecNacimiento = formato.parse(fecha);
            PersonaJpaController personajpa = new PersonaJpaController(emf);
            
            Persona personaDTO = new Persona(documento, nombre, apellido1, apellido2, fecNacimiento, tipodoc, direccion, telefono1, telefono2, email);
            personaDTO.setGenero(genero);
            personaDTO.setPais(pais);
            
            personajpa.create(personaDTO);

            //crear el usuario
            UsuarioJpaController usuariojpa = new UsuarioJpaController(emf);
            Date fecCreacion = new Date();
            Usuario usuarioDto = new Usuario(email, documento, fecCreacion, "2");

            //crear el tipo usuario
            TipoUsuario tipousuario;
            
            if ("planta".equals(tipo)) {
                
                tipousuario = usuarioJpa.findTipoUsuario(5);
                
            } else {
                
                tipousuario = usuarioJpa.findTipoUsuario(6);
                
            }
            
            usuarioDto.setIdTipoUsuario(tipousuario);
            usuarioDto.setIdPersona(personaDTO);
            usuarioDto.setIdUsuario(usuariojpa.getUsuarioLast().getIdUsuario() + 1);
            try {
                
                usuariojpa.create(usuarioDto);
                
            } catch (Exception e) {
                
                personajpa.destroy(personaDTO.getNumeroDoc());
                
                String cause = e.getCause().getCause().getMessage();
                
                request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
                
                response.sendRedirect("Administrador/empleado_registrar");
                
                return;
            }
//Send Mail with credentials
//            String dominio = "https://avecs.azurewebsites.net/";
//            String titulo = "Nuevo Usuario - Avecs";
//            String cuerpo = "Bienvenido a Avecs, sus datos para "
//                    + "iniciar sesión son:\n *Usuario: " + email + "\n *Contraseña: " + sincifrar
//                    + "\n \n Visita " + dominio;
//            Utileria.enviarCorreo(email, titulo, cuerpo);
//
            request.getSession().setAttribute("msg", "Usuario registrado exitosamente!");
            response.sendRedirect("Administrador/empleado_registrar");
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
