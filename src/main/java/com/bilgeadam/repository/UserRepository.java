package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import com.bilgeadam.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public class UserRepository extends MyFactoryRepository<User,Long> {
    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;

    public UserRepository(){
        super(new User());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM tbl_user as u WHERE u.email=:email";
        Query query = entityManager.createNativeQuery(sql,User.class);
        query.setParameter("email",email);
      //  Optional<User> user = (Optional<User>) query.getSingleResult();
        List<User> userList = query.getResultList();
        Optional<User> user = Optional.ofNullable(userList.get(0));
        return user;
    }
}
