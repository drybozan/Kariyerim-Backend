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
import java.util.ArrayList;
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
    public List<JobAd>  getAll(int pageNo, int pageSize){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<JobAd> criteriaQuery = criteriaBuilder.createQuery(JobAd.class);
        Root<JobAd> root = criteriaQuery.from(JobAd.class);

        criteriaQuery.select(root);

        Query<JobAd> dbQuery = currentSession.createQuery(criteriaQuery);
        dbQuery.setFirstResult((pageNo-1) * pageSize);
        dbQuery.setMaxResults(pageSize);
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
        }catch (Exception e){return null;}
    }

    public List<JobAd> getByFilter(int pageNo, int pageSize, Integer[]  cityId, Integer[]  jobPositionId, Integer[]  workPlaceId, Integer[]  workTimeId){
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<JobAd> criteriaQuery = criteriaBuilder.createQuery(JobAd.class);
            Root<JobAd> root = criteriaQuery.from(JobAd.class);

            List<Predicate> prediklerim = new ArrayList<>();

            if(cityId != null)
                prediklerim.add(root.get("city").get("id").in(cityId));
            if (jobPositionId != null)
                prediklerim.add(root.get("jobPosition").get("id").in(jobPositionId));
            if (workPlaceId != null)
                prediklerim.add(root.get("workPlace").get("id").in(workPlaceId));
            if(workTimeId !=null)
                prediklerim.add(root.get("workTime").get("id").in(workTimeId));

            Predicate sonuc = null;
            for(int i=0; i < prediklerim.size(); i++){
              sonuc = criteriaBuilder.and(prediklerim.get(i));
            }
            if(prediklerim.size() > 0)
                criteriaQuery.select(root).where(sonuc);
            else
                criteriaQuery.select(root);
            Query<JobAd> query = currentSession.createQuery(criteriaQuery);
            query.setFirstResult((pageNo-1) * pageSize);
            query.setMaxResults(pageSize);
            List<JobAd> list = query.getResultList();
            return list;
    }
}


