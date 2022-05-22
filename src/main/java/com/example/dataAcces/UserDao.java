package com.example.dataAcces;


import com.example.entities.concretes.User;
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
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public   List<User>  getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);

        Query<User> dbQuery = currentSession.createQuery(criteriaQuery);

        List<User> resultList = dbQuery.getResultList();
        return  resultList;
    }

    public User findByEmail(String email){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
        criteriaQuery.select(root).where(emailPredicate);

        Query<User> query = currentSession.createQuery(criteriaQuery);
        User user = query.getSingleResult();
        return user;
    }
   
}
