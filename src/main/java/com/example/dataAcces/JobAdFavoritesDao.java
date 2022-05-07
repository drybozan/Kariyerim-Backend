package com.example.dataAcces;

import com.example.entities.concretes.Candidate;
import com.example.entities.concretes.JobAd;
import com.example.entities.concretes.JobAdFavorites;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

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
            getCurrentSession().remove(favoriteId);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public JobAdFavorites findByCandidateId(int candidateId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobAdFavorites> criteriaQuery = criteriaBuilder.createQuery(JobAdFavorites.class);
        Root<JobAdFavorites> jobAdFavoritesRoot = criteriaQuery.from(JobAdFavorites.class);
        Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);

        jobAdFavoritesRoot.get("candidate").alias("candidate");

        Predicate findByCandidateIdPredicate = criteriaBuilder.equal(jobAdFavoritesRoot.get("candidate.id"), candidateRoot.get("candidateId"));
        criteriaQuery.select(jobAdFavoritesRoot).where(findByCandidateIdPredicate);
        criteriaQuery.distinct(true);

        Query<JobAdFavorites> query = currentSession.createQuery(criteriaQuery);
        JobAdFavorites jobAdFavorites = query.getSingleResult();
        return jobAdFavorites;
    }

    public JobAdFavorites getById(int jobAdFavoritesId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobAdFavorites> criteriaQuery = criteriaBuilder.createQuery(JobAdFavorites.class);
        Root<JobAdFavorites> root = criteriaQuery.from(JobAdFavorites.class);

        Predicate cvIdPredicate = criteriaBuilder.equal(root.get("id"), "jobAdFavoritesId");
        criteriaQuery.select(root).where(cvIdPredicate);

        Query<JobAdFavorites> query = currentSession.createQuery(criteriaQuery);
        JobAdFavorites jobAdFavorites = query.getSingleResult();
        return jobAdFavorites;
    }

    public boolean existsByCandidate_IdAndJobAd_Id(int candidateId, int jobAdId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobAdFavorites> criteriaQuery = criteriaBuilder.createQuery(JobAdFavorites.class);
        Root<JobAdFavorites> jobAdFavoritesRoot = criteriaQuery.from(JobAdFavorites.class);
        Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);
        Root<JobAd> jobAdRoot = criteriaQuery.from(JobAd.class);

        jobAdFavoritesRoot.get("candidate").alias("candidate");
        jobAdRoot.get("jobAd").alias("jobAd");

        Predicate findByCandidateIdPredicate = criteriaBuilder.equal(jobAdFavoritesRoot.get("candidate.id"), candidateRoot.get("candidateId"));
        Predicate findByJobAdPredicate = criteriaBuilder.equal(jobAdFavoritesRoot.get("jobAd.id"), jobAdRoot.get("jobAdId"));
        criteriaQuery.select(jobAdFavoritesRoot).where(criteriaBuilder.and(findByCandidateIdPredicate,findByJobAdPredicate));
        criteriaQuery.distinct(true);

        Query<JobAdFavorites> query = currentSession.createQuery(criteriaQuery);
        JobAdFavorites jobAdFavorites = query.getSingleResult();
        Boolean existIds = jobAdFavorites==null ? true:false;
        return existIds;
    }

    //List<JobAdFavorites> findByCandidateId(int id);
    //boolean existsByCandidate_IdAndJobAd_Id(int candidateId, int JobAdId);
}
