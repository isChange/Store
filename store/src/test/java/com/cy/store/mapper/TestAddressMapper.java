package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAddressMapper {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void test1(){
        Integer count = addressMapper.countByUid(3);
        System.out.println(count);
    }
    @Test
    public void test2(){
        Address address = new Address();
        address.setAddress("江岸区谌家矶");
        address.setUid(3);
        address.setPhone("123456789");
        address.setCreatedUser("小李");
        address.setCreatedTime(new Date());
        Integer insert = addressMapper.insert(address);

    }
    @Test
    public void test3(){
        List<Address> addressByUid = addressMapper.findAddressByUid(3);
        addressByUid.forEach(address -> System.out.println(address));
    }

    @Test
    public void test4(){
        Integer integer = addressMapper.updateIsDefaultByUid(3);
        System.out.println(integer);
    }
    @Test
    public void test5(){
        Integer integer = addressMapper.updateIsDefaultByAid(8,"ly666",new Date());
    }
    @Test
    public void test6(){
        Integer integer = addressMapper.deleteAddressByAid(1);
    }

    @Test
    public void test7(){
        Address address = addressMapper.deleteAddressForDef(3);
        System.out.println(address);
    }
}
