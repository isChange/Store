package com.cy.store.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Data
@ToString
public class Order extends BaseEntity{
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;//'状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成',
    private Date orderTime;
    private Date payTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(oid, order.oid) && Objects.equals(uid, order.uid) && Objects.equals(recvName, order.recvName) && Objects.equals(recvPhone, order.recvPhone) && Objects.equals(recvProvince, order.recvProvince) && Objects.equals(recvCity, order.recvCity) && Objects.equals(recvArea, order.recvArea) && Objects.equals(recvAddress, order.recvAddress) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(status, order.status) && Objects.equals(orderTime, order.orderTime) && Objects.equals(payTime, order.payTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oid, uid, recvName, recvPhone, recvProvince, recvCity, recvArea, recvAddress, totalPrice, status, orderTime, payTime);
    }
}
