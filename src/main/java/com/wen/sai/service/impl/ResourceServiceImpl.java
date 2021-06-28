package com.wen.sai.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wen.sai.model.Resource;
import com.wen.sai.mapper.ResourceMapper;
import com.wen.sai.service.ResourceCacheService;
import com.wen.sai.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
@Service
@AllArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final ResourceCacheService resourceCacheService;

    private final ResourceMapper resourceMapper;

    @Override
    public List<Resource> listByUserId(String userId) {
        List<Resource> resourceList = resourceCacheService.listByUserId(userId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = resourceMapper.listByUserId(userId);
        if (CollUtil.isEmpty(resourceList)) {
            return null;
        }
        resourceCacheService.setResources(userId, resourceList);
        return resourceList;
    }
}
