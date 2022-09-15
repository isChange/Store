package com.cy.store.service;


import com.cy.store.entity.Cart;
import com.cy.store.entity.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCartService {

    @Autowired
    ICartService cartService;

    @Test
    public void test1(){
        Cart cart = new Cart();
        cart.setCid(1);
        cart.setUid(3);
        cart.setPid(4);
        cart.setNum(4);
        cart.setPrice(4666L);
        cart.setCreatedTime(new Date());
        cart.setCreatedUser("ly666");
        cartService.insertCart(2,3,3,"ly666");
    }
    @Test
    public void test2(){
        cartService.addNum(10,"ly666",1,3);
    }
    @Test
    public void test3(){
        Integer[] list = {7,8,9,10,11,12};
        List<CartVo> cartByList = cartService.findCartByList(list,3);
        cartByList.forEach(cartVo -> System.out.println(cartVo));
    }
    @Test
    public void test4(){
        cartService.deleteCart(11,5);
    }
}
