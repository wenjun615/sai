package com.wen.sai.controller;

import com.wen.sai.common.api.CommonResult;
import com.wen.sai.entity.base.BaseController;
import com.wen.sai.entity.query.UserQuery;
import com.wen.sai.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
@AllArgsConstructor
@RestController
@RequestMapping("/user")
@Api(tags = {"UserController"})
public class UserController extends BaseController {

    private final UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Validated(UserQuery.Login.class) UserQuery query) {
        String token = userService.login(query);
        return CommonResult.successful(token);
    }
}
