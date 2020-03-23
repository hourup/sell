package com.yaya.sell.repository;

import com.yaya.sell.dataobject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> byId = repository.findById(1);
        System.out.println(byId.get());
    }

    @Test
    @Transactional
    public void saveTest() {
        ProductCategory category = new ProductCategory()
                .setCategoryName("testCategoryName")
                .setCategoryType(121);
        ProductCategory result = repository.save(category);
        Assert.notNull(result, "保存成功");
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> categoryTypeList = Arrays.asList(2, 3, 4);
        List<ProductCategory> categoryList = repository.findByCategoryTypeIn(categoryTypeList);
        Assert.notEmpty(categoryList, "查询成功");
    }
}