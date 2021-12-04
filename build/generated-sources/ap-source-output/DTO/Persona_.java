package DTO;

import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-03T22:01:03")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellido2;
    public static volatile ListAttribute<Persona, Usuario> usuarioList;
    public static volatile SingularAttribute<Persona, String> apellido1;
    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile SingularAttribute<Persona, String> representanteLegal;
    public static volatile SingularAttribute<Persona, String> comentario;
    public static volatile SingularAttribute<Persona, String> nombres;
    public static volatile SingularAttribute<Persona, String> pais;
    public static volatile SingularAttribute<Persona, Date> fechaNac;
    public static volatile SingularAttribute<Persona, String> tipoCliente;
    public static volatile SingularAttribute<Persona, String> razonSocial;
    public static volatile SingularAttribute<Persona, String> numeroDoc;
    public static volatile SingularAttribute<Persona, String> genero;
    public static volatile SingularAttribute<Persona, String> tipoDoc;
    public static volatile SingularAttribute<Persona, String> telefono1;
    public static volatile SingularAttribute<Persona, String> telefono2;
    public static volatile SingularAttribute<Persona, String> email;

}