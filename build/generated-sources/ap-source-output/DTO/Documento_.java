package DTO;

import DTO.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-10T14:11:24")
@StaticMetamodel(Documento.class)
public class Documento_ { 

    public static volatile SingularAttribute<Documento, Integer> idUser;
    public static volatile SingularAttribute<Documento, Date> fechaDeSubida;
    public static volatile SingularAttribute<Documento, Integer> idDocumento;
    public static volatile SingularAttribute<Documento, String> ruta;
    public static volatile SingularAttribute<Documento, Solicitud> idSolicitud;
    public static volatile SingularAttribute<Documento, String> nombre;

}