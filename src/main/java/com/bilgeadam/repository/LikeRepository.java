package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Like;

import com.bilgeadam.utility.HibernateUtility;
import com.bilgeadam.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigInteger;


public class LikeRepository extends MyFactoryRepository<Like,Long> {

    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;
    public LikeRepository(){
        super(new Like());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder =entityManager.getCriteriaBuilder();
    }

    public boolean userAlreadyLikedAPost(Long id, Long postId) {

        String sql = "SELECT COUNT(*) FROM tbl_like WHERE userid=:userId AND postid=:postId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId",id);
        query.setParameter("postId",postId);
        BigInteger count = (BigInteger) query.getSingleResult();
        //BigInt
        return count.longValue() > 0;

    }
}
