package com.example.dataAcces;


import com.example.entities.concretes.Experiance;
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

    public boolean deleteById(Experiance experiance) {
        boolean success = true;
        try {
            getCurrentSession().remove(experiance);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Experiance getById(int experianceId){
        try {
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<Experiance> criteriaQuery = criteriaBuilder.createQuery(Experiance.class);
            Root<Experiance> root = criteriaQuery.from(Experiance.class);

            Predicate experianceIdPredicate = criteriaBuilder.equal(root.get("id"), experianceId);
            criteriaQuery.select(root).where(experianceIdPredicate);

            Query<Experiance> query = currentSession.createQuery(criteriaQuery);
            Experiance experiance = query.getSingleResult();
            return experiance;
        }catch (Exception e){return  null;}
    }

    public List<Experiance> findByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Experiance> criteriaQuery = criteriaBuilder.createQuery(Experiance.class);
        Root<Experiance> experianceRoot = criteriaQuery.from(Experiance.class);

        Predicate findByCvIdPredicate = criteriaBuilder.equal(experianceRoot.get("cv_id"), cvId);
        criteriaQuery.select(experianceRoot).where(findByCvIdPredicate);
        criteriaQuery.distinct(true);

        Query<Experiance> query = currentSession.createQuery(criteriaQuery);
        List<Experiance> experiance = query.getResultList();
        return experiance;
    }
}
