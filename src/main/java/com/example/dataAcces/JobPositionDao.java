package com.example.dataAcces;


import com.example.entities.concretes.JobPosition;
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
public class JobPositionDao  {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(JobPosition jobPosition) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(jobPosition);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }


    public   List<JobPosition> getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobPosition> criteriaQuery = criteriaBuilder.createQuery(JobPosition.class);
        Root<JobPosition> root = criteriaQuery.from(JobPosition.class);

        criteriaQuery.select(root);

        Query<JobPosition> dbQuery = currentSession.createQuery(criteriaQuery);

        List<JobPosition> resultList = dbQuery.getResultList();
        return  resultList;
    }

    public JobPosition findByName(String name){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobPosition> criteriaQuery = criteriaBuilder.createQuery(JobPosition.class);
        Root<JobPosition> root = criteriaQuery.from(JobPosition.class);

        Predicate namePredicate = criteriaBuilder.equal(root.get("name"), "name");

        criteriaQuery.select(root).where(namePredicate);

        Query<JobPosition> query = currentSession.createQuery(criteriaQuery);
        JobPosition jobPosition = query.getSingleResult();
        return jobPosition;
    }

    public JobPosition getById(int jobPositionId){
        try {
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<JobPosition> criteriaQuery = criteriaBuilder.createQuery(JobPosition.class);
            Root<JobPosition> root = criteriaQuery.from(JobPosition.class);

            Predicate jobPositionIdPredicate = criteriaBuilder.equal(root.get("id"), jobPositionId);
            criteriaQuery.select(root).where(jobPositionIdPredicate);

            Query<JobPosition> query = currentSession.createQuery(criteriaQuery);
            JobPosition jobPosition = query.getSingleResult();
            return jobPosition;
        }catch (Exception e){
            return null;
        }

    }
}
