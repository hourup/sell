package com.yaya.sell.service.impl;

import com.yaya.sell.dataobject.SellerInfo;
import com.yaya.sell.repository.SellerInfoRepository;
import com.yaya.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaomengya
 * @date 2020/5/4
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
