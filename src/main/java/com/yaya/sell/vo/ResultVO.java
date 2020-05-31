package com.yaya.sell.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yaomengya
 * @date 2020/3/20
 */
@Data
@Accessors(chain = true)
public class ResultVO<T> {

    /** 错误码，0 表示请求成功 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    private T data;
}
