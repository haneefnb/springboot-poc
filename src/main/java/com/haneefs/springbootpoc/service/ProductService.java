package com.haneefs.springbootpoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haneefs.springbootpoc.entity.ProductDao;
import com.haneefs.springbootpoc.exception.RecordNotFoundException;
import com.haneefs.springbootpoc.model.Product;
import com.haneefs.springbootpoc.repository.ProductRepository;
import com.haneefs.springbootpoc.utils.HaneefsLogger;
import com.haneefs.springbootpoc.utils.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
   @Autowired
    ProductRepository productRepository;

    HaneefsLogger logger = HaneefsLogger.createLogger(ProductService.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RequestHandler requestHandler;

    public Product createProduct(Product c){
        ProductDao createdProduct = productRepository
                .save(objectMapper.convertValue(c,ProductDao.class));
        return createdProduct!=null?objectMapper.convertValue(createdProduct,Product.class):null;
    }

    public Product getProductById(long id)  {
        Optional<ProductDao> productDaoOpt = productRepository.findById(id);

        if(productDaoOpt.isEmpty()){
            try {
                throw new RecordNotFoundException("The record is not found with given ID");
            } catch (RecordNotFoundException e) {
                logger.error(requestHandler.getRequestId(),"ERR1","No Record Found with Given ID",id);
                throw new RuntimeException(e);
            }
        }
        return objectMapper.convertValue(productDaoOpt.get(),Product.class);
    }

    public List<Product> getAllProducts(){
        List<Product> returnList = new ArrayList<>();
        List<ProductDao> products =  productRepository.findAll();
        if(products != null && products.size()>0) {
            returnList =  products.stream()
                    .map((dao) -> objectMapper.convertValue(dao, Product.class)).collect(Collectors.toList());
        }
        return returnList;
    }
}
