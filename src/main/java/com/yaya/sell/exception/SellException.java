package com.yaya.sell.exception;

import com.yaya.sell.enums.IResultEnum;

/**
 * @author yaomengya
 * @date 2020/3/21
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SellException(IResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(IResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }
}
