package com.wen.sai.common.constant;

/**
 * <p>
 * 自定义 API 响应信息常量
 * </p>
 *
 * @author wenjun
 * @since 2021/3/21
 */
public interface ApiMessageConstants {

    String SUCCESS = "操作成功！";

    String FAILURE = "操作失败！";

    String VALIDATE_FAILURE = "请求参数验证失败！";

    String UNAUTHENTICATED = "未登录或登录过期！";

    String UNAUTHORISED = "权限不足！";
}
