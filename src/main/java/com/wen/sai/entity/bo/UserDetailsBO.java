package com.wen.sai.entity.bo;

import com.wen.sai.model.Resource;
import com.wen.sai.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Spring Security 用户详情
 * </p>
 *
 * @author wenjun
 * @since 2021/3/24
 */
@Data
@AllArgsConstructor
public class UserDetailsBO implements UserDetails {

    private static final long serialVersionUID = 3840428676226849008L;

    /**
     * 用户
     */
    private User user;

    /**
     * 资源
     */
    private List<Resource> resourceList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resourceList.stream()
                .map(resource -> new SimpleGrantedAuthority(resource.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getDeleted().equals(0);
    }
}
