package com.wen.sai.common.api;

import lombok.Data;

/**
 * <p>
 * 公共返回结果类
 * </p>
 *
 * @author wenjun
 * @since 2021/1/24
 */
@Data
public class CommonResult<T> {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> successful(T data) {
        return new CommonResult<>(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     *
     * @param message 响应信息
     * @param data    响应数据
     * @param <T>     响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> successful(String message, T data) {
        return new CommonResult<>(CommonCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败
     *
     * @param commonCode 公共响应码枚举
     * @param <T>        响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> failed(CommonCode commonCode) {
        return new CommonResult<>(commonCode.getCode(), commonCode.getMessage(), null);
    }

    /**
     * 失败
     *
     * @param commonCode 公共响应码枚举
     * @param message    响应信息
     * @param <T>        响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> failed(CommonCode commonCode, String message) {
        return new CommonResult<>(commonCode.getCode(), message, null);
    }
}
