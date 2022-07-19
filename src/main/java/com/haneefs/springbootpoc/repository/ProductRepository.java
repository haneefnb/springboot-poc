package com.haneefs.springbootpoc.repository;

import com.haneefs.springbootpoc.entity.CustomerDao;
import com.haneefs.springbootpoc.entity.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<ProductDao,Long> {
}
