package DAO.Plus;

import DAO.SolicitudJpaController;
import java.io.Serializable;
import DTO.Solicitud;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class SolicitudJpaControllerPlus extends SolicitudJpaController implements Serializable {

    public SolicitudJpaControllerPlus(EntityManagerFactory emf) {
        super(emf);
    }

    public Solicitud getSolicitudLast() {
        EntityManager em = getEntityManager();
        try {
            return (Solicitud) em.createNativeQuery("Select * from solicitud order by id_solicitud desc limit 1", Solicitud.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
