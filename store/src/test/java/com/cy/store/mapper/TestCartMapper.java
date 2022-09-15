package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.entity.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCartMapper {
    @Autowired
    CartMapper cartMapper;

    @Test
    public void test1(){
        Cart cart = new Cart();
        cart.setCid(1);
        cart.setUid(3);
        cart.setPid(4);
        cart.setNum(1);
        cart.setPrice(4666L);
        cart.setCreatedTime(new Date());
        cart.setCreatedUser("ly666");

        Integer integer = cartMapper.insertCart(cart);
        System.out.println(integer);

    }
    @Test
    public void test2(){
        Integer integer = cartMapper.updateForNum(3, 1,"ly666",new Date());
        System.out.println(integer);
    }

    @Test
    public void test3(){
        List<CartVo> cartByUid = cartMapper.findCartByUid(3);
        cartByUid.forEach(cartVo -> System.out.println(cartVo));
    }

    @Test
    public void test4(){
        Integer[] list = {7,8,9,10,11,12};
        List<CartVo> result = cartMapper.findCartInCid(list);
        result.forEach(cartVo -> System.out.println(cartVo));
    }

    @Test
    public void test5(){
        Integer integer = cartMapper.deleteCartByCid(7);
    }

}
