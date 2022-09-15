package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.entity.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    List<CartVo> findCartByUid(@Param("uid") Integer uid);

    Integer insertCart(Cart cart);

    Integer updateForNum(@Param("num") Integer num , @Param("cid") Integer cid , @Param("modifiedUser") String modifiedUser,
                         @Param("modifiedTime")Date modifiedTime);

    Cart findCartByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid);

    Cart findCartByCid(@Param("cid") Integer cid);

    List<CartVo> findCartInCid(@Param("cids") Integer[] cids);

    Integer deleteCartByCid(@Param("cid") Integer cid);


}
