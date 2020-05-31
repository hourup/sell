package com.yaya.sell.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yaya.sell.dataobject.OrderDetail;
import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.form.OrderForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaomengya
 * @date 2020/3/22
 */
@Mapper
public interface OrderFormConvert {

    OrderFormConvert INSTANCE = Mappers.getMapper(OrderFormConvert.class);

    @Mappings({
            @Mapping(source = "name", target = "buyerName"),
            @Mapping(source = "phone", target = "buyerPhone"),
            @Mapping(source = "address", target = "buyerAddress"),
            @Mapping(source = "openid", target = "buyerOpenid"),
            @Mapping(target = "orderDetailList", expression = "java(items2OrderDetailList(orderForm.getItems()))")
    })
    OrderDTO convert(OrderForm orderForm);

    default List<OrderDetail> items2OrderDetailList(String items) {
        Gson gson = new Gson();

        if (StringUtils.isEmpty(items)) {
            return new ArrayList<>();
        }

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(items, new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            throw new SellException(ResultEnum.PARAM_ERROR, e.getMessage());
        }
        return orderDetailList;
    }
}
