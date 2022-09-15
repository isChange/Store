package com.cy.store.controller;

import com.cy.store.entity.JsonResult;
import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;

    @RequestMapping("/insert")
    public JsonResult<Order> insertOrder(HttpSession session , Integer aid , @RequestParam("cids") Integer[] cids){
        int uid = Integer.parseInt(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        Order order = orderService.insertOrder(aid, uid, cids, username);
        return new JsonResult<>(OK,order);
    }
}
