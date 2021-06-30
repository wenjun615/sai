package com.wen.sai.component;

import cn.hutool.core.util.URLUtil;
import com.wen.sai.model.Resource;
import com.wen.sai.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 动态权限数据源，用于获取动态权限规则
 * </p>
 *
 * @author wenjun
 * @since 2020/12/23
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceService resourceService;

    /**
     * 动态权限规则
     */
    private Map<String, ConfigAttribute> configAttributeMap;

    /**
     * 启动服务后执行一次，执行顺序：Constructor 构造方法  -> @Autowired 依赖注入  -> @PostConstruct 方法
     */
    @PostConstruct
    public void loadDataSource() {
        List<Resource> resourceList = resourceService.list();
        // ConcurrentHashMap 线程安全
        configAttributeMap = new ConcurrentHashMap<>(resourceList.size());
        resourceList.forEach(resource -> configAttributeMap.put(resource.getPath(),
                new SecurityConfig(resource.getId())));
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    /**
     * 当前访问资源所需的权限规则集合
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<ConfigAttribute> configAttributeList = new ArrayList<>();
        if (Objects.isNull(configAttributeMap)) {
            loadDataSource();
        }
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String url = ((FilterInvocation) o).getRequestUrl();
        // 当前访问路径
        String path = URLUtil.getPath(url);
        configAttributeMap.forEach((pattern, configAttribute) -> {
            if (pathMatcher.match(pattern, path)) {
                configAttributeList.add(configAttribute);
            }
        });
        return configAttributeList;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
