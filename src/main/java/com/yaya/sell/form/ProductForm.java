package com.yaya.sell.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author yaomengya
 * @date 2020/5/4
 */
@Data
@Accessors(chain = true)
public class ProductForm {

    private String productId;

    @NotEmpty(message = "商品名称必填")
    private String productName;

    @DecimalMin(message = "价格最低 0.01 元", value = "0.01")
    private BigDecimal productPrice;

    @NotEmpty(message = "商品描述必填")
    private String productDescription;

    @Min(message = "库存最低为 0", value = 0)
    private Integer productStock;

    @NotEmpty(message = "商品图片必填")
    private String productIcon;

    @NotNull(message = "商品类型必填")
    private Integer categoryType;
}
