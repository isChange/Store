package com.cy.store.service;

import com.cy.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestOrderService {
    @Autowired
    IOrderService orderService;

    @Test
    public void test1(){
        Integer[] cids = {7,8,9,10};
        Order o = orderService.insertOrder(5, 3, cids, "ly666");
        System.out.println(o);
    }

}
