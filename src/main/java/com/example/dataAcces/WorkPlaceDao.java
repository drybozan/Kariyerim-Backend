package com.example.dataAcces;

import com.example.entities.concretes.WorkPlace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class WorkPlaceDao {
    @Autowired
    private SessionFactory sessionFactory ;
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    public List<WorkPlace> getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<WorkPlace> criteriaQuery = criteriaBuilder.createQuery(WorkPlace.class);
        Root<WorkPlace> root = criteriaQuery.from(WorkPlace.class);

        criteriaQuery.select(root);
        Query<WorkPlace> dbQuery = currentSession.createQuery(criteriaQuery);

        List<WorkPlace> resultList = dbQuery.getResultList();
        return  resultList;
    }

    public WorkPlace getById(int workPlaceId){
        try {
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<WorkPlace> criteriaQuery = criteriaBuilder.createQuery(WorkPlace.class);
            Root<WorkPlace> root = criteriaQuery.from(WorkPlace.class);

            Predicate workPlaceIdPredicate = criteriaBuilder.equal(root.get("id"), workPlaceId);
            criteriaQuery.select(root).where(workPlaceIdPredicate);

            Query<WorkPlace> query = currentSession.createQuery(criteriaQuery);
            WorkPlace workPlace = query.getSingleResult();
            return workPlace;
        }catch (Exception e){
            return null;
        }
    }
}
