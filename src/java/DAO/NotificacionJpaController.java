/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.EstadoNotificacion;
import DTO.Notificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo
 */
public class NotificacionJpaController implements Serializable {

    public NotificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notificacion notificacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        EstadoNotificacion estadoNotificacionOrphanCheck = notificacion.getEstadoNotificacion();
        if (estadoNotificacionOrphanCheck != null) {
            Notificacion oldNotificacionOfEstadoNotificacion = estadoNotificacionOrphanCheck.getNotificacion();
            if (oldNotificacionOfEstadoNotificacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The EstadoNotificacion " + estadoNotificacionOrphanCheck + " already has an item of type Notificacion whose estadoNotificacion column cannot be null. Please make another selection for the estadoNotificacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoNotificacion estadoNotificacion = notificacion.getEstadoNotificacion();
            if (estadoNotificacion != null) {
                estadoNotificacion = em.getReference(estadoNotificacion.getClass(), estadoNotificacion.getIdNotificacion());
                notificacion.setEstadoNotificacion(estadoNotificacion);
            }
            em.persist(notificacion);
            if (estadoNotificacion != null) {
                estadoNotificacion.setNotificacion(notificacion);
                estadoNotificacion = em.merge(estadoNotificacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNotificacion(notificacion.getIdNotificacion()) != null) {
                throw new PreexistingEntityException("Notificacion " + notificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notificacion notificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificacion persistentNotificacion = em.find(Notificacion.class, notificacion.getIdNotificacion());
            EstadoNotificacion estadoNotificacionOld = persistentNotificacion.getEstadoNotificacion();
            EstadoNotificacion estadoNotificacionNew = notificacion.getEstadoNotificacion();
            List<String> illegalOrphanMessages = null;
            if (estadoNotificacionNew != null && !estadoNotificacionNew.equals(estadoNotificacionOld)) {
                Notificacion oldNotificacionOfEstadoNotificacion = estadoNotificacionNew.getNotificacion();
                if (oldNotificacionOfEstadoNotificacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The EstadoNotificacion " + estadoNotificacionNew + " already has an item of type Notificacion whose estadoNotificacion column cannot be null. Please make another selection for the estadoNotificacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNotificacionNew != null) {
                estadoNotificacionNew = em.getReference(estadoNotificacionNew.getClass(), estadoNotificacionNew.getIdNotificacion());
                notificacion.setEstadoNotificacion(estadoNotificacionNew);
            }
            notificacion = em.merge(notificacion);
            if (estadoNotificacionOld != null && !estadoNotificacionOld.equals(estadoNotificacionNew)) {
                estadoNotificacionOld.setNotificacion(null);
                estadoNotificacionOld = em.merge(estadoNotificacionOld);
            }
            if (estadoNotificacionNew != null && !estadoNotificacionNew.equals(estadoNotificacionOld)) {
                estadoNotificacionNew.setNotificacion(notificacion);
                estadoNotificacionNew = em.merge(estadoNotificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notificacion.getIdNotificacion();
                if (findNotificacion(id) == null) {
                    throw new NonexistentEntityException("The notificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificacion notificacion;
            try {
                notificacion = em.getReference(Notificacion.class, id);
                notificacion.getIdNotificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacion with id " + id + " no longer exists.", enfe);
            }
            EstadoNotificacion estadoNotificacion = notificacion.getEstadoNotificacion();
            if (estadoNotificacion != null) {
                estadoNotificacion.setNotificacion(null);
                estadoNotificacion = em.merge(estadoNotificacion);
            }
            em.remove(notificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notificacion> findNotificacionEntities() {
        return findNotificacionEntities(true, -1, -1);
    }

    public List<Notificacion> findNotificacionEntities(int maxResults, int firstResult) {
        return findNotificacionEntities(false, maxResults, firstResult);
    }

    private List<Notificacion> findNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notificacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Notificacion findNotificacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notificacion> rt = cq.from(Notificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
