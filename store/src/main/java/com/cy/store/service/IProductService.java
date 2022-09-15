package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     *
     * @return 返回库存最少的4个商品名称
     */
    List<Product> findHotProduct ();

    Product findProductById(Integer id);

}
