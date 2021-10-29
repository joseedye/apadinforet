/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.UserSistem;
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
public class UserSistemJpaController implements Serializable {

    public UserSistemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserSistem userSistem) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userSistem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUserSistem(userSistem.getIdUsis()) != null) {
                throw new PreexistingEntityException("UserSistem " + userSistem + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserSistem userSistem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userSistem = em.merge(userSistem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userSistem.getIdUsis();
                if (findUserSistem(id) == null) {
                    throw new NonexistentEntityException("The userSistem with id " + id + " no longer exists.");
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
            UserSistem userSistem;
            try {
                userSistem = em.getReference(UserSistem.class, id);
                userSistem.getIdUsis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userSistem with id " + id + " no longer exists.", enfe);
            }
            em.remove(userSistem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserSistem> findUserSistemEntities() {
        return findUserSistemEntities(true, -1, -1);
    }

    public List<UserSistem> findUserSistemEntities(int maxResults, int firstResult) {
        return findUserSistemEntities(false, maxResults, firstResult);
    }

    private List<UserSistem> findUserSistemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserSistem.class));
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

    public UserSistem findUserSistem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserSistem.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserSistemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserSistem> rt = cq.from(UserSistem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
