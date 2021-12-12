package DTO;

import DTO.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-12T13:24:53")
@StaticMetamodel(DocumentoSolicitud.class)
public class DocumentoSolicitud_ { 

    public static volatile SingularAttribute<DocumentoSolicitud, Date> fechaDeSubida;
    public static volatile SingularAttribute<DocumentoSolicitud, Integer> idDocumento;
    public static volatile SingularAttribute<DocumentoSolicitud, String> ruta;
    public static volatile SingularAttribute<DocumentoSolicitud, Solicitud> idSolicitud;
    public static volatile SingularAttribute<DocumentoSolicitud, String> nombre;

}