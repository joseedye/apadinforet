/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.ExtraProveedor;
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
 * @author Leonardo
 */
public class ExtraProveedorJpaController implements Serializable {

    public ExtraProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExtraProveedor extraProveedor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(extraProveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findExtraProveedor(extraProveedor.getIdProveedor()) != null) {
                throw new PreexistingEntityException("ExtraProveedor " + extraProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExtraProveedor extraProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            extraProveedor = em.merge(extraProveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = extraProveedor.getIdProveedor();
                if (findExtraProveedor(id) == null) {
                    throw new NonexistentEntityException("The extraProveedor with id " + id + " no longer exists.");
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
            ExtraProveedor extraProveedor;
            try {
                extraProveedor = em.getReference(ExtraProveedor.class, id);
                extraProveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The extraProveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(extraProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExtraProveedor> findExtraProveedorEntities() {
        return findExtraProveedorEntities(true, -1, -1);
    }

    public List<ExtraProveedor> findExtraProveedorEntities(int maxResults, int firstResult) {
        return findExtraProveedorEntities(false, maxResults, firstResult);
    }

    private List<ExtraProveedor> findExtraProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExtraProveedor.class));
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

    public ExtraProveedor findExtraProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExtraProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getExtraProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExtraProveedor> rt = cq.from(ExtraProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
