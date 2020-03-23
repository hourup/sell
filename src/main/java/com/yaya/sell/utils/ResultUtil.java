package com.yaya.sell.utils;

import com.yaya.sell.enums.IResultEnum;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.vo.ResultVO;

/**
 * 组装返回结果的工具类
 *
 * @author changhr
 * @create 2019-04-23 16:09
 */
public final class ResultUtil {

    /**
     * 拼装带返回数据的成功结果
     *
     * @param data 成功时返回的数据对象
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>()
                .setCode(ResultEnum.SUCCESS.getCode())
                .setMsg(ResultEnum.SUCCESS.getMsg())
                .setData(data);
    }

    /**
     * 拼装带返回数据的成功结果，自定义成功状态码
     *
     * @param data        成功时返回的数据对象
     * @param iResultEnum 成功状态码枚举类
     */
    public static <T> ResultVO<T> success(T data, IResultEnum iResultEnum) {
        return new ResultVO<T>()
                .setCode(iResultEnum.getCode())
                .setMsg(iResultEnum.getMsg())
                .setData(data);
    }

    /**
     * 拼装不带返回数据的成功结果
     */
    public static <T> ResultVO<T> success() {
        return new ResultVO<T>()
                .setCode(ResultEnum.SUCCESS.getCode())
                .setMsg(ResultEnum.SUCCESS.getMsg());
    }

    /**
     * 拼装请求失败的返回结果
     *
     * @param resultEnum 失败结果枚举
     */
    public static <T> ResultVO<T> error(IResultEnum resultEnum) {
        return new ResultVO<T>()
                .setCode(resultEnum.getCode())
                .setMsg(resultEnum.getMsg());
    }

    /**
     * 拼装请求失败的返回结果
     *
     * @param code 失败结果状态码
     * @param msg 失败结果描述信息
     */
    public static <T> ResultVO<T> error(Integer code, String msg) {
        return new ResultVO<T>()
                .setCode(code)
                .setMsg(msg);
    }

    /**
     * 拼装请求失败的返回结果
     *
     * @param resultEnum 失败结果枚举
     * @param msg        失败结果描述信息
     */
    public static <T> ResultVO<T> error(IResultEnum resultEnum, String msg) {
        return new ResultVO<T>()
                .setCode(resultEnum.getCode())
                .setMsg(msg);
    }
}
