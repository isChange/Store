package com.cy.store.service;

import com.cy.store.entity.District;
import com.cy.store.service.serviceImpl.DistrictServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDistrictService {
    @Autowired
    private DistrictServiceImpl districtService;
    @Test
    public void test1(){
        List<District> districtByParent = districtService.getDistrictByParent("86");
        districtByParent.forEach(district -> System.out.println(district));

    }
    @Test
    public void test2(){
        String name = districtService.getNameByCode("100086");
        System.out.println(name);
    }

    @Test
    public void test3(){

    }
}
