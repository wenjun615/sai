package com.wen.sai.service.impl;

import com.wen.sai.common.service.RedisService;
import com.wen.sai.model.User;
import com.wen.sai.service.UserCacheService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户缓存 ServiceImpl
 * </p>
 *
 * @author wenjun
 * @since 2021/3/25
 */
@Service
@AllArgsConstructor
public class UserCacheServiceImpl implements UserCacheService {

    private final RedisService redisService;

    @Value("${redis.database}")
    private final String database;

    @Value("${redis.key.user}")
    private final String userKey;

    @Value("${redis.expire}")
    private final Long expire;

    @Override
    public User getByUsername(String username) {
        String key = database + ":" + userKey + ":" + username;
        return (User) redisService.get(key);
    }

    @Override
    public void set(User user) {
        String key = database + ":" + userKey + ":" + user.getUsername();
        redisService.set(key, user, expire);
    }
}
