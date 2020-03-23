package com.yaya.sell.service.impl;

import com.yaya.sell.dataobject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.notNull(productCategory, "查询一个");
        Assert.isTrue(productCategory.getCategoryId() == 1, "相等");
    }

    @Test
    void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.notEmpty(productCategoryList, "不为空");
    }

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        Assert.notEmpty(productCategoryList, "不为空");
    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory()
                .setCategoryName("男生专享")
                .setCategoryType(5);
        ProductCategory category = categoryService.save(productCategory);
        Assert.notNull(category, "查询一个");
    }
}