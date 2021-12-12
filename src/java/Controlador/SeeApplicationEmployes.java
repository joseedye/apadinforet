/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.Solicitud;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class SeeApplicationEmployes extends HttpServlet {

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
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(emf);            
        SolicitudJpaController solicitudJpa = new SolicitudJpaController(emf);
        
        int id = Integer.parseInt(request.getParameter("idSolQuery"));
        Usuario users = usuarioJpa.findUsuario(id);
                       
        Map<String, Object> mapSolicitudes = new HashMap<>();
        int i =0;
        List <Solicitud> solicitudes  = users.getSolicitudList();
        
        for (Iterator<Solicitud> iterator = solicitudes.iterator(); iterator.hasNext();) {
            Solicitud next = iterator.next();            
            mapSolicitudes.put(""+ i++ ,Utileria.solicitudToMap(next));
        }
        
        int j =0;
         Map<String, Object> mapEmpleados = new HashMap<>();
        List<Usuario> empleados = usuarioJpa.findUsuarioEntities();
        for (Iterator<Usuario> iterator = empleados.iterator(); iterator.hasNext();) {
            Usuario next = iterator.next();
            if(next.getIdTipoUsuario().getIdTipoUsuario()>=5){
               mapEmpleados.put(""+ j++, Utileria.usuarioToMap(next));
            }
        }
        
        Map <String , String> userbuscado = Utileria.usuarioToMap(users);
        request.getSession().setAttribute("empleados", mapEmpleados);
        request.getSession().setAttribute("usuariobuscado", userbuscado);
        request.getSession().setAttribute("solicitudes", mapSolicitudes);
        response.sendRedirect(user.get("TipoUsuario") + "/solicituddetall");
        
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
