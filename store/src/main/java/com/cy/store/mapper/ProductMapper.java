package com.cy.store.mapper;

import com.cy.store.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    List<Product> findProductByNum();

    Product findProductById(@Param("id") Integer id);
}
