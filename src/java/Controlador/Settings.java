/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.io.File;
import DTO.Imagen;
import DAO.ImagenJpaController;
import DTO.Textos;
import DAO.TextosJpaController;
import DTO.TipoSolicitud;
import java.util.HashMap;
import DAO.TipoSolicitudJpaController;
import DAO.Conexion;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        EntityManagerFactory emf = Conexion.getConexion().getBd();
        TipoSolicitudJpaController tipos = new TipoSolicitudJpaController(emf);
        String msgFail = "No se lograron subir todos los archivos: ";
        boolean fail = false;
        int id = Integer.parseInt(request.getParameter("id"));
        String as = request.getParameter("a");
        String idimg = request.getParameter("idimg");
        String urll = request.getParameter("urll");
        switch (id) {
            case 0: {
                Map<String, String> mapSolicitudes = new HashMap<String, String>();
                List<TipoSolicitud> tipossol = (List<TipoSolicitud>) tipos.findTipoSolicitudEntities();
                int i = 0;
                for (TipoSolicitud tipossol2 : tipossol) {
                    mapSolicitudes.put(i + "", tipossol2.getDescripcion());
                    mapSolicitudes.put(i++ + "n", tipossol2.getIdSolicitud() + "");
                }
                TextosJpaController textos = new TextosJpaController(emf);
                Map<String, String> maptextos = new HashMap<String, String>();
                List<Textos> lista = (List<Textos>) textos.findTextosEntities();
                int flag = 0;
                for (Textos lista2 : lista) {
                    maptextos.put(flag + "", lista2.getDescripcion());
                    maptextos.put(flag++ + "n", lista2.getIdTexto() + "");
                }
                ImagenJpaController urlimagenes = new ImagenJpaController(emf);
                List<Imagen> imagenes = (List<Imagen>) urlimagenes.findImagenEntities();
                i = 0;
                Map<String, String> mapimagenes = new HashMap<String, String>();
                for (Imagen img1 : imagenes) {
                    mapimagenes.put(i + "", img1.getDescipcion());
                    mapimagenes.put(i + "n", img1.getIdImagen() + "");
                    mapimagenes.put(i++ + "u", img1.getUrl() + "");
                }
                request.getSession().setAttribute("imagenes", (Object) mapimagenes);
                request.getSession().setAttribute("textos", (Object) maptextos);
                request.getSession().setAttribute("tipoSolicitud", (Object) mapSolicitudes);
                response.sendRedirect("Administrador/config");
                break;
            }
            case 1: {
                String[] nombres = {"fondo.png", "ufps.png"};
                String rutaa = this.getServletContext().getRealPath("/img"); //Ruta donde se guardará el archivo.
                Part archi = request.getPart("imagen");
                String fileNamee = nombres[0]; // Nombre Archivo con extension.
                File archivo = new File(rutaa + "/fondo.png");
                archivo.delete();
                try (InputStream is = archi.getInputStream()) {
                    File f = new File(rutaa + "/" + fileNamee);
                    Files.copy(is, f.toPath(), new CopyOption[0]);
                } catch (Exception ex) {
                    msgFail = msgFail + fileNamee + " ";
                    fail = true;
                }
                if (fail) {
                    request.getSession().setAttribute("msg", (Object) (msgFail + " no se pudo modificar."));
                    response.sendRedirect("/Error/errorRedir");
                    break;
                }
                request.getSession().setAttribute("msg", (Object) "Imagen modificada con Exito!");
                response.sendRedirect("Administrador/config");
                break;
            }
            case 2: {
                //modificar o eliminar

                String eliminar = request.getParameter("selecteliminar");
                try {
                    tipos.destroy(Integer.valueOf(Integer.parseInt(eliminar)));
                    request.getSession().setAttribute("msg", (Object) "Se ha eliminado!");
                } catch (Exception e) {
                    request.getSession().setAttribute("msg", (Object) "Error intente nuevamente!");
                }
                response.sendRedirect("Settings.do?id=0");
                break;
            }
            case 3: {
                //guardar
                String nuevo = request.getParameter("nuevo");
                TipoSolicitud nu = new TipoSolicitud();
                nu.setDescripcion(nuevo);
                tipos.create(nu);
                request.getSession().setAttribute("msg", (Object) "Tipo de solicitud guardada con Exito!");
                response.sendRedirect("Settings.do?id=0");
                break;
            }
            case 4: {
                //modifica los campos de texto
                TextosJpaController textobd = new TextosJpaController(emf);
                String idt = request.getParameter("idtexto");
                String texto = request.getParameter("texto");
                TextosJpaController textoss = new TextosJpaController(emf);
                Textos t = textoss.findTextos(Integer.valueOf(Integer.parseInt(idt)));
                t.setDescripcion(texto);
                try {
                    textoss.edit(t);
                    request.getSession().setAttribute("msg", (Object) "Texto guardado con Exito!");
                    response.sendRedirect("Settings.do?id=0");
                } catch (Exception ex2) {
                }
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
