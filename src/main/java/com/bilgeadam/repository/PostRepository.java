package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import com.bilgeadam.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class PostRepository extends MyFactoryRepository<Post,Long> {
    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;

    public PostRepository(){
        super(new Post());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public List<Post> myLikedPosts(Long userId) {
        String sql = "SELECT p.* FROM tbl_post p INNER JOIN tbl_like l ON p.id = l.postid WHERE l.userid =:userId";
        Query query = entityManager.createNativeQuery(sql,Post.class);
        query.setParameter("userId",userId);
        List<Post> postList = query.getResultList();
        return postList;
    }

    public List<Post> findPostsByUserId(Long userId) {
        String sql = "SELECT * FROM tbl_post WHERE userid =:userId";
        Query query = entityManager.createNativeQuery(sql, Post.class);
        query.setParameter("userId",userId);
        List<Post> likedPosts = query.getResultList();
        return likedPosts;
    }
}
