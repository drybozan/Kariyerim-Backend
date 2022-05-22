package com.example.dataAcces;

import com.example.entities.concretes.City;
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
public class CityDao {
    @Autowired
    private SessionFactory sessionFactory ;
    private static Logger logger = LoggerFactory.getLogger(CityDao.class);
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    public List<City> getAll(){
        logger.warn("---Here 0");
        Session currentSession = getCurrentSession();
        logger.warn("---Here 1");
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        logger.warn("---Here 2");

        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        logger.warn("---Here 3");

        Root<City> root = criteriaQuery.from(City.class);
        logger.warn("----Here 4");

        criteriaQuery.select(root);

        Query<City> dbQuery = currentSession.createQuery(criteriaQuery);

        List<City> resultList = dbQuery.getResultList();
        return resultList;
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
