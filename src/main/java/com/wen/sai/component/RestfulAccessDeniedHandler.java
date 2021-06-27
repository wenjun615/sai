package com.wen.sai.component;

import cn.hutool.json.JSONUtil;
import com.wen.sai.common.api.CommonCode;
import com.wen.sai.common.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 自定义返回结果：没有权限访问时
 * </p>
 *
 * @author wenjun
 * @since 2020/12/21
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.println(JSONUtil.parse(CommonResult.failed(CommonCode.UNAUTHORISED)));
        writer.flush();
        writer.close();
    }
}
