package com.haneefs.springbootpoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haneefs.springbootpoc.entity.CustomerDao;
import com.haneefs.springbootpoc.model.Customer;
import com.haneefs.springbootpoc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private  CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    ObjectMapper mapper;

    @Test
    public void getAllCustomersTest(){
        //Creating a list of Daos
        List<CustomerDao> customers = Arrays.asList(
                CustomerDao.builder().id(101).name("Haneef").address("Chennai").build(),
                CustomerDao.builder().id(102).name("Raj").address("Banglore").build()
        );

        CustomerDao cdao1 = CustomerDao.builder().id(101).name("Haneef").address("Chennai").build();
        CustomerDao cdao2 = CustomerDao.builder().id(102).name("Raj").address("Banglore").build();

        Customer c1 = Customer.builder().id(101).name("Haneef").address("Chennai").build();
        Customer c2 = Customer.builder().id(102).name("Raj").address("Banglore").build();
        //Mocking repository method
        when(customerRepository.findAll()).thenReturn(customers);
        when(mapper.convertValue(cdao1,Customer.class)).thenReturn(c1);
        when(mapper.convertValue(cdao2,Customer.class)).thenReturn(c2);

        assertEquals(customers.size(),customerService.getAllCustomers().size());

    }
}
