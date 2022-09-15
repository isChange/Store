package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);

}
