package DTO;

import DTO.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-16T21:25:20")
@StaticMetamodel(EstatusSolicitud.class)
public class EstatusSolicitud_ { 

    public static volatile SingularAttribute<EstatusSolicitud, String> descripcionDetallada;
    public static volatile ListAttribute<EstatusSolicitud, Solicitud> solicitudList;
    public static volatile SingularAttribute<EstatusSolicitud, Integer> idEstatus;
    public static volatile SingularAttribute<EstatusSolicitud, String> desripcion;

}