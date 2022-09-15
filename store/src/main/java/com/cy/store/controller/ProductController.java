package com.cy.store.controller;

import com.cy.store.entity.JsonResult;
import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController{

    @Autowired
    public IProductService productService;

    @RequestMapping("/hotProduct")
    public JsonResult<List<Product>> hotList(){
        List<Product> hotProductList = productService.findHotProduct();
        return new JsonResult<>(OK,hotProductList);
    }

    @RequestMapping("/detail")
    public JsonResult<Product> detail(Integer id){
        Product result = productService.findProductById(id);
        return new JsonResult<>(OK,result);
    }
}
