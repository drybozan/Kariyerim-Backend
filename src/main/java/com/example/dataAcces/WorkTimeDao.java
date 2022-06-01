package com.example.dataAcces;

import com.example.entities.concretes.WorkTime;
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
public class WorkTimeDao {
    @Autowired
    private SessionFactory sessionFactory ;
    private Session getCurrentSession(){return sessionFactory.getCurrentSession();}

    public List<WorkTime> getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<WorkTime> criteriaQuery = criteriaBuilder.createQuery(WorkTime.class);
        Root<WorkTime> root = criteriaQuery.from(WorkTime.class);

        criteriaQuery.select(root);
        Query<WorkTime> dbQuery = currentSession.createQuery(criteriaQuery);

        List<WorkTime> resultList = dbQuery.getResultList();
        return resultList;
    }

    public WorkTime getById(int workTimeId){
        try {
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<WorkTime> criteriaQuery = criteriaBuilder.createQuery(WorkTime.class);
            Root<WorkTime> root = criteriaQuery.from(WorkTime.class);

            Predicate worktimeIdPredicate = criteriaBuilder.equal(root.get("id"), workTimeId);
            criteriaQuery.select(root).where(worktimeIdPredicate);

            Query<WorkTime> query = currentSession.createQuery(criteriaQuery);
            WorkTime workTime = query.getSingleResult();
            return workTime;
        }catch (Exception e){
            return null;
        }
    }
}
