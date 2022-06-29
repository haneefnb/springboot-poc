package com.haneefs.springbootpoc.controller;

import com.haneefs.springbootpoc.AppUtils;
import com.haneefs.springbootpoc.constants.AppConstants;
import com.haneefs.springbootpoc.model.Customer;
import com.haneefs.springbootpoc.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Test
    public void getCustomerByIdTest() throws Exception {
        //Mocking
        Customer c = Customer.builder().id(101).name("Haneef").address("Chennai").build();
        when(customerService.getCustomerById(101)).thenReturn(c);

        //Request Preparation
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(AppConstants.CUSTOMER_API_URI+"/101")
                        .accept(MediaType.APPLICATION_JSON);

        //Calling API & Verification
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:101,name:Haneef,address:Chennai}"))
                .andReturn();
    }


    @Test
    public void getCustomerByIdTestNegativeCase() throws Exception {
        //Mocking
        Customer c = Customer.builder().id(101).name("Haneef").address("Chennai").build();
        when(customerService.getCustomerById(101)).thenReturn(c);

        //Request Preparation
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(AppConstants.CUSTOMER_API_URI+"/102")
                        .accept(MediaType.APPLICATION_JSON);

        //Calling API & Verification
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Sorry"))
                .andReturn();
    }

    @Test
    public void createCustomerTest() throws Exception {
        //Mocking
        Customer c = Customer.builder().id(102).name("Haneef").address("Chennai").build();
        when(customerService.createCustomer(c)).thenReturn(c);

        RequestBuilder rb = MockMvcRequestBuilders
                .post(AppConstants.CUSTOMER_API_URI+"/")
                .content(AppUtils.getJsonString(c))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result =  mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();

    }



}
