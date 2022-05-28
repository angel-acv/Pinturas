/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pinturasbd.Controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pinturasbd.Controllers.exceptions.NonexistentEntityException;
import pinturasbd.models.EntregasPinturas;

/**
 *
 * @author Angel
 */
public class EntregasPinturasJpaController implements Serializable {

    public EntregasPinturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntregasPinturas entregasPinturas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entregasPinturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntregasPinturas entregasPinturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entregasPinturas = em.merge(entregasPinturas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entregasPinturas.getEntrId();
                if (findEntregasPinturas(id) == null) {
                    throw new NonexistentEntityException("The entregasPinturas with id " + id + " no longer exists.");
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
            EntregasPinturas entregasPinturas;
            try {
                entregasPinturas = em.getReference(EntregasPinturas.class, id);
                entregasPinturas.getEntrId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregasPinturas with id " + id + " no longer exists.", enfe);
            }
            em.remove(entregasPinturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntregasPinturas> findEntregasPinturasEntities() {
        return findEntregasPinturasEntities(true, -1, -1);
    }

    public List<EntregasPinturas> findEntregasPinturasEntities(int maxResults, int firstResult) {
        return findEntregasPinturasEntities(false, maxResults, firstResult);
    }

    private List<EntregasPinturas> findEntregasPinturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntregasPinturas.class));
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

    public EntregasPinturas findEntregasPinturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntregasPinturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregasPinturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntregasPinturas> rt = cq.from(EntregasPinturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
