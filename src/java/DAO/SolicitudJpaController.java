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
import DTO.EstatusSolicitud;
import DTO.Usuario;
import DTO.Documento;
import DTO.Solicitud;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo
 */
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) throws PreexistingEntityException, Exception {
        if (solicitud.getDocumentoList() == null) {
            solicitud.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstatusSolicitud estatus = solicitud.getEstatus();
            if (estatus != null) {
                estatus = em.getReference(estatus.getClass(), estatus.getIdEstatus());
                solicitud.setEstatus(estatus);
            }
            Usuario idCliente = solicitud.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdUsuario());
                solicitud.setIdCliente(idCliente);
            }
            Usuario idSolucionador = solicitud.getIdSolucionador();
            if (idSolucionador != null) {
                idSolucionador = em.getReference(idSolucionador.getClass(), idSolucionador.getIdUsuario());
                solicitud.setIdSolucionador(idSolucionador);
            }
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : solicitud.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getIdDocumento());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            solicitud.setDocumentoList(attachedDocumentoList);
            em.persist(solicitud);
            if (estatus != null) {
                estatus.getSolicitudList().add(solicitud);
                estatus = em.merge(estatus);
            }
            if (idCliente != null) {
                idCliente.getSolicitudList().add(solicitud);
                idCliente = em.merge(idCliente);
            }
            if (idSolucionador != null) {
                idSolucionador.getSolicitudList().add(solicitud);
                idSolucionador = em.merge(idSolucionador);
            }
            for (Documento documentoListDocumento : solicitud.getDocumentoList()) {
                Solicitud oldIdSolicitudOfDocumentoListDocumento = documentoListDocumento.getIdSolicitud();
                documentoListDocumento.setIdSolicitud(solicitud);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldIdSolicitudOfDocumentoListDocumento != null) {
                    oldIdSolicitudOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldIdSolicitudOfDocumentoListDocumento = em.merge(oldIdSolicitudOfDocumentoListDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitud(solicitud.getIdSolicitud()) != null) {
                throw new PreexistingEntityException("Solicitud " + solicitud + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getIdSolicitud());
            EstatusSolicitud estatusOld = persistentSolicitud.getEstatus();
            EstatusSolicitud estatusNew = solicitud.getEstatus();
            Usuario idClienteOld = persistentSolicitud.getIdCliente();
            Usuario idClienteNew = solicitud.getIdCliente();
            Usuario idSolucionadorOld = persistentSolicitud.getIdSolucionador();
            Usuario idSolucionadorNew = solicitud.getIdSolucionador();
            List<Documento> documentoListOld = persistentSolicitud.getDocumentoList();
            List<Documento> documentoListNew = solicitud.getDocumentoList();
            List<String> illegalOrphanMessages = null;
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documento " + documentoListOldDocumento + " since its idSolicitud field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estatusNew != null) {
                estatusNew = em.getReference(estatusNew.getClass(), estatusNew.getIdEstatus());
                solicitud.setEstatus(estatusNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdUsuario());
                solicitud.setIdCliente(idClienteNew);
            }
            if (idSolucionadorNew != null) {
                idSolucionadorNew = em.getReference(idSolucionadorNew.getClass(), idSolucionadorNew.getIdUsuario());
                solicitud.setIdSolucionador(idSolucionadorNew);
            }
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getIdDocumento());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            solicitud.setDocumentoList(documentoListNew);
            solicitud = em.merge(solicitud);
            if (estatusOld != null && !estatusOld.equals(estatusNew)) {
                estatusOld.getSolicitudList().remove(solicitud);
                estatusOld = em.merge(estatusOld);
            }
            if (estatusNew != null && !estatusNew.equals(estatusOld)) {
                estatusNew.getSolicitudList().add(solicitud);
                estatusNew = em.merge(estatusNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getSolicitudList().remove(solicitud);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getSolicitudList().add(solicitud);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idSolucionadorOld != null && !idSolucionadorOld.equals(idSolucionadorNew)) {
                idSolucionadorOld.getSolicitudList().remove(solicitud);
                idSolucionadorOld = em.merge(idSolucionadorOld);
            }
            if (idSolucionadorNew != null && !idSolucionadorNew.equals(idSolucionadorOld)) {
                idSolucionadorNew.getSolicitudList().add(solicitud);
                idSolucionadorNew = em.merge(idSolucionadorNew);
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Solicitud oldIdSolicitudOfDocumentoListNewDocumento = documentoListNewDocumento.getIdSolicitud();
                    documentoListNewDocumento.setIdSolicitud(solicitud);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldIdSolicitudOfDocumentoListNewDocumento != null && !oldIdSolicitudOfDocumentoListNewDocumento.equals(solicitud)) {
                        oldIdSolicitudOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldIdSolicitudOfDocumentoListNewDocumento = em.merge(oldIdSolicitudOfDocumentoListNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getIdSolicitud();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
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
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getIdSolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Documento> documentoListOrphanCheck = solicitud.getDocumentoList();
            for (Documento documentoListOrphanCheckDocumento : documentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Solicitud (" + solicitud + ") cannot be destroyed since the Documento " + documentoListOrphanCheckDocumento + " in its documentoList field has a non-nullable idSolicitud field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            EstatusSolicitud estatus = solicitud.getEstatus();
            if (estatus != null) {
                estatus.getSolicitudList().remove(solicitud);
                estatus = em.merge(estatus);
            }
            Usuario idCliente = solicitud.getIdCliente();
            if (idCliente != null) {
                idCliente.getSolicitudList().remove(solicitud);
                idCliente = em.merge(idCliente);
            }
            Usuario idSolucionador = solicitud.getIdSolucionador();
            if (idSolucionador != null) {
                idSolucionador.getSolicitudList().remove(solicitud);
                idSolucionador = em.merge(idSolucionador);
            }
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
