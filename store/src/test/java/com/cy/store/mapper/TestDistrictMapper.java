package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDistrictMapper {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void test1(){
        List<District> districtByParent = districtMapper.getDistrictByParent("130100");

        districtByParent.forEach(district -> System.out.println(district));

    }
    @Test
    public void test2(){
        String districtByCode = districtMapper.getNameByCode("130006");
        System.out.println(districtByCode);
    }
}
