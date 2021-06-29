package com.wen.sai.component;

import cn.hutool.json.JSONUtil;
import com.wen.sai.common.api.CommonCode;
import com.wen.sai.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 自定义返回结果：未登录或登录过期
 * </p>
 *
 * @author wenjun
 * @since 2020/12/21
 */
@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.println(JSONUtil.parse(CommonResult.failed(CommonCode.UNAUTHENTICATED)));
        writer.flush();
        writer.close();
    }
}
