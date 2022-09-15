package com.cy.store.controller;

import com.cy.store.entity.Cart;
import com.cy.store.entity.JsonResult;
import com.cy.store.entity.vo.CartVo;
import com.cy.store.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController{

    @Autowired
    ICartService cartService;

    @RequestMapping("/addCart")
    public JsonResult<Void> insertCart(HttpSession session , Integer amount , Integer pid){
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        cartService.insertCart(pid,amount,uid,username);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/cartList")
    public JsonResult<List<CartVo>> list(HttpSession session){
        int uid = Integer.parseInt(session.getAttribute("uid").toString());
        List<CartVo> result = cartService.findCartByUid(uid);

        return new JsonResult<>(OK,result);
    }

    @RequestMapping("/addNum")
    public JsonResult<Integer> updateNum(HttpSession session , Integer cid ,Integer num){
        String username = session.getAttribute("username").toString();
        int uid = Integer.parseInt(session.getAttribute("uid").toString());
        Integer data = cartService.addNum(cid, username, num, uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("/accounts")
    public JsonResult<List<CartVo>> accounts(HttpSession session , @RequestParam("cids") Integer[] cids){
        int uid = Integer.parseInt(session.getAttribute("uid").toString());
        List<CartVo> result = cartService.findCartByList(cids, uid);
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/delete")
    public JsonResult<Void> delete(HttpSession session , Integer cid){
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        cartService.deleteCart(cid,uid);
        return new JsonResult<>(OK);
    }
}
