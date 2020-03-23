package com.yaya.sell.form;

import com.yaya.sell.dataobject.OrderDetail;
import com.yaya.sell.dto.CartDTO;
import com.yaya.sell.vo.ProductInfoVO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author changhr2013
 * @date 2020/3/22
 */
@Data
@Accessors(chain = true)
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid 必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
