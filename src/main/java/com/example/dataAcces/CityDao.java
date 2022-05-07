package com.example.dataAcces;

import com.example.entities.concretes.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }
    public City getAll(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> root = criteriaQuery.from(City.class);

        criteriaQuery.select(root);

        Query<City> dbQuery = currentSession.createQuery(criteriaQuery);

        List<City> resultList = dbQuery.getResultList();
        return (City) resultList;
    }

    public City getById(int cityId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> root = criteriaQuery.from(City.class);

        Predicate cityIdPredicate = criteriaBuilder.equal(root.get("id"), "cityId");
        criteriaQuery.select(root).where(cityIdPredicate);

        Query<City> query = currentSession.createQuery(criteriaQuery);
        City city = query.getSingleResult();
        return city;
    }

}
