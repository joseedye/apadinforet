package DTO;

import DTO.EstadoNotificacion;
import DTO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T11:42:40")
@StaticMetamodel(Notificacion.class)
public class Notificacion_ { 

    public static volatile SingularAttribute<Notificacion, String> descripcion;
    public static volatile SingularAttribute<Notificacion, Integer> estado;
    public static volatile SingularAttribute<Notificacion, Integer> idNotificacion;
    public static volatile SingularAttribute<Notificacion, Usuario> idUsuario;
    public static volatile SingularAttribute<Notificacion, EstadoNotificacion> estadoNotificacion;

}