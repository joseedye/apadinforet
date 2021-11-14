/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.DocumentoSolicitud;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo
 */
public class DocumentoSolicitudJpaController implements Serializable {

    public DocumentoSolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentoSolicitud documentoSolicitud) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud idSolicitud = documentoSolicitud.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getIdSolicitud());
                documentoSolicitud.setIdSolicitud(idSolicitud);
            }
            em.persist(documentoSolicitud);
            if (idSolicitud != null) {
                idSolicitud.getDocumentoSolicitudList().add(documentoSolicitud);
                idSolicitud = em.merge(idSolicitud);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentoSolicitud documentoSolicitud) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoSolicitud persistentDocumentoSolicitud = em.find(DocumentoSolicitud.class, documentoSolicitud.getIdDocumento());
            Solicitud idSolicitudOld = persistentDocumentoSolicitud.getIdSolicitud();
            Solicitud idSolicitudNew = documentoSolicitud.getIdSolicitud();
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getIdSolicitud());
                documentoSolicitud.setIdSolicitud(idSolicitudNew);
            }
            documentoSolicitud = em.merge(documentoSolicitud);
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getDocumentoSolicitudList().remove(documentoSolicitud);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getDocumentoSolicitudList().add(documentoSolicitud);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documentoSolicitud.getIdDocumento();
                if (findDocumentoSolicitud(id) == null) {
                    throw new NonexistentEntityException("The documentoSolicitud with id " + id + " no longer exists.");
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
            DocumentoSolicitud documentoSolicitud;
            try {
                documentoSolicitud = em.getReference(DocumentoSolicitud.class, id);
                documentoSolicitud.getIdDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentoSolicitud with id " + id + " no longer exists.", enfe);
            }
            Solicitud idSolicitud = documentoSolicitud.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getDocumentoSolicitudList().remove(documentoSolicitud);
                idSolicitud = em.merge(idSolicitud);
            }
            em.remove(documentoSolicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentoSolicitud> findDocumentoSolicitudEntities() {
        return findDocumentoSolicitudEntities(true, -1, -1);
    }

    public List<DocumentoSolicitud> findDocumentoSolicitudEntities(int maxResults, int firstResult) {
        return findDocumentoSolicitudEntities(false, maxResults, firstResult);
    }

    private List<DocumentoSolicitud> findDocumentoSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DocumentoSolicitud.class));
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

    public DocumentoSolicitud findDocumentoSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentoSolicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DocumentoSolicitud> rt = cq.from(DocumentoSolicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
