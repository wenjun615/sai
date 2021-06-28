package com.wen.sai.service;

import com.wen.sai.model.Resource;

import java.util.List;

/**
 * <p>
 * 资源缓存 Service
 * </p>
 *
 * @author wenjun
 * @since 2020/12/26
 */
public interface ResourceCacheService {

    /**
     * 根据用户 ID 获取资源列表
     *
     * @param userId 用户 ID
     * @return 资源列表
     */
    List<Resource> listByUserId(String userId);

    /**
     * 设置资源列表
     *
     * @param userId       用户 ID
     * @param resourceList 资源列表
     */
    void setResources(String userId, List<Resource> resourceList);
}
