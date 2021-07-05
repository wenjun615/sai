package com.wen.sai.service.impl;

import com.wen.sai.common.service.RedisService;
import com.wen.sai.config.SaiRedisProperties;
import com.wen.sai.model.User;
import com.wen.sai.service.UserCacheService;
import lombok.AllArgsConstructor;
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

    private final SaiRedisProperties saiRedisProperties;

    @Override
    public User getByUsername(String username) {
        String key = String.format("%s:%s:%s", saiRedisProperties.getDatabase(), saiRedisProperties.getKey().getUser(),
                username);
        return (User) redisService.get(key);
    }

    @Override
    public void set(User user) {
        String key = String.format("%s:%s:%s", saiRedisProperties.getDatabase(), saiRedisProperties.getKey().getUser(),
                user.getUsername());
        redisService.set(key, user, saiRedisProperties.getExpire());
    }
}
