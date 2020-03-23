package com.yaya.sell.convert;

import com.yaya.sell.dataobject.OrderDetail;
import com.yaya.sell.dataobject.ProductInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
@Mapper
public interface OrderDetailConvert {

    OrderDetailConvert INSTANCE = Mappers.getMapper(OrderDetailConvert.class);

    @Mappings({})
    OrderDetail convert(ProductInfo productInfo);
}
