package DTO;

import DTO.Documento;
import DTO.EstatusSolicitud;
import DTO.SolicitudPK;
import DTO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-08T20:35:13")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, String> descripcion;
    public static volatile SingularAttribute<Solicitud, SolicitudPK> solicitudPK;
    public static volatile ListAttribute<Solicitud, Documento> documentoList;
    public static volatile SingularAttribute<Solicitud, String> tematica;
    public static volatile SingularAttribute<Solicitud, EstatusSolicitud> estatus;
    public static volatile SingularAttribute<Solicitud, Usuario> idSolucionador;
    public static volatile SingularAttribute<Solicitud, Usuario> usuario;

}