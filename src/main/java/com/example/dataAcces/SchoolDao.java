package com.example.dataAcces;


import com.example.entities.concretes.School;
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
public class SchoolDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(School school) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(school);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public boolean deleteById(School school) {
        boolean success = true;
        try {
            getCurrentSession().remove(school);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public School getById(int schoolId){
        try {
            Session currentSession = getCurrentSession();
                    CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
                    CriteriaQuery<School> criteriaQuery = criteriaBuilder.createQuery(School.class);
                    Root<School> root = criteriaQuery.from(School.class);

                    Predicate schoolIdPredicate = criteriaBuilder.equal(root.get("id"), schoolId);
                    criteriaQuery.select(root).where(schoolIdPredicate);

                    Query<School> query = currentSession.createQuery(criteriaQuery);
                    School school = query.getSingleResult();
                    return school;
        }catch (Exception e){
            return null;
        }

    }

    public List<School> findByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<School> criteriaQuery = criteriaBuilder.createQuery(School.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);
        Root<Cv> cvRoot = criteriaQuery.from(Cv.class);

        schoolRoot.get("cv").alias("cv");

        Predicate findByCvIdPredicate = criteriaBuilder.equal(schoolRoot.get("cv.id"), cvRoot.get("cvId"));
        criteriaQuery.select(schoolRoot).where(findByCvIdPredicate);
        criteriaQuery.distinct(true);

        Query<School> query = currentSession.createQuery(criteriaQuery);
        List<School> school = query.getResultList();
        return school;
    }

}
