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
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pinturasbd.Controllers.exceptions.NonexistentEntityException;
import pinturasbd.models.Potes;

/**
 *
 * @author Angel
 */
public class PotesJpaController implements Serializable {

    public PotesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PotesJpaController() {
        this.emf= Persistence.createEntityManagerFactory("PinturasBDPU");
        
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Potes potes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(potes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Potes potes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            potes = em.merge(potes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = potes.getPotId();
                if (findPotes(id) == null) {
                    throw new NonexistentEntityException("The potes with id " + id + " no longer exists.");
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
            Potes potes;
            try {
                potes = em.getReference(Potes.class, id);
                potes.getPotId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The potes with id " + id + " no longer exists.", enfe);
            }
            em.remove(potes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Potes> findPotesEntities() {
        return findPotesEntities(true, -1, -1);
    }

    public List<Potes> findPotesEntities(int maxResults, int firstResult) {
        return findPotesEntities(false, maxResults, firstResult);
    }

    private List<Potes> findPotesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Potes.class));
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

    public Potes findPotes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Potes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPotesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Potes> rt = cq.from(Potes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
