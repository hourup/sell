package com.yaya.sell.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author changhr2013
 * @date 2020/3/22
 */
@ApiModel("创建订单表单")
@Data
@Accessors(chain = true)
public class OrderForm {

    @ApiModelProperty("买家姓名")
    @NotEmpty(message = "姓名必填")
    private String name;

    @ApiModelProperty("买家手机号")
    @NotEmpty(message = "手机号必填")
    private String phone;

    @ApiModelProperty("买家地址")
    @NotEmpty(message = "地址必填")
    private String address;

    @ApiModelProperty("买家 openid")
    @NotEmpty(message = "openid 必填")
    private String openid;

    @ApiModelProperty("买家购物车")
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
