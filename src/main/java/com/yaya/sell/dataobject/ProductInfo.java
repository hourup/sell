package com.yaya.sell.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author changhr2013
 * @date 2020/3/19
 */
@Entity
@Data
@Accessors(chain = true)
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private Integer productStock;

    private String productIcon;

    /** 0：正常 1：下架 */
    private Integer productStatus;

    private Integer categoryType;
}
