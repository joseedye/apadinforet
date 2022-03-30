package Controlador;

import DTO.Usuario;
import DAO.Conexion;
import DAO.UsuarioJpaController;
import Util.Utileria;
import static Util.Utileria.getExtensionPath;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Leonardo
 */
@MultipartConfig
public class UpdatePhoto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String msgFail = "Error al cargar imagen: ";
        boolean fail = false;
        EntityManagerFactory emf = Conexion.getConexion().getBd();
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        int idUser = Integer.valueOf(user.get("idUsuario"));
        UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
        Usuario usuario = usuarioDao.findUsuario(idUser);

        String rutaDestinationProyecto = "/Files/profile-photos/";
        String rutaDestinationDisco = getServletContext().getRealPath(rutaDestinationProyecto); //Ruta donde se guardará el archivo.

        Part filePart = req.getPart("change-photo"); // Archivo input

        String fileNameSource = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Nombre Archivo con extension.
        String extension = getExtensionPath(fileNameSource);
        String fileNameDestination = "foto-perfil-userid-" + idUser + "" + extension;

        if (!fileNameSource.equals("")) {

            File uploads = new File(rutaDestinationDisco); //Objeto File con la ruta
            File file = new File(uploads, fileNameDestination); //Objeto File con la ruta y el nombre del archivo.

            try {
                InputStream fileContent = filePart.getInputStream(); //Archivo ahora es InputStream
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING); //Copia el archivo a la ruta

                //Guardar en base de datos
                String urlFoto = rutaDestinationProyecto + fileNameDestination;

                usuario.setUrlFoto(urlFoto);
                usuarioDao.edit(usuario);

                //actualizar user en request
                Map<String, String> usuarioMap = Utileria.usuarioToMap(usuario);
                req.getSession().setAttribute("user", usuarioMap);

            } catch (Exception ex) {
                msgFail += fileNameSource + " ";
                fail = true;
            }
        }

        if (fail) {
            req.getSession().setAttribute("msg", msgFail);
            res.sendRedirect("/Error/errorRedir");
        } else {
            req.getSession().setAttribute("msg", "Foto Perfil cambiada con éxito!");
            res.sendRedirect("Notification.do");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
