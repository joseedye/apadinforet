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
import DTO.Persona;
import DTO.TipoUsuario;
import DTO.Solicitud;
import DTO.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getSolicitudList() == null) {
            usuario.setSolicitudList(new ArrayList<Solicitud>());
        }
        if (usuario.getSolicitudList1() == null) {
            usuario.setSolicitudList1(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = usuario.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                usuario.setIdPersona(idPersona);
            }
            TipoUsuario idTipoUsuario = usuario.getIdTipoUsuario();
            if (idTipoUsuario != null) {
                idTipoUsuario = em.getReference(idTipoUsuario.getClass(), idTipoUsuario.getIdTipoUsuario());
                usuario.setIdTipoUsuario(idTipoUsuario);
            }
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : usuario.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            usuario.setSolicitudList(attachedSolicitudList);
            List<Solicitud> attachedSolicitudList1 = new ArrayList<Solicitud>();
            for (Solicitud solicitudList1SolicitudToAttach : usuario.getSolicitudList1()) {
                solicitudList1SolicitudToAttach = em.getReference(solicitudList1SolicitudToAttach.getClass(), solicitudList1SolicitudToAttach.getIdSolicitud());
                attachedSolicitudList1.add(solicitudList1SolicitudToAttach);
            }
            usuario.setSolicitudList1(attachedSolicitudList1);
            em.persist(usuario);
            if (idPersona != null) {
                idPersona.getUsuarioList().add(usuario);
                idPersona = em.merge(idPersona);
            }
            if (idTipoUsuario != null) {
                idTipoUsuario.getUsuarioList().add(usuario);
                idTipoUsuario = em.merge(idTipoUsuario);
            }
            for (Solicitud solicitudListSolicitud : usuario.getSolicitudList()) {
                Usuario oldIdClienteOfSolicitudListSolicitud = solicitudListSolicitud.getIdCliente();
                solicitudListSolicitud.setIdCliente(usuario);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdClienteOfSolicitudListSolicitud != null) {
                    oldIdClienteOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdClienteOfSolicitudListSolicitud = em.merge(oldIdClienteOfSolicitudListSolicitud);
                }
            }
            for (Solicitud solicitudList1Solicitud : usuario.getSolicitudList1()) {
                Usuario oldIdSolucionadorOfSolicitudList1Solicitud = solicitudList1Solicitud.getIdSolucionador();
                solicitudList1Solicitud.setIdSolucionador(usuario);
                solicitudList1Solicitud = em.merge(solicitudList1Solicitud);
                if (oldIdSolucionadorOfSolicitudList1Solicitud != null) {
                    oldIdSolucionadorOfSolicitudList1Solicitud.getSolicitudList1().remove(solicitudList1Solicitud);
                    oldIdSolucionadorOfSolicitudList1Solicitud = em.merge(oldIdSolucionadorOfSolicitudList1Solicitud);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Persona idPersonaOld = persistentUsuario.getIdPersona();
            Persona idPersonaNew = usuario.getIdPersona();
            TipoUsuario idTipoUsuarioOld = persistentUsuario.getIdTipoUsuario();
            TipoUsuario idTipoUsuarioNew = usuario.getIdTipoUsuario();
            List<Solicitud> solicitudListOld = persistentUsuario.getSolicitudList();
            List<Solicitud> solicitudListNew = usuario.getSolicitudList();
            List<Solicitud> solicitudList1Old = persistentUsuario.getSolicitudList1();
            List<Solicitud> solicitudList1New = usuario.getSolicitudList1();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its idCliente field is not nullable.");
                }
            }
            for (Solicitud solicitudList1OldSolicitud : solicitudList1Old) {
                if (!solicitudList1New.contains(solicitudList1OldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudList1OldSolicitud + " since its idSolucionador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                usuario.setIdPersona(idPersonaNew);
            }
            if (idTipoUsuarioNew != null) {
                idTipoUsuarioNew = em.getReference(idTipoUsuarioNew.getClass(), idTipoUsuarioNew.getIdTipoUsuario());
                usuario.setIdTipoUsuario(idTipoUsuarioNew);
            }
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            usuario.setSolicitudList(solicitudListNew);
            List<Solicitud> attachedSolicitudList1New = new ArrayList<Solicitud>();
            for (Solicitud solicitudList1NewSolicitudToAttach : solicitudList1New) {
                solicitudList1NewSolicitudToAttach = em.getReference(solicitudList1NewSolicitudToAttach.getClass(), solicitudList1NewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList1New.add(solicitudList1NewSolicitudToAttach);
            }
            solicitudList1New = attachedSolicitudList1New;
            usuario.setSolicitudList1(solicitudList1New);
            usuario = em.merge(usuario);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getUsuarioList().remove(usuario);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getUsuarioList().add(usuario);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idTipoUsuarioOld != null && !idTipoUsuarioOld.equals(idTipoUsuarioNew)) {
                idTipoUsuarioOld.getUsuarioList().remove(usuario);
                idTipoUsuarioOld = em.merge(idTipoUsuarioOld);
            }
            if (idTipoUsuarioNew != null && !idTipoUsuarioNew.equals(idTipoUsuarioOld)) {
                idTipoUsuarioNew.getUsuarioList().add(usuario);
                idTipoUsuarioNew = em.merge(idTipoUsuarioNew);
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Usuario oldIdClienteOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdCliente();
                    solicitudListNewSolicitud.setIdCliente(usuario);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdClienteOfSolicitudListNewSolicitud != null && !oldIdClienteOfSolicitudListNewSolicitud.equals(usuario)) {
                        oldIdClienteOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdClienteOfSolicitudListNewSolicitud = em.merge(oldIdClienteOfSolicitudListNewSolicitud);
                    }
                }
            }
            for (Solicitud solicitudList1NewSolicitud : solicitudList1New) {
                if (!solicitudList1Old.contains(solicitudList1NewSolicitud)) {
                    Usuario oldIdSolucionadorOfSolicitudList1NewSolicitud = solicitudList1NewSolicitud.getIdSolucionador();
                    solicitudList1NewSolicitud.setIdSolucionador(usuario);
                    solicitudList1NewSolicitud = em.merge(solicitudList1NewSolicitud);
                    if (oldIdSolucionadorOfSolicitudList1NewSolicitud != null && !oldIdSolucionadorOfSolicitudList1NewSolicitud.equals(usuario)) {
                        oldIdSolucionadorOfSolicitudList1NewSolicitud.getSolicitudList1().remove(solicitudList1NewSolicitud);
                        oldIdSolucionadorOfSolicitudList1NewSolicitud = em.merge(oldIdSolucionadorOfSolicitudList1NewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Solicitud> solicitudListOrphanCheck = usuario.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable idCliente field.");
            }
            List<Solicitud> solicitudList1OrphanCheck = usuario.getSolicitudList1();
            for (Solicitud solicitudList1OrphanCheckSolicitud : solicitudList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Solicitud " + solicitudList1OrphanCheckSolicitud + " in its solicitudList1 field has a non-nullable idSolucionador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = usuario.getIdPersona();
            if (idPersona != null) {
                idPersona.getUsuarioList().remove(usuario);
                idPersona = em.merge(idPersona);
            }
            TipoUsuario idTipoUsuario = usuario.getIdTipoUsuario();
            if (idTipoUsuario != null) {
                idTipoUsuario.getUsuarioList().remove(usuario);
                idTipoUsuario = em.merge(idTipoUsuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public Usuario findUsuario(String user) {
        EntityManager em = getEntityManager();
        try {
            List<Usuario> listUsuarios = em.createNamedQuery("Usuario.findByUser", Usuario.class).setParameter("user", user).getResultList();

            if (listUsuarios.isEmpty()) {
                return null;
            } else {
                return listUsuarios.get(0);
            }
        } finally {
            em.close();
        }
    }
    
}
