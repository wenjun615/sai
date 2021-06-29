package com.wen.sai.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户相关功能请求参数
 * </p>
 *
 * @author wenjun
 * @since 2021-06-30
 */
@Data
@ApiModel(value = "User Param", description = "用户相关功能请求参数")
public class UserQuery {

    @NotBlank(groups = {Register.class, Login.class})
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank(groups = {Register.class, Login.class})
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    public interface Register {
    }

    public interface Login {
    }
}
