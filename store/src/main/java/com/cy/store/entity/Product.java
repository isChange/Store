package com.cy.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString

public class Product extends BaseEntity implements Serializable {

    private Integer id; //商品id
    private Integer categoryId; //分类id
    private String itemType; //商品系列
    private String title; //商品标题
    private String sellPoint; //商品卖点
    private Long price; //
    private Integer num; //库存数量
    private String image;//图片路径
    private Integer status;//商品状态  1：上架   2：下架   3：删除
    private Integer priority;//显示优先级

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(categoryId, product.categoryId) && Objects.equals(itemType, product.itemType) && Objects.equals(title, product.title) && Objects.equals(sellPoint, product.sellPoint) && Objects.equals(price, product.price) && Objects.equals(num, product.num) && Objects.equals(image, product.image) && Objects.equals(status, product.status) && Objects.equals(priority, product.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, categoryId, itemType, title, sellPoint, price, num, image, status, priority);
    }
}
