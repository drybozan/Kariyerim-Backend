package com.example.dataAcces;


import com.example.entities.concretes.JobAd;
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
public class JobAdDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(JobAd jobAd) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(jobAd);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public List<JobAd>  getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobAd> criteriaQuery = criteriaBuilder.createQuery(JobAd.class);
        Root<JobAd> root = criteriaQuery.from(JobAd.class);

        criteriaQuery.select(root);

        Query<JobAd> dbQuery = currentSession.createQuery(criteriaQuery);

        List<JobAd> resultList = dbQuery.getResultList();
        return resultList;
    }


    public JobAd getById(int jobAdId){
        try {
           Session currentSession = getCurrentSession();
                   CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
                   CriteriaQuery<JobAd> criteriaQuery = criteriaBuilder.createQuery(JobAd.class);
                   Root<JobAd> root = criteriaQuery.from(JobAd.class);

                   Predicate jobAdIdPredicate = criteriaBuilder.equal(root.get("id"), jobAdId);
                   criteriaQuery.select(root).where(jobAdIdPredicate);

                   Query<JobAd> query = currentSession.createQuery(criteriaQuery);
                   JobAd jobAd = query.getSingleResult();
                   return jobAd;
        }catch (Exception e){
            return null;
        }
    }


//    @Query("Select j from com.Bozan.db.entities.concretes.JobAd j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
//           +" and ((:#{#filter.jobPositionId}) IS NULL OR j.jobPosition.id IN (:#{#filter.jobPositionId}))"
//           +" and ((:#{#filter.workPlaceId}) IS NULL OR j.workPlace.id IN (:#{#filter.workPlaceId}))"
//            +" and ((:#{#filter.workTimeId}) IS NULL OR j.workTime.id IN (:#{#filter.workTimeId}))"
//    )
//    public Page<JobAd> getByFilter(@Param("filter") JobAdFilter jobAdFilter, Pageable pageable);
}


