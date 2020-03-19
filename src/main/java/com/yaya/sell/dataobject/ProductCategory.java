package com.yaya.sell.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author changhr2013
 * @date 2020/3/17
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Accessors(chain = true)
public class ProductCategory {

    /** 类目 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /** 类目名字 */
    private String categoryName;

    /** 类目类型 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
