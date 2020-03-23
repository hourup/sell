package com.yaya.sell.convert;

import com.yaya.sell.dataobject.ProductInfo;
import com.yaya.sell.vo.ProductInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
@Mapper
public interface ProductInfoConvert {

    ProductInfoConvert INSTANCE = Mappers.getMapper(ProductInfoConvert.class);

    @Mappings({})
    ProductInfoVO convert(ProductInfo productInfo);

    @Mappings({})
    List<ProductInfoVO> convert(List<ProductInfo> productInfoList);
}
