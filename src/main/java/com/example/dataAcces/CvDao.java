package com.example.dataAcces;

import com.example.entities.concretes.Candidate;
import com.example.entities.concretes.Cv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class CvDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(Cv cv) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(cv);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Cv getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Cv> criteriaQuery = criteriaBuilder.createQuery(Cv.class);
        Root<Cv> root = criteriaQuery.from(Cv.class);

        criteriaQuery.select(root);

        Query<Cv> dbQuery = currentSession.createQuery(criteriaQuery);

        List<Cv> resultList = dbQuery.getResultList();
        return (Cv) resultList;
    }

    public Cv getByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Cv> criteriaQuery = criteriaBuilder.createQuery(Cv.class);
        Root<Cv> root = criteriaQuery.from(Cv.class);

        Predicate cvIdPredicate = criteriaBuilder.equal(root.get("id"), "cvId");
        criteriaQuery.select(root).where(cvIdPredicate);

        Query<Cv> query = currentSession.createQuery(criteriaQuery);
        Cv cv = query.getSingleResult();
        return cv;
    }

    public Cv findByCandidateId(int candidateId){
             Session currentSession = getCurrentSession();
             CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
             CriteriaQuery<Cv> criteriaQuery = criteriaBuilder.createQuery(Cv.class);
             Root<Cv> cvRoot = criteriaQuery.from(Cv.class);
             Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);

             cvRoot.get("candidate").alias("candidate");

             Predicate findByCandidateIdPredicate = criteriaBuilder.equal(cvRoot.get("candidate.id"), candidateRoot.get("candidateId"));
             criteriaQuery.select(cvRoot).where(findByCandidateIdPredicate);
             criteriaQuery.distinct(true);

             Query<Cv> query = currentSession.createQuery(criteriaQuery);
             Cv cv = query.getSingleResult();
             return cv;
         }
}
