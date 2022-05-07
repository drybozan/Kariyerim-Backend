package com.example.dataAcces;


import com.example.entities.concretes.Cv;
import com.example.entities.concretes.Experiance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class ExperianceDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(Experiance experiance) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(experiance);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    public boolean deleteById(int experianceId) {
        boolean success = true;
        try {
            getCurrentSession().remove(experianceId);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Experiance getById(int experianceId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Experiance> criteriaQuery = criteriaBuilder.createQuery(Experiance.class);
        Root<Experiance> root = criteriaQuery.from(Experiance.class);

        Predicate experianceIdPredicate = criteriaBuilder.equal(root.get("id"), "experianceId");
        criteriaQuery.select(root).where(experianceIdPredicate);

        Query<Experiance> query = currentSession.createQuery(criteriaQuery);
        Experiance experiance = query.getSingleResult();
        return experiance;
    }

    public Experiance findByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Experiance> criteriaQuery = criteriaBuilder.createQuery(Experiance.class);
        Root<Cv> cvRoot = criteriaQuery.from(Cv.class);
        Root<Experiance> experianceRoot = criteriaQuery.from(Experiance.class);

        experianceRoot.get("cv").alias("cv");

        Predicate findByCvIdPredicate = criteriaBuilder.equal(experianceRoot.get("cv.id"), cvRoot.get("cvId"));
        criteriaQuery.select(experianceRoot).where(findByCvIdPredicate);
        criteriaQuery.distinct(true);

        Query<Experiance> query = currentSession.createQuery(criteriaQuery);
        Experiance experiance = query.getSingleResult();
        return experiance;
    }

}
