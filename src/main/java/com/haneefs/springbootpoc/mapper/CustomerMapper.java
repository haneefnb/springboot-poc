package com.haneefs.springbootpoc.mapper;

import com.haneefs.springbootpoc.entity.CustomerDao;
import com.haneefs.springbootpoc.model.Customer;

public class CustomerMapper {
    public Customer convertDaoToDto(CustomerDao input){
        Customer c = Customer.builder().id(input.getId()).build();
        return c;
    }
}
