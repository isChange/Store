package com.cy.store.service.serviceImpl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.entity.vo.CartVo;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class CartService implements ICartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    IProductService productService;
    @Override
    public void insertCart(Integer pid , Integer amount ,Integer uid ,String username) {
        //查看该商品是否在购物车中
        Cart result = cartMapper.findCartByUidAndPid(uid, pid);
        if(result == null){
            // 是：表示该用户并未将该商品添加到购物车
            // 创建Cart对象
            Cart cart = new Cart();
            // 封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            Product product = productService.findProductById(pid);
            // 封装数据：price
            cart.setPrice(product.getPrice());
            // 封装数据：4个日志
            Date now = new Date();
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            Integer integerInsert = cartMapper.insertCart(cart);
            if (integerInsert != 1){
                throw new InsertException("添加时发生未知异常");
            }
        }else {
            Integer integerUpdate =
                    cartMapper.updateForNum(result.getNum() + amount, result.getCid(),username,new Date());
            if (integerUpdate != 1){
                throw new UpdateException("更新数据发生未知异常");
            }
        }


    }

    @Override
    public List<CartVo> findCartByUid(Integer uid) {
        List<CartVo> result = cartMapper.findCartByUid(uid);
        for (CartVo cartVo :result) {
            cartVo.setRealPrice(cartVo.getPrice() * cartVo.getNum());
        }
        return result;
    }

    @Override
    public Integer addNum(Integer cid, String username ,Integer num,Integer uid) {
        Cart result = cartMapper.findCartByCid(cid);
        if (result == null){
            throw new CartNotFoundException("该商品不在购物车中");
        }
        if (result.getUid() != uid){
            throw new UsernameDuplicateException("操作的不是同一个人");
        }
        Integer newNum = result.getNum() + 1;
        Integer integerNum = cartMapper.updateForNum(newNum, cid, username, new Date());
        if (integerNum != 1){
            throw new UpdateException("更新时发现未知异常");
        }
        return newNum;
    }

    @Override
    public List<CartVo> findCartByList(Integer[] list , Integer uid) {
        List<CartVo> result = cartMapper.findCartInCid(list);
        //剔除不是该用户的记录
        /**
         * 因为这里需要对list遍历 只能用迭代器进行移除对应的数据
         * 否者会报异常
         */
        Iterator<CartVo> iterator = result.iterator();
        while (iterator.hasNext()){
            CartVo next = iterator.next();
            if (next.getUid() != uid){
                iterator.remove();
            }
        }
        return result;
    }

    @Override
    public void deleteCart(Integer cid,Integer uid) {

        Cart result = cartMapper.findCartByCid(cid);
        if (result == null){
            return;
        }
        if (result.getUid() != uid){
            throw new AccessDeniedException("操纵这不是同一人");
        }
        Integer integer = cartMapper.deleteCartByCid(cid);
        if (integer != 1){
            throw new DeleteException("删除过程中出现未知异常");
        }

    }
}
