/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.InfoBancaria;
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
public class InfoBancariaJpaController implements Serializable {

    public InfoBancariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoBancaria infoBancaria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoBancaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInfoBancaria(infoBancaria.getIdUsuario()) != null) {
                throw new PreexistingEntityException("InfoBancaria " + infoBancaria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoBancaria infoBancaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoBancaria = em.merge(infoBancaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoBancaria.getIdUsuario();
                if (findInfoBancaria(id) == null) {
                    throw new NonexistentEntityException("The infoBancaria with id " + id + " no longer exists.");
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
            InfoBancaria infoBancaria;
            try {
                infoBancaria = em.getReference(InfoBancaria.class, id);
                infoBancaria.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoBancaria with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoBancaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoBancaria> findInfoBancariaEntities() {
        return findInfoBancariaEntities(true, -1, -1);
    }

    public List<InfoBancaria> findInfoBancariaEntities(int maxResults, int firstResult) {
        return findInfoBancariaEntities(false, maxResults, firstResult);
    }

    private List<InfoBancaria> findInfoBancariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoBancaria.class));
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

    public InfoBancaria findInfoBancaria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoBancaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoBancariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoBancaria> rt = cq.from(InfoBancaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
