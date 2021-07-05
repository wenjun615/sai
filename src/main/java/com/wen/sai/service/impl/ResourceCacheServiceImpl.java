package com.wen.sai.service.impl;

import com.wen.sai.common.service.RedisService;
import com.wen.sai.config.SaiRedisProperties;
import com.wen.sai.model.Resource;
import com.wen.sai.service.ResourceCacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源缓存 ServiceImpl
 * </p>
 *
 * @author wenjun
 * @since 2020/12/26
 */
@Service
@AllArgsConstructor
public class ResourceCacheServiceImpl implements ResourceCacheService {

    private final RedisService redisService;

    private final SaiRedisProperties saiRedisProperties;

    @Override
    public List<Resource> listByUserId(String userId) {
        String key = String.format("%s:%s:%s", saiRedisProperties.getDatabase(), saiRedisProperties.getKey().getResources(),
                userId);
        return (List<Resource>) redisService.get(key);
    }

    @Override
    public void setResources(String userId, List<Resource> resourceList) {
        String key = String.format("%s:%s:%s", saiRedisProperties.getDatabase(), saiRedisProperties.getKey().getResources(),
                userId);
        redisService.set(key, resourceList, saiRedisProperties.getExpire());
    }
}
