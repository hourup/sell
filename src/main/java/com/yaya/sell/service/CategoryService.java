package com.yaya.sell.service;

import com.yaya.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author changhr2013
 * @date 2020/3/19
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory category);
}
