package com.wen.sai.component;

import com.wen.sai.common.api.CommonCode;
import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.constant.ApiMessageConstants;
import com.wen.sai.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author wenjun
 * @since 2021/1/25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义 API 异常处理
     *
     * @param e 自定义 API 异常
     * @return 公共返回结果对象
     */
    @ExceptionHandler(ApiException.class)
    public CommonResult<?> apiExceptionHandle(ApiException e) {
        log.error(e.getMessage(), e);
        if (Objects.nonNull(e.getCommonCode())) {
            return CommonResult.failed(e.getCommonCode());
        }
        return CommonResult.failed(CommonCode.FAILURE, e.getMessage());
    }

    /**
     * 请求参数校验失败异常处理
     *
     * @param e 请求参数校验失败异常
     * @return 公共返回结果对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<?> validateExceptionHandle(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder(ApiMessageConstants.VALIDATE_FAILURE);
        e.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> stringBuilder.append(fieldError.getField())
                        .append("：")
                        .append(fieldError.getDefaultMessage())
                        .append("；"));
        String message = stringBuilder.toString();
        log.error(message, e);
        return CommonResult.failed(CommonCode.VALIDATE_FAILURE, message);
    }
}
