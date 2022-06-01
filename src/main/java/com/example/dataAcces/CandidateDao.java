package com.example.dataAcces;

import com.example.entities.concretes.Candidate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

@Repository
public class CandidateDao  {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Candidate findByNationalNumber(String nationalNumber){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
        Root<Candidate> root = criteriaQuery.from(Candidate.class);
        Predicate nationalNumberPredicate = criteriaBuilder.equal(root.get("nationalNumber"), nationalNumber);
        criteriaQuery.select(root).where(nationalNumberPredicate);
        Query<Candidate> query = currentSession.createQuery(criteriaQuery);
        Candidate candidate = query.getSingleResult();
        return candidate;
    }

    public List<Candidate> getAll(){
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
            Root<Candidate> root = criteriaQuery.from(Candidate.class);
            criteriaQuery.select(root);
            Query<Candidate> dbQuery = currentSession.createQuery(criteriaQuery);
            List<Candidate> resultList = dbQuery.getResultList();
            return resultList;
    }

    public boolean save(Candidate candidate) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(candidate);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Candidate getById(int candidateId){
       try{
           Session currentSession = getCurrentSession();
           CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
           CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
           Root<Candidate> root = criteriaQuery.from(Candidate.class);

           Predicate candidateIdPredicate = criteriaBuilder.equal(root.get("id"), candidateId);
           criteriaQuery.select(root).where(candidateIdPredicate);

           Query<Candidate> query = currentSession.createQuery(criteriaQuery);
           Candidate candidate = query.getSingleResult();
           return candidate;
       }
       catch (Exception e ){
           return null;
       }
    }
}

