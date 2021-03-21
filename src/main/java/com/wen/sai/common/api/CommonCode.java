package com.wen.sai.common.api;

import com.wen.sai.common.constant.ApiCodeConstants;
import com.wen.sai.common.constant.ApiMessageConstants;

/**
 * <p>
 * 公共响应码枚举
 * </p>
 *
 * @author wenjun
 * @since 2021/1/24
 */
public enum CommonCode {

    // 公共响应码枚举
    SUCCESS(ApiCodeConstants.SUCCESS, ApiMessageConstants.SUCCESS),
    FAILURE(ApiCodeConstants.FAILURE, ApiMessageConstants.FAILURE),
    VALIDATE_FAILURE(ApiCodeConstants.VALIDATE_FAILURE, ApiMessageConstants.VALIDATE_FAILURE),
    UNAUTHENTICATED(ApiCodeConstants.UNAUTHENTICATED, ApiMessageConstants.UNAUTHENTICATED),
    UNAUTHORISED(ApiCodeConstants.UNAUTHORISED, ApiMessageConstants.UNAUTHORISED);

    /**
     * 响应码
     */
    private final String code;

    /**
     * 响应信息
     */
    private final String message;

    CommonCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
