package com.wen.sai.component;

import cn.hutool.core.collection.CollUtil;
import com.wen.sai.common.constant.ApiMessageConstants;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * <p>
 * 动态权限决策管理器，用于判断用户是否有访问权限
 * </p>
 *
 * @author wenjun
 * @since 2020/12/23
 */
@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollUtil.isNotEmpty(collection)) {
            for (ConfigAttribute configAttribute : collection) {
                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                    if (Objects.equals(grantedAuthority.getAuthority(), configAttribute.getAttribute())) {
                        return;
                    }
                }
            }
            throw new AccessDeniedException(ApiMessageConstants.UNAUTHORISED);
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
