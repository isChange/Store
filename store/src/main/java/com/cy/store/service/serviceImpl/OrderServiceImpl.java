package com.cy.store.service.serviceImpl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.entity.vo.CartVo;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ICartService cartService;
    @Autowired
    IAddressService addressService;

    @Override
    public Order insertOrder(Integer aid, Integer uid, Integer[] cids ,String username) {
        // 创建当前时间对象
        Date now = new Date();
        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVo> cartInCid = cartService.findCartByList(cids,uid);

        // 计算这些商品的总价
        Long totalPrice = 0l;
        for (CartVo cartVo :cartInCid) {
            totalPrice += cartVo.getPrice() * cartVo.getPrice();
        }

        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address addressByAid = addressService.findAddressByAid(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvProvince(addressByAid.getProvinceName());
        order.setRecvCity(addressByAid.getCityName());
        order.setRecvArea(addressByAid.getAreaName());
        order.setRecvPhone(addressByAid.getPhone());
        order.setRecvName(addressByAid.getName());
        order.setRecvAddress(addressByAid.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        order.setPayTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer integer = orderMapper.insertOrder(order);
        if (integer != 1){
            throw new InsertException("添加时发生未知异常");
        }
        // 遍历carts，循环插入订单商品数据
        for (CartVo cartVo :cartInCid) {
            // 创建订单商品数据
            OrderItem orderItem = new OrderItem();
            // 补全数据：oid(order.getOid())
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setPrice(cartVo.getPrice());
            orderItem.setImage(cartVo.getImage());
            orderItem.setNum(cartVo.getNum());
            // 补全数据：4项日志
            orderItem.setCreatedUser(order.getCreatedUser());
            orderItem.setCreatedTime(now);
            orderItem.setModifiedUser(order.getModifiedUser());
            orderItem.setModifiedTime(order.getModifiedTime());
            // 插入订单商品数据
            Integer integer1 = orderMapper.insertOrderItem(orderItem);
            if (integer1 != 1){
                throw new InsertException("添加数据时发生未知异常");
            }
        }
        // 返回
        return order;
    }
}
