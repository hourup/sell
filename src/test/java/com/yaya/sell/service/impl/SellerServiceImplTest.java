package com.yaya.sell.service.impl;

import com.yaya.sell.dataobject.SellerInfo;
import com.yaya.sell.service.SellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerServiceImplTest {

    private static final String OPEN_ID = "oZKwhxIrP0qWhy7oNFnzMrcps9Vs";

    @Autowired
    private SellerService sellerService;

    @Test
    void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid("oZKwhxIrP0qWhy7oNFnzMrcps9Vs");
        assertEquals(sellerInfo.getOpenid(), OPEN_ID);
    }
}