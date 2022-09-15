package com.cy.store.service;

import com.cy.store.entity.Order;

public interface IOrderService {

    Order insertOrder(Integer aid ,Integer uid ,Integer[] cids , String username);


}
