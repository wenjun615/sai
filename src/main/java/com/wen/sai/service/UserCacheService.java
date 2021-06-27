package com.wen.sai.service;

import com.wen.sai.model.User;

/**
 * <p>
 * 用户缓存 Service
 * </p>
 *
 * @author wenjun
 * @since 2021/3/25
 */
public interface UserCacheService {

    /**
     * 根据用户名获取单个
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 设置
     *
     * @param user 用户
     */
    void set(User user);
}
