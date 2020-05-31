package com.yaya.sell.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yaomengya
 * @date 2020/5/4
 */
@Data
@Accessors(chain = true)
public class CategoryForm {

    /** 类目 ID */
    private Integer categoryId;

    /** 类目名字 */
    @NotEmpty(message = "类目名称必填")
    private String categoryName;

    /** 类目类型 */
    @NotNull(message = "类目类型不能为空")
    @Min(value = 0, message = "类目类型需要大于 0")
    private Integer categoryType;
}
