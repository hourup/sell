package com.yaya.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yaomengya
 * @date 2020/3/21
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class CartDTO {

    private String productId;

    private Integer productQuantity;
}
