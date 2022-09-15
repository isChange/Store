package com.cy.store.service;

import com.cy.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProductService {
    @Autowired
    IProductService productService;

    @Test
    public void test1(){
        Product productById = productService.findProductById(10000003);
        System.out.println(productById);
    }
}
