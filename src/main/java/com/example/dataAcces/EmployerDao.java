package com.example.dataAcces;

import com.example.entities.concretes.Employer;
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
public class EmployerDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(Employer employer) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(employer);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    public  List<Employer>  getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Employer> criteriaQuery = criteriaBuilder.createQuery(Employer.class);
        Root<Employer> root = criteriaQuery.from(Employer.class);

        criteriaQuery.select(root);

        Query<Employer> dbQuery = currentSession.createQuery(criteriaQuery);

        List<Employer> resultList = dbQuery.getResultList();
        return  resultList;
    }

    public Employer getById(int employerId){
        try {
            Session currentSession = getCurrentSession();
                    CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
                    CriteriaQuery<Employer> criteriaQuery = criteriaBuilder.createQuery(Employer.class);
                    Root<Employer> root = criteriaQuery.from(Employer.class);

                    Predicate employerIdPredicate = criteriaBuilder.equal(root.get("id"), employerId);
                    criteriaQuery.select(root).where(employerIdPredicate);

                    Query<Employer> query = currentSession.createQuery(criteriaQuery);
                    Employer employer = query.getSingleResult();
                    return employer;
        }catch (Exception e){
            return null;
        }

    }

    public Employer findByEmail(String email){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Employer> criteriaQuery = criteriaBuilder.createQuery(Employer.class);
        Root<Employer> root = criteriaQuery.from(Employer.class);

        Predicate  emailPredicate = criteriaBuilder.equal(root.get("email"), "email");
        criteriaQuery.select(root).where(emailPredicate);

        Query<Employer> query = currentSession.createQuery(criteriaQuery);
        Employer employer = query.getSingleResult();
        return employer;
    }
}
