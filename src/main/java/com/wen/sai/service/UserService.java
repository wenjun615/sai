package com.wen.sai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.sai.entity.query.UserQuery;
import com.wen.sai.model.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名加载用户详情
     *
     * @param username 用户名
     * @return 用户详情
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 登录
     *
     * @param query 请求参数
     * @return Token
     */
    String login(UserQuery query);
}
