package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.service.serviceImpl.AddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAddressService {

    @Autowired
    private AddressServiceImpl addressService;
    @Test
    public void test1(){
        Address address = new Address();
        address.setAddress("江岸区谌家矶");
        address.setUid(3);
        address.setPhone("123456789");
        address.setCreatedUser("小李");
        address.setCreatedTime(new Date());
        addressService.insertAddress(address,"小王",3);
    }
    @Test
    public void test2(){
        List<Address> addressByUid = addressService.findAddressByUid(3);
        addressByUid.forEach(address -> System.out.println(address));
    }
    @Test
    public void test3(){
        addressService.setIsDefault(6,3,"ly666");
    }
    @Test
    public void test4(){
        addressService.deleteAddressByAid(2,3);
    }
}
