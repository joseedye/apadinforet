/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.DocumentoPropio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo
 */
public class DocumentoPropioJpaController implements Serializable {

    public DocumentoPropioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentoPropio documentoPropio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = documentoPropio.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                documentoPropio.setIdUsuario(idUsuario);
            }
            em.persist(documentoPropio);
            if (idUsuario != null) {
                idUsuario.getDocumentoPropioList().add(documentoPropio);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentoPropio documentoPropio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoPropio persistentDocumentoPropio = em.find(DocumentoPropio.class, documentoPropio.getIdDocumento());
            Usuario idUsuarioOld = persistentDocumentoPropio.getIdUsuario();
            Usuario idUsuarioNew = documentoPropio.getIdUsuario();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                documentoPropio.setIdUsuario(idUsuarioNew);
            }
            documentoPropio = em.merge(documentoPropio);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getDocumentoPropioList().remove(documentoPropio);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getDocumentoPropioList().add(documentoPropio);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documentoPropio.getIdDocumento();
                if (findDocumentoPropio(id) == null) {
                    throw new NonexistentEntityException("The documentoPropio with id " + id + " no longer exists.");
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
            DocumentoPropio documentoPropio;
            try {
                documentoPropio = em.getReference(DocumentoPropio.class, id);
                documentoPropio.getIdDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentoPropio with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = documentoPropio.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getDocumentoPropioList().remove(documentoPropio);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(documentoPropio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentoPropio> findDocumentoPropioEntities() {
        return findDocumentoPropioEntities(true, -1, -1);
    }

    public List<DocumentoPropio> findDocumentoPropioEntities(int maxResults, int firstResult) {
        return findDocumentoPropioEntities(false, maxResults, firstResult);
    }

    private List<DocumentoPropio> findDocumentoPropioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DocumentoPropio.class));
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

    public DocumentoPropio findDocumentoPropio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentoPropio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoPropioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DocumentoPropio> rt = cq.from(DocumentoPropio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
