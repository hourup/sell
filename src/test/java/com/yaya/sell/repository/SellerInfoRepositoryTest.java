package com.yaya.sell.repository;

import com.yaya.sell.dataobject.SellerInfo;
import com.yaya.sell.utils.KeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    void add() {
        SellerInfo sellerInfo = new SellerInfo()
                .setSellerId(KeyUtil.getUniqueKey())
                .setUsername("admin")
                .setPassword("admin")
                .setOpenid("abc");

        SellerInfo save = sellerInfoRepository.save(sellerInfo);
        assertNotNull(save);
    }

    @Test
    void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");
        assertEquals(sellerInfo.getOpenid(), "abc");
    }
}