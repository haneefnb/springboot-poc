package com.haneefs.springbootpoc.controller;
import com.haneefs.springbootpoc.constants.AppConstants;
import com.haneefs.springbootpoc.entity.CustomerDao;
import com.haneefs.springbootpoc.model.Customer;
import com.haneefs.springbootpoc.service.CustomerService;
import com.haneefs.springbootpoc.utils.HaneefsLogger;
import com.haneefs.springbootpoc.utils.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(AppConstants.CUSTOMER_API_URI)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    RequestHandler requestHandler;

    HaneefsLogger logger = HaneefsLogger.createLogger(CustomerController.class);

    @PostMapping("/")
    public ResponseEntity createCustomer(@RequestBody Customer c) throws URISyntaxException {
        Customer result = customerService.createCustomer(c);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity getCustomer(@PathVariable("customerId") String customerId){
        logger.info(requestHandler.getRequestId(),"METHOD_START","Controller Method Started");
        Customer customer = null;
             customer = customerService.getCustomerById(Integer.parseInt(customerId));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/")
    public ResponseEntity getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }



}