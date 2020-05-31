package com.yaya.sell.service;

import com.yaya.sell.dataobject.SellerInfo;

/**
 * @author yaomengya
 * @date 2020/5/4
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
