package com.yaya.sell.convert;

import com.yaya.sell.dataobject.OrderMaster;
import com.yaya.sell.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
@Mapper
public interface OrderMasterConvert {

    OrderMasterConvert INSTANCE = Mappers.getMapper(OrderMasterConvert.class);

    @Mappings({})
    OrderMaster convert(OrderDTO orderDTO);

    @Mappings({})
    OrderDTO convert(OrderMaster orderMaster);
}
