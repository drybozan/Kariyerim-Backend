package com.example.dataAcces;


import com.example.entities.concretes.Language;
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
    public boolean deleteById(Language language) {
        boolean success = true;
        try {
            getCurrentSession().remove(language);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public Language getById(int languageId){
        try {
            Session currentSession = getCurrentSession();
            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<Language> criteriaQuery = criteriaBuilder.createQuery(Language.class);
            Root<Language> root = criteriaQuery.from(Language.class);

            Predicate languageIdPredicate = criteriaBuilder.equal(root.get("id"), languageId);
            criteriaQuery.select(root).where(languageIdPredicate);

            Query<Language> query = currentSession.createQuery(criteriaQuery);
            Language language = query.getSingleResult();
            return language;
        }catch (Exception e){
            return null;
        }
    }

    public List<Language> findByCvId(int cvId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Language> criteriaQuery = criteriaBuilder.createQuery(Language.class);
        Root<Language> languageRoot = criteriaQuery.from(Language.class);

        Predicate findByCvIdPredicate = criteriaBuilder.equal(languageRoot.get("cv_id"), cvId);
        criteriaQuery.select(languageRoot).where(findByCvIdPredicate);
        criteriaQuery.distinct(true);

        Query<Language> query = currentSession.createQuery(criteriaQuery);
        List<Language> language = query.getResultList();
        return language;
    }
}
