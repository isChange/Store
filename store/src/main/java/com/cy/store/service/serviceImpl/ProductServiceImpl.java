package com.cy.store.service.serviceImpl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import com.cy.store.service.ex.ProductStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Product> findHotProduct() {
        List<Product> productByNum = productMapper.findProductByNum();
        for (Product product :productByNum) {
            //清空不需要显示或不必要的数据
            product.setCategoryId(null);
            product.setPriority(null);
            product.setItemType(null);
            product.setSellPoint(null);
            product.setCreatedTime(null);
            product.setCreatedUser(null);
            product.setModifiedTime(null);
            product.setModifiedUser(null);
        }

        return productByNum;
    }

    @Override
    public Product findProductById(Integer id) {
        //获取product
        Product result = productMapper.findProductById(id);
        if (result == null){
            throw new ProductNotFoundException("该商品不存在");
        }
        if (result.getStatus() == 3 || result.getStatus() == 2){
            throw new ProductStatusException("该商品已经下架或已经被删除");
        }
        return result;
    }
}
