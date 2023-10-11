package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.ComputerSpec;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import com.bilgeadam.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ComputerSpecRepository extends MyFactoryRepository<ComputerSpec,Long> {
    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;

    public ComputerSpecRepository(){
        super(new ComputerSpec());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public List<ComputerSpec> findAllUnassignedSpecsForAUser(User user) {
        String sql = "SELECT * FROM tbl_computer_spec as c WHERE c.computerid IS NULL AND c.userid="+user.getId();
        Query query = entityManager.createNativeQuery(sql, ComputerSpec.class);
        List<ComputerSpec> computerSpecList = query.getResultList();
        return computerSpecList;
    }
}
