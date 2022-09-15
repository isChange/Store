package com.cy.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
public class Cart extends BaseEntity implements Serializable {
    private Integer cid ; // '购物车数据id',
    private Integer uid;  // '用户id',
    private Integer pid; // '商品id',
    private Long price; // '加入时商品单价',
    private Integer num ;// '商品数量',

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cid, cart.cid) && Objects.equals(uid, cart.uid) && Objects.equals(pid, cart.pid) && Objects.equals(price, cart.price) && Objects.equals(num, cart.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cid, uid, pid, price, num);
    }
}
