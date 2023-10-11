package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Photo;
import com.bilgeadam.utility.HibernateUtility;
import com.bilgeadam.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public class PhotoRepository extends MyFactoryRepository<Photo,Long> {

    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;
    public PhotoRepository(){
        super(new Photo());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


}
