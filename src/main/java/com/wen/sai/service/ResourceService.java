package com.wen.sai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.sai.model.Resource;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据用户 ID 获取资源列表
     *
     * @param userId 用户 ID
     * @return 资源列表
     */
    List<Resource> listByUserId(String userId);
}
