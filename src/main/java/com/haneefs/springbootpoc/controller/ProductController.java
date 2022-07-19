package com.haneefs.springbootpoc.controller;
import com.haneefs.springbootpoc.constants.AppConstants;
import com.haneefs.springbootpoc.model.Product;
import com.haneefs.springbootpoc.service.ProductService;
import com.haneefs.springbootpoc.utils.HaneefsLogger;
import com.haneefs.springbootpoc.utils.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(AppConstants.PRODUCT_API_URI)
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    RequestHandler requestHandler;

    HaneefsLogger logger = HaneefsLogger.createLogger(ProductController.class);

    @PostMapping("/")
    public ResponseEntity createProduct(@RequestBody Product c) throws URISyntaxException {
        Product result = productService.createProduct(c);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProduct(@PathVariable("productId") String productId){
        logger.info(requestHandler.getRequestId(),"METHOD_START","Controller Method Started");
        Product product = null;
             product = productService.getProductById(Long.parseLong(productId));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/")
    public ResponseEntity getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }



}