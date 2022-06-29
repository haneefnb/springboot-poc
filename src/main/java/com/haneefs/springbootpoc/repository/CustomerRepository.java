package com.haneefs.springbootpoc.repository;

import com.haneefs.springbootpoc.entity.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao,Integer> {
}