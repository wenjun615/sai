package com.wen.sai.common.exception;

import com.wen.sai.common.api.CommonCode;
import lombok.Getter;

/**
 * <p>
 * 自定义 API 异常
 * </p>
 *
 * @author wenjun
 * @since 2020/12/17
 */
@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1820168484211116930L;

    /**
     * 公共响应码枚举
     */
    private CommonCode commonCode;

    public ApiException(CommonCode commonCode) {
        super(commonCode.getMessage());
        this.commonCode = commonCode;
    }

    public ApiException(String message) {
        super(message);
    }
}
