package DAO.Plus;

import DAO.UsuarioJpaController;
import java.util.List;
import DTO.Usuario;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class UsuarioJpaControllerPlus extends UsuarioJpaController implements Serializable {

    public UsuarioJpaControllerPlus(EntityManagerFactory emf) {
        super(emf);
    }
       
    public Usuario findUsuario(String user) {
        EntityManager em = getEntityManager();
        try {
            List<Usuario> listUsuarios = em.createNamedQuery("Usuario.findByUser", Usuario.class).setParameter("user", user).getResultList();

            if (listUsuarios.isEmpty()) {
                return null;
            } else {
                return listUsuarios.get(0);
            }
        } finally {
            em.close();
        }
    }
    
    public Usuario getUsuarioLast() {
        EntityManager em = getEntityManager();
        try {
            return (Usuario) em.createNativeQuery("Select * from usuario order by id_usuario desc limit 1", Usuario.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
