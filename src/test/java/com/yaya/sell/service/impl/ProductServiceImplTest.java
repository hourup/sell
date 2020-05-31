package com.yaya.sell.service.impl;

import com.yaya.sell.dataobject.ProductInfo;
import com.yaya.sell.enums.ProductStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.isTrue(productInfo.getProductId().equals("123456"), "相等");
    }

    @Test
    void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.notEmpty(productInfoList, "不为空");
    }

    @Test
    void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<ProductInfo> infoPage = productService.findAll(pageRequest);
        System.out.println(infoPage.getTotalElements());
        infoPage.getContent().forEach(System.out::println);
        Assert.notEmpty(infoPage.getContent(),"不为空");
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo()
                .setProductId("123457")
                .setProductName("皮皮虾")
                .setProductPrice(new BigDecimal("3.2"))
                .setProductStock(100)
                .setProductDescription("很好吃的虾")
                .setProductIcon("http://xxx.jpg")
                .setProductStatus(ProductStatusEnum.DOWN.getCode())
                .setCategoryType(2);
        ProductInfo info = productService.save(productInfo);
        Assert.notNull(info, "不为空");
    }

    @Test
    void onSale() {
        ProductInfo productInfo = productService.onSale("123456");
        Assert.isTrue(productInfo.getProductStatusEnum().equals(ProductStatusEnum.UP), "上架");
    }

    @Test
    void offSale() {
        ProductInfo productInfo = productService.offSale("123456");
        Assert.isTrue(productInfo.getProductStatusEnum().equals(ProductStatusEnum.DOWN), "下架");
    }
}