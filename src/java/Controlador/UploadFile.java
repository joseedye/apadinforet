/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.DocumentoJpaController;
import DAO.UsuarioJpaController;
import DTO.Documento;
import DTO.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author rozo
 */
@MultipartConfig
public class UploadFile extends HttpServlet {

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
            throws ServletException, IOException, Exception {

        String msgFail = "No se subieron estos archivos: ";
        boolean fail = false;

        EntityManagerFactory emf = Conexion.getConexion().getBd();
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        int idUser = Integer.valueOf(user.get("idUsuario"));

        String ruta = getServletContext().getRealPath("/Files"); //Ruta donde se guardará el archivo.
        String publico = req.getParameter("publico"); //public file
        String desc = req.getParameter("desc"); //Description file
        String nomb = req.getParameter("nombre");
        Part arch = req.getPart("archivo");
        String fileName = Paths.get(arch.getSubmittedFileName()).getFileName().toString(); // Nombre Archivo con extension.
       
        try (InputStream is = arch.getInputStream()) {
            File f = new File(ruta + "/" + fileName);
            Files.copy(is, f.toPath());

                //Guardar en base de datos
                String rutaDoc = "Files/" + fileName;
                DocumentoJpaController documentoDao = new DocumentoJpaController(emf);
                UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
                Usuario usuario = usuarioDao.findUsuario(idUser);

                Documento d = new Documento();
                d.setNombre(desc);
                d.setRuta(rutaDoc);
                d.setFechaDeSubida(new Date());
                d.setIdUser(usuario.getIdUsuario());
                documentoDao.create(d);

        } catch (Exception ex) {
                msgFail += fileName + " ";
                fail = true;
            }
        
        

        if (fail) {
            req.getSession().setAttribute("msg", msgFail + " .Es posible que ya existan.");
            res.sendRedirect("/Error/errorRedir");
        } else {
            req.getSession().setAttribute("msg", "Archivos Subidos con Exito!");
            res.sendRedirect("QueryDocuments.do");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        String a = "";

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
