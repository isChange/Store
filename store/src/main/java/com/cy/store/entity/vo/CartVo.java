package com.cy.store.entity.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVo cartVo = (CartVo) o;
        return Objects.equals(cid, cartVo.cid) && Objects.equals(uid, cartVo.uid) && Objects.equals(pid, cartVo.pid) && Objects.equals(price, cartVo.price) && Objects.equals(num, cartVo.num) && Objects.equals(title, cartVo.title) && Objects.equals(realPrice, cartVo.realPrice) && Objects.equals(image, cartVo.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid, pid, price, num, title, realPrice, image);
    }
}
