/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DTO.DocumentoPropio;
import DTO.DocumentoSolicitud;
import DTO.ExtraProveedor;
import DTO.InfoBancaria;
import DTO.Solicitud;
import DTO.Usuario;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rozo
 */
public class Utileria {

    public static Map<String, String> usuarioToMap(Usuario user) {
        Map<String, String> map = new HashMap<>();
        map.put("idUsuario", user.getIdUsuario().toString());
        map.put("user", user.getUser());
        map.put("fecCreacion", dateToString(user.getFechaCreacion()));
        map.put("idtipou", user.getIdTipoUsuario().getIdTipoUsuario() + "");
        map.put("TipoUsuario", user.getIdTipoUsuario().getDesTipoUsuario());
        map.put("nombres", user.getIdPersona().getNombres());
        map.put("apellido1", user.getIdPersona().getApellido1());
        map.put("apellido2", user.getIdPersona().getApellido2());
        map.put("fecNacimiento", dateToString(user.getIdPersona().getFechaNac()));
        map.put("tipoDocumento", user.getIdPersona().getTipoDoc());
        map.put("numDocumento", user.getIdPersona().getNumeroDoc() + "");
        map.put("genero", user.getIdPersona().getGenero());
        map.put("direccion", user.getIdPersona().getDireccion());
        map.put("email", user.getIdPersona().getEmail());
        map.put("telefono1", user.getIdPersona().getTelefono1() + "");
        map.put("telefono2", user.getIdPersona().getTelefono2() + "");
        map.put("activo", user.getActivo() + "");
        //map.put("activo", user.getActivo() + ""); crear en la bd ya
        map.put("contra", user.getPassword());
        map.put("pais", user.getIdPersona().getPais());
        map.put("comentario", user.getIdPersona().getComentario());
        map.put("reprecentante", user.getIdPersona().getRepresentanteLegal() + "");
        map.put("razon", user.getIdPersona().getRazonSocial());
        map.put("Tipocliente", user.getIdPersona().getTipoCliente());
        return map;
    }

    private static String dateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }

    public static Map<String, String> bancaToMap(InfoBancaria banca) {
        Map<String, String> map = new HashMap<>();
        map.put("idb", banca.getIdUsuario() + "");
        map.put("banco", banca.getBanco());
        map.put("ciudad", banca.getCiudad());
        map.put("codbanco", banca.getCodBanco());
        map.put("formap", banca.getFormaPago());
        map.put("ncuenta", banca.getNumeroCuenta());
        map.put("pais", banca.getPais());
        map.put("sucursal", banca.getSucursal());
        map.put("tipoc", banca.getTipoCuenta());
        return map;
    }

    public static Map<String, String> solicitudToMap(Solicitud sol) {
        Map<String, String> map = new HashMap<>();
        map.put("ids", sol.getIdSolicitud() + "");
        map.put("idc", sol.getIdCliente().getIdUsuario() + "");
        map.put("descripcion", sol.getDescripcion());
        map.put("tematica", sol.getTematica());
        map.put("decripciondetallada", sol.getEstatus().getDescripcionDetallada());
        map.put("fecha", dateToString(sol.getFecha()));
        return map;
    }

    public static String msgExPersistence(String cause) {
        int inicio = cause.indexOf("entry") + 5;
        int fin = cause.indexOf("for");
        return cause.substring(inicio, fin);
    }

    public static Map<String, String> getPaises() {

        //Map<String, Map<String, String>> mapT = new HashMap<>();
        Map<String, String> mapI = new HashMap<>();
        try {

            URL url = new URL("https://gist.githubusercontent.com/brenes/1095110/raw/c8f208b03485ba28f97c500ab7271e8bce43b9c6/paises.csv");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String lectura = in.readLine();
            String salida[] = new String[2];
            String parte[];
            int i = 0;
            while ((lectura = in.readLine()) != null) {

                parte = lectura.split(",");

                salida[0] = parte[0].substring(1, parte[0].length() - 1);
                salida[1] = parte[5].substring(1, parte[5].length() - 1);;

                mapI.put("pais" + i, salida[0]);
                mapI.put("cod" + i++, salida[1]);

            }

            in.close();

        } catch (Exception e) {

        }

        return mapI;
    }

    public static Map<String, String> documentoToMap(DocumentoSolicitud documento) {
        Map<String, String> map = new HashMap<>();
        map.put("idDoc", documento.getIdDocumento() + "");
        map.put("nombre", documento.getNombre());
        map.put("rutaDoc", documento.getRuta());
        map.put("fecha", dateToString(documento.getFechaDeSubida()));
        map.put("idTipoDoc", documento.getIdSolicitud() + "");
        map.put("soy", "solicitud");

        return map;
    }

    public static Map<String, String> documentoToMap(DocumentoPropio documento) {
        Map<String, String> map = new HashMap<>();
        map.put("idDoc", documento.getIdDocumento() + "");
        map.put("nombre", documento.getNombre());
        map.put("rutaDoc", documento.getRuta());
        map.put("fecha", dateToString(documento.getFechaDeSubida()));
        map.put("iduser", documento.getIdUsuario() + "");
        map.put("soy", "propio");
        return map;
    }

    public static int cantidadDocumento(Usuario strp) {
        int documentos = 0;
        documentos = strp.getDocumentoPropioList().size();

        // obtengo el numero de documentos por solicitud como cliente
        for (Solicitud str : strp.getSolicitudList()) {
            documentos += str.getDocumentoSolicitudList().size();
        }

        for (Solicitud str : strp.getSolicitudList1()) {
            documentos += str.getDocumentoSolicitudList().size();
        }

        return documentos;
    }

    public static String cantidadSolicitudes(Usuario strp) {

        int solicitudes = 0;

        //por cliente o proveedor
        solicitudes = strp.getSolicitudList().size();

        //por solucionador
        solicitudes += strp.getSolicitudList1().size();

        return solicitudes + "";

    }

    public static boolean enviarCorreo(String destino, String titulo, String cuerpo) {
        try {
            String emailUsuarioEmisor = "inforetsas@gmail.com";
            String clave = "alpsdfgv12";
            ServiceEmail email = new ServiceEmail(emailUsuarioEmisor, clave);
            email.enviarEmail(destino, titulo, cuerpo);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Map<String, String> extraToMap(ExtraProveedor extraDTO) {
        Map<String, String> map = new HashMap<>();
        map.put("iduserextra", extraDTO.getIdProveedor() + "");

        map.put("ica", extraDTO.getIca() == null ? "" : extraDTO.getIca());

        map.put("reg", extraDTO.getResolucionGranContri() == null ? "" : extraDTO.getResolucionGranContri());
        map.put("rer", extraDTO.getResolucionRetenedor() == null ? "" : extraDTO.getResolucionRetenedor());
        map.put("fechag", dateToString(extraDTO.getFechaGranContr()));
        map.put("fechar", dateToString(extraDTO.getFechaRetenedor()));
        return map;

    }

    public static String cantidadSolicitudesC(Usuario cliente, Usuario empleado) {
        int solicitudes = 0;

         for (Solicitud strp : empleado.getSolicitudList1()) {      
             if(strp.getIdCliente().getIdUsuario()==cliente.getIdUsuario()){
             solicitudes++;}
         }
       
        return solicitudes + "";
    }

}
