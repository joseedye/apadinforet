/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.TipoSolicitud;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rozo
 */
public class TipoSolicitudJpaController implements Serializable {

    public TipoSolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoSolicitud tipoSolicitud) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoSolicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoSolicitud tipoSolicitud) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoSolicitud = em.merge(tipoSolicitud);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoSolicitud.getIdSolicitud();
                if (findTipoSolicitud(id) == null) {
                    throw new NonexistentEntityException("The tipoSolicitud with id " + id + " no longer exists.");
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
            TipoSolicitud tipoSolicitud;
            try {
                tipoSolicitud = em.getReference(TipoSolicitud.class, id);
                tipoSolicitud.getIdSolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoSolicitud with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoSolicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoSolicitud> findTipoSolicitudEntities() {
        return findTipoSolicitudEntities(true, -1, -1);
    }

    public List<TipoSolicitud> findTipoSolicitudEntities(int maxResults, int firstResult) {
        return findTipoSolicitudEntities(false, maxResults, firstResult);
    }

    private List<TipoSolicitud> findTipoSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoSolicitud.class));
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

    public TipoSolicitud findTipoSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoSolicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoSolicitud> rt = cq.from(TipoSolicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
