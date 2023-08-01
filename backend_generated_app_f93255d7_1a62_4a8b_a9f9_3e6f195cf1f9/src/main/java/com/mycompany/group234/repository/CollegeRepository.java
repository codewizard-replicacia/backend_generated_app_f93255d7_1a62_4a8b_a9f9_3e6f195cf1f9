package com.mycompany.group234.repository;


import com.mycompany.group234.model.College;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class CollegeRepository extends SimpleJpaRepository<College, String> {
    private final EntityManager em;
    public CollegeRepository(EntityManager em) {
        super(College.class, em);
        this.em = em;
    }
    @Override
    public List<College> findAll() {
        return em.createNativeQuery("Select * from \"generated_app\".\"College\"", College.class).getResultList();
    }
}