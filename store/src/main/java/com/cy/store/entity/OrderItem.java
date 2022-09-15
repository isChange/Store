package com.cy.store.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class OrderItem extends BaseEntity{
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(oid, orderItem.oid) && Objects.equals(pid, orderItem.pid) && Objects.equals(title, orderItem.title) && Objects.equals(image, orderItem.image) && Objects.equals(price, orderItem.price) && Objects.equals(num, orderItem.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, oid, pid, title, image, price, num);
    }
}
