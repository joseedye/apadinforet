/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.PersonaJpaController;
import DAO.TipoUsuarioJpaController;
import DTO.Persona;
import DTO.TipoUsuario;
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
public class RegisterAdmin extends HttpServlet {

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

            TipoUsuario tipousuario = new TipoUsuario(1, "administrador");

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

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecNacimiento = formato.parse(fecha);
            PersonaJpaController personajpa = new PersonaJpaController(emf);

            Persona personaDTO = new Persona(documento,nombre, apellido1, apellido2, fecNacimiento, tipodoc, genero, direccion,telefono1 ,telefono2, email);
            personajpa.create(personaDTO);

            //crear el usuario
            
            
            
            
            
            
            
            
            
            
            
            
            
//            //Creacion usuario si es tipo docente
//            DocenteJpaController docentejpa = new DocenteJpaController(emf);
//            Docente docente = new Docente();
////            if Integer idPersona, String nombres, String apellido1, String apellido2, Date fechaNac, String tipoDoc, int numeroDoc, boolean genero, String direccion, int telefono1,  String email) {
//            (tipo == 2) {
//                int codigo = Integer.valueOf(request.getParameter("codigo"));
//                docente.setCodigoDocente(codigo);
//                docente.setIdPersona(personaDTO);
//
//                try {
//                    docentejpa.create(docente);
//                } catch (Exception e) {
//                    int idLastPersona = personajpa.getPersonaLast().getIdPersona();
//                    personajpa.destroy(idLastPersona);
//
//                    String cause = e.getCause().getCause().getMessage();
//                    request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
//                    response.sendRedirect("Administrador/usuario_registrar");
//                    return;
//                }
//            }
//
//            String password = Utileria.randomString();
//            String sincifrar = password;
//            password = DigestUtils.sha1Hex(password);
//            Date fecCreacion = new Date();
//
//            UsuarioJpaController usuariojpa = new UsuarioJpaController(emf);
//            Usuario usuarioDTO = new Usuario(email, password, fecCreacion, true);
//            usuarioDTO.setIdPersona(personajpa.findPersona(documento));
//            usuarioDTO.setIdPersona(personaDTO);
//            usuarioDTO.setIdTipoUsuario(tipoDTO);
//
//            try {
//                usuariojpa.create(usuarioDTO);
//            } catch (Exception e) {
//                personaDTO = personajpa.getPersonaLast();
//                personajpa.destroy(personaDTO.getIdPersona());
//                docente = docentejpa.findDocenteByPersona(personaDTO.getIdPersona());
//                docentejpa.destroy(docente.getIdDocente());
//
//                String cause = e.getCause().getCause().getMessage();
//                request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
//                response.sendRedirect("Administrador/usuario_registrar");
//                return;
//            }
//
//            //Send Mail with credentials
//            String dominio = "https://avecs.azurewebsites.net/";
//            String titulo = "Nuevo Usuario - Avecs";
//            String cuerpo = "Bienvenido a Avecs, sus datos para "
//                    + "iniciar sesión son:\n *Usuario: " + email + "\n *Contraseña: " + sincifrar
//                    + "\n \n Visita " + dominio;
//            Utileria.enviarCorreo(email, titulo, cuerpo);
//
//            request.getSession().setAttribute("msg", "Usuario registrado exitosamente!");
//            response.sendRedirect("Administrador/usuario_registrar");

        } catch (Exception e) {
           // String cause = e.getCause().getCause().getMessage();
//            request.getSession().setAttribute("msg", "Error, el dato: " + Utileria.msgExPersistence(cause) + " ya existe!");
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
