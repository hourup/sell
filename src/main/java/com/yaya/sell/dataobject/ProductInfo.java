package com.yaya.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yaya.sell.enums.ProductStatusEnum;
import com.yaya.sell.utils.EnumUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yaomengya
 * @date 2020/3/19
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private Integer productStock;

    private String productIcon;

    /** 0：正常 1：下架 */
    private Integer productStatus = ProductStatusEnum.DOWN.getCode();

    private Integer categoryType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(this.productStatus, ProductStatusEnum.class);
    }
}
