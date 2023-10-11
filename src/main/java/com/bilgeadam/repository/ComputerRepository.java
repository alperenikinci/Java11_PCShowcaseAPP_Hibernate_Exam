package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Computer;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import com.bilgeadam.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ComputerRepository extends MyFactoryRepository<Computer,Long> {

    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;


    public ComputerRepository(){
        super(new Computer());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public List<Computer> findAllByUser(User user) {
        String sql = "SELECT * FROM tbl_computer as c WHERE c.userid=:userId";
        Query query =entityManager.createNativeQuery(sql,Computer.class);
        query.setParameter("userId",user.getId());
        List<Computer> computerList = query.getResultList();
        return computerList;
    }
}
