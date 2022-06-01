package com.example.dataAcces;

import com.example.entities.concretes.JobAdFavorites;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class JobAdFavoritesDao{
    @Autowired
    private SessionFactory sessionFactory ;
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public boolean save(JobAdFavorites jobAdFavorites) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(jobAdFavorites);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public boolean deleteById(int favoriteId) {
        boolean success = true;
        try {
            getCurrentSession().remove(getById(favoriteId));

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public List<JobAdFavorites> findByCandidateId(int candidateId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobAdFavorites> criteriaQuery = criteriaBuilder.createQuery(JobAdFavorites.class);
        Root<JobAdFavorites> jobAdFavoritesRoot = criteriaQuery.from(JobAdFavorites.class);

        Predicate findByCandidateIdPredicate = criteriaBuilder.equal(jobAdFavoritesRoot.get("candidate").get("id"),candidateId);
        criteriaQuery.select(jobAdFavoritesRoot).where(findByCandidateIdPredicate);
        criteriaQuery.distinct(true);

        Query<JobAdFavorites> query = currentSession.createQuery(criteriaQuery);
        List<JobAdFavorites> jobAdFavorites = query.getResultList();
        return jobAdFavorites;
    }

    public JobAdFavorites getById(int jobAdFavoritesId){
        try {
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<JobAdFavorites> criteriaQuery = criteriaBuilder.createQuery(JobAdFavorites.class);
            Root<JobAdFavorites> root = criteriaQuery.from(JobAdFavorites.class);

            Predicate cvIdPredicate = criteriaBuilder.equal(root.get("id"), jobAdFavoritesId);
            criteriaQuery.select(root).where(cvIdPredicate);

            Query<JobAdFavorites> query = currentSession.createQuery(criteriaQuery);
            JobAdFavorites jobAdFavorites = query.getSingleResult();
            return jobAdFavorites;
        }catch (Exception e){
            return null;
        }
    }

    public boolean existsByCandidate_IdAndJobAd_Id(int candidateId, int jobAdId){
        try{
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<JobAdFavorites> criteriaQuery = criteriaBuilder.createQuery(JobAdFavorites.class);
            Root<JobAdFavorites> jobAdFavoritesRoot = criteriaQuery.from(JobAdFavorites.class);

            Predicate findByCandidateIdPredicate = criteriaBuilder.equal(jobAdFavoritesRoot.get("candidate").get("id"), candidateId);
            Predicate findByJobAdPredicate = criteriaBuilder.equal(jobAdFavoritesRoot.get("jobAd").get("id"), jobAdId);
            criteriaQuery.select(jobAdFavoritesRoot).where(criteriaBuilder.and(findByCandidateIdPredicate,findByJobAdPredicate));
            criteriaQuery.distinct(true);

            Query<JobAdFavorites> query = currentSession.createQuery(criteriaQuery);
            JobAdFavorites jobAdFavorites = query.getSingleResult();
            return true;
        }
        catch (Exception ex){return false;}
    }
}
