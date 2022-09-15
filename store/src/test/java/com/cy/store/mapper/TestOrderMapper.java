package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestOrderMapper {
    @Autowired
    OrderMapper orderMapper;
    @Test
    public void test1(){
        Order order = new Order();
        order.setUid(3);
        order.setPayTime(new Date());
        order.setRecvName("方昆");
        order.setRecvPhone("10086");
        orderMapper.insertOrder(order);
    }
    @Test
    public void test2(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000002);
        orderItem.setNum(5);
        orderItem.setPrice(6666l);
        orderItem.setTitle("qweewqewqeq");
        orderMapper.insertOrderItem(orderItem);
    }

}
