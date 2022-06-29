package com.haneefs.springbootpoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haneefs.springbootpoc.entity.CustomerDao;
import com.haneefs.springbootpoc.exception.RecordNotFoundException;
import com.haneefs.springbootpoc.model.Customer;
import com.haneefs.springbootpoc.repository.CustomerRepository;
import com.haneefs.springbootpoc.utils.HaneefsLogger;
import com.haneefs.springbootpoc.utils.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    HaneefsLogger logger = HaneefsLogger.createLogger(CustomerService.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RequestHandler requestHandler;

    public Customer createCustomer(Customer c){
        CustomerDao createdCustomer = customerRepository
                .save(objectMapper.convertValue(c,CustomerDao.class));
        return createdCustomer!=null?objectMapper.convertValue(createdCustomer,Customer.class):null;
    }

    public Customer getCustomerById(int id)  {
        Optional<CustomerDao> custonerDaoOpt = customerRepository.findById(id);

        if(custonerDaoOpt.isEmpty()){
            try {
                throw new RecordNotFoundException("The record is not found with given ID");
            } catch (RecordNotFoundException e) {
                logger.error(requestHandler.getRequestId(),"ERR1","No Record Found with Given ID",id);
                throw new RuntimeException(e);
            }
        }
        return objectMapper.convertValue(custonerDaoOpt.get(),Customer.class);
    }
    
    public List<Customer> getAllCustomers(){
        List<CustomerDao> customers =  customerRepository.findAll();
        return customers.stream().map((dao)->objectMapper.
                convertValue(dao,Customer.class)).collect(Collectors.toList());
    }
}