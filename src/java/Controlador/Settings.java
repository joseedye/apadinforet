/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.DocumentoPropioJpaController;
import DAO.TextosJpaController;
import DAO.TipoSolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.DocumentoPropio;
import DTO.Textos;
import DTO.TipoSolicitud;
import DTO.Usuario;
import Util.Utileria;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class Settings extends HttpServlet {

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
        TipoSolicitudJpaController tipos = new TipoSolicitudJpaController(emf);
        String msgFail = "No se lograron subir todos los archivos: ";
        boolean fail = false;
        int id = Integer.parseInt(request.getParameter("id"));
        String as = request.getParameter("a");

        switch (id) {
            case 0:
                Map<String, String> mapSolicitudes = new HashMap<>();

                List<TipoSolicitud> tipossol = tipos.findTipoSolicitudEntities();
                int i = 0;
                for (TipoSolicitud tipossol1 : tipossol) {
                    mapSolicitudes.put(i + "", tipossol1.getDescripcion());
                    mapSolicitudes.put(i++ + "n", tipossol1.getIdSolicitud() + "");
                }

                TextosJpaController textos = new TextosJpaController(emf);
                Map<String, String> maptextos = new HashMap<>();
                List<Textos> lista = textos.findTextosEntities();
                int flag = 0;
                for (Textos lista1 : lista) {
                    maptextos.put(flag + "", lista1.getDescripcion());
                    maptextos.put(flag++ + "n", lista1.getIdTexto()+"");                   
                    
                }
                request.getSession().setAttribute("textos", maptextos);
                request.getSession().setAttribute("tipoSolicitud", mapSolicitudes);
                response.sendRedirect("Administrador/config");
                break;

            case 1:
                String nombres[] = {"fondo.png", "ufps.png"};
                String rutaa = getServletContext().getRealPath("/img"); //Ruta donde se guardará el archivo.
                Part archi = request.getPart("imagen");
                String fileNamee = nombres[0]; // Nombre Archivo con extension.

                File archivo = new File(rutaa + "/fondo.png");
                archivo.delete();

                try (InputStream is = archi.getInputStream()) {
                    File f = new File(rutaa + "/" + fileNamee);
                    Files.copy(is, f.toPath());

                } catch (Exception ex) {
                    msgFail += fileNamee + " ";
                    fail = true;
                }

                if (fail) {
                    request.getSession().setAttribute("msg", msgFail + " no se pudo modificar.");
                    response.sendRedirect("/Error/errorRedir");
                } else {
                    request.getSession().setAttribute("msg", "Imagen modificada con Exito!");
                    response.sendRedirect("Administrador/config");
                }

                break;

            case 2:
                //modificar o eliminar

                String eliminar = request.getParameter("selecteliminar");
                try {
                    tipos.destroy(Integer.parseInt(eliminar));
                    request.getSession().setAttribute("msg", "Se ha eliminado!");
                } catch (Exception e) {
                    request.getSession().setAttribute("msg", "Error intente nuevamente!");
                }
                response.sendRedirect("Settings.do?id=0");
                break;
            case 3:
                //guardar
                String nuevo = request.getParameter("nuevo");
                TipoSolicitud nu = new TipoSolicitud();
                nu.setDescripcion(nuevo);
                tipos.create(nu);
                request.getSession().setAttribute("msg", "Tipo de solicitud guardada con Exito!");
                response.sendRedirect("Settings.do?id=0");
                break;

            case 4:
                //modifica los campos de texto
                TextosJpaController textobd = new TextosJpaController(emf);
                String idt = request.getParameter("idtexto");
                String texto = request.getParameter("texto");
                TextosJpaController textoss = new TextosJpaController(emf);
                Textos t = textoss.findTextos(Integer.parseInt(idt));
                t.setDescripcion(texto);
                try{
                textoss.edit(t);
                request.getSession().setAttribute("msg", "Texto guardado con Exito!");
                response.sendRedirect("Settings.do?id=0");
                }catch(Exception e){
                    
                }
                break;

            default:
                break;

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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UploadFile.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
