package com.example.dataAcces;

import com.example.entities.concretes.Technology;
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
public class TechnologyDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public boolean save(Technology technology) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(technology);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public boolean deleteById(Technology technology) {
        boolean success = true;
        try {
            getCurrentSession().remove(technology);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Technology getById(int technologyId){
        try {
            Session currentSession = getCurrentSession();
                    CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
                    CriteriaQuery<Technology> criteriaQuery = criteriaBuilder.createQuery(Technology.class);
                    Root<Technology> root = criteriaQuery.from(Technology.class);

                    Predicate technologyIdPredicate = criteriaBuilder.equal(root.get("id"), technologyId);
                    criteriaQuery.select(root).where(technologyIdPredicate);

                    Query<Technology> query = currentSession.createQuery(criteriaQuery);
                    Technology technology = query.getSingleResult();
                    return technology;
        }catch (Exception e){
            return null;
        }
    }

    public List<Technology> findByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Technology> criteriaQuery = criteriaBuilder.createQuery(Technology.class);
        Root<Technology> technologyRoot = criteriaQuery.from(Technology.class);

        Predicate findByCvIdPredicate = criteriaBuilder.equal(technologyRoot.get("cv_id"), cvId);
        criteriaQuery.select(technologyRoot).where(findByCvIdPredicate);
        criteriaQuery.distinct(true);

        Query<Technology> query = currentSession.createQuery(criteriaQuery);
        List<Technology> technology = query.getResultList();
        return technology;
    }
}
