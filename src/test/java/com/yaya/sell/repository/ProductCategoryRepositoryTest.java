package com.yaya.sell.repository;

import com.yaya.sell.dataobject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void saveTest() {
        ProductCategory productCategory = repository.findById(2).get();
        productCategory.setCategoryType(6);
        repository.save(productCategory);
    }
}