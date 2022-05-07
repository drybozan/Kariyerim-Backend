package com.example.dataAcces;


import com.example.entities.concretes.Cv;
import com.example.entities.concretes.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class LanguageDao {
    @Autowired
    private SessionFactory sessionFactory ;

    private Session getCurrentSession(){

        return sessionFactory.getCurrentSession();
    }

    public boolean save(Language language) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(language);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    public boolean deleteById(int languageId) {
        boolean success = true;
        try {
            getCurrentSession().remove(languageId);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Language getById(int languageId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Language> criteriaQuery = criteriaBuilder.createQuery(Language.class);
        Root<Language> root = criteriaQuery.from(Language.class);

        Predicate languageIdPredicate = criteriaBuilder.equal(root.get("id"), "languageId");
        criteriaQuery.select(root).where(languageIdPredicate);

        Query<Language> query = currentSession.createQuery(criteriaQuery);
        Language language = query.getSingleResult();
        return language;
    }

    public Language findByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Language> criteriaQuery = criteriaBuilder.createQuery(Language.class);
        Root<Language> languageRoot = criteriaQuery.from(Language.class);
        Root<Cv> cvRoot = criteriaQuery.from(Cv.class);

        languageRoot.get("cv").alias("cv");

        Predicate findByCvIdPredicate = criteriaBuilder.equal(languageRoot.get("cv.id"), cvRoot.get("cvId"));
        criteriaQuery.select(languageRoot).where(findByCvIdPredicate);
        criteriaQuery.distinct(true);

        Query<Language> query = currentSession.createQuery(criteriaQuery);
        Language language = query.getSingleResult();
        return language;
    }

}
