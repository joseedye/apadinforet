/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.EstadoNotificacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Notificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class EstadoNotificacionJpaController implements Serializable {

    public EstadoNotificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoNotificacion estadoNotificacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificacion notificacion = estadoNotificacion.getNotificacion();
            if (notificacion != null) {
                notificacion = em.getReference(notificacion.getClass(), notificacion.getIdNotificacion());
                estadoNotificacion.setNotificacion(notificacion);
            }
            em.persist(estadoNotificacion);
            if (notificacion != null) {
                EstadoNotificacion oldEstadoNotificacionOfNotificacion = notificacion.getEstadoNotificacion();
                if (oldEstadoNotificacionOfNotificacion != null) {
                    oldEstadoNotificacionOfNotificacion.setNotificacion(null);
                    oldEstadoNotificacionOfNotificacion = em.merge(oldEstadoNotificacionOfNotificacion);
                }
                notificacion.setEstadoNotificacion(estadoNotificacion);
                notificacion = em.merge(notificacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoNotificacion(estadoNotificacion.getIdNotificacion()) != null) {
                throw new PreexistingEntityException("EstadoNotificacion " + estadoNotificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoNotificacion estadoNotificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoNotificacion persistentEstadoNotificacion = em.find(EstadoNotificacion.class, estadoNotificacion.getIdNotificacion());
            Notificacion notificacionOld = persistentEstadoNotificacion.getNotificacion();
            Notificacion notificacionNew = estadoNotificacion.getNotificacion();
            List<String> illegalOrphanMessages = null;
            if (notificacionOld != null && !notificacionOld.equals(notificacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Notificacion " + notificacionOld + " since its estadoNotificacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (notificacionNew != null) {
                notificacionNew = em.getReference(notificacionNew.getClass(), notificacionNew.getIdNotificacion());
                estadoNotificacion.setNotificacion(notificacionNew);
            }
            estadoNotificacion = em.merge(estadoNotificacion);
            if (notificacionNew != null && !notificacionNew.equals(notificacionOld)) {
                EstadoNotificacion oldEstadoNotificacionOfNotificacion = notificacionNew.getEstadoNotificacion();
                if (oldEstadoNotificacionOfNotificacion != null) {
                    oldEstadoNotificacionOfNotificacion.setNotificacion(null);
                    oldEstadoNotificacionOfNotificacion = em.merge(oldEstadoNotificacionOfNotificacion);
                }
                notificacionNew.setEstadoNotificacion(estadoNotificacion);
                notificacionNew = em.merge(notificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoNotificacion.getIdNotificacion();
                if (findEstadoNotificacion(id) == null) {
                    throw new NonexistentEntityException("The estadoNotificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoNotificacion estadoNotificacion;
            try {
                estadoNotificacion = em.getReference(EstadoNotificacion.class, id);
                estadoNotificacion.getIdNotificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoNotificacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Notificacion notificacionOrphanCheck = estadoNotificacion.getNotificacion();
            if (notificacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoNotificacion (" + estadoNotificacion + ") cannot be destroyed since the Notificacion " + notificacionOrphanCheck + " in its notificacion field has a non-nullable estadoNotificacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoNotificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoNotificacion> findEstadoNotificacionEntities() {
        return findEstadoNotificacionEntities(true, -1, -1);
    }

    public List<EstadoNotificacion> findEstadoNotificacionEntities(int maxResults, int firstResult) {
        return findEstadoNotificacionEntities(false, maxResults, firstResult);
    }

    private List<EstadoNotificacion> findEstadoNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoNotificacion.class));
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

    public EstadoNotificacion findEstadoNotificacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoNotificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoNotificacion> rt = cq.from(EstadoNotificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
