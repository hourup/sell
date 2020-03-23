package com.yaya.sell.repository;

import com.yaya.sell.dataobject.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    void findByProductStatus() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.notEmpty(productInfoList, "非空");
    }

    @Test
    void save(){
        ProductInfo productInfo = new ProductInfo()
                .setProductId("123456")
                .setProductName("皮蛋瘦肉粥")
                .setProductPrice(new BigDecimal("3.2"))
                .setProductStock(100)
                .setProductDescription("好喝的粥")
                .setProductIcon("http://xxx.jpg")
                .setProductStatus(0)
                .setCategoryType(2);

        ProductInfo info = repository.save(productInfo);
        Assert.notNull(info, "非空");
    }
}