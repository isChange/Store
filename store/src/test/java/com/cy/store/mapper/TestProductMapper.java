package com.cy.store.mapper;

import com.cy.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProductMapper {
    @Autowired
    ProductMapper productMapper;

    @Test
    public void test1(){
        List<Product> productByNum = productMapper.findProductByNum();
        productByNum.forEach(product -> System.out.println(product));
    }

    @Test
    public void test2(){
        Product productById = productMapper.findProductById(10000002);
        System.out.println(productById);
    }
}
