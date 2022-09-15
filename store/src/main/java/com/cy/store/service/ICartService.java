package com.cy.store.service;


import com.cy.store.entity.vo.CartVo;

import java.util.List;

public interface ICartService {
    void insertCart(Integer pid , Integer amount ,Integer uid , String username);

    List<CartVo> findCartByUid(Integer uid);

    Integer addNum(Integer cid ,String username ,Integer num , Integer uid);

    List<CartVo> findCartByList(Integer[] list , Integer uid);

    void deleteCart(Integer cid , Integer uid );
}
