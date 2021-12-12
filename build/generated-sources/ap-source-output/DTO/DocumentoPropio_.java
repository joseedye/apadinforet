package DTO;

import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-12T13:24:53")
@StaticMetamodel(DocumentoPropio.class)
public class DocumentoPropio_ { 

    public static volatile SingularAttribute<DocumentoPropio, Date> fechaDeSubida;
    public static volatile SingularAttribute<DocumentoPropio, Integer> idDocumento;
    public static volatile SingularAttribute<DocumentoPropio, String> ruta;
    public static volatile SingularAttribute<DocumentoPropio, Usuario> idUsuario;
    public static volatile SingularAttribute<DocumentoPropio, String> nombre;

}