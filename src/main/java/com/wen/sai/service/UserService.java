package com.wen.sai.service;

import com.wen.sai.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 根据用户名获取单个
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);
}
