package com.wen.sai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.sai.common.exception.ApiException;
import com.wen.sai.common.util.JwtUtil;
import com.wen.sai.config.JwtProperties;
import com.wen.sai.entity.bo.UserDetailsBO;
import com.wen.sai.entity.query.UserQuery;
import com.wen.sai.mapper.UserMapper;
import com.wen.sai.model.Resource;
import com.wen.sai.model.User;
import com.wen.sai.service.ResourceService;
import com.wen.sai.service.UserCacheService;
import com.wen.sai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserCacheService userCacheService;

    private final ResourceService resourceService;

    private final PasswordEncoder passwordEncoder;

    private final JwtProperties jwtProperties;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<Resource> resourceList = resourceService.listByUserId(user.getId());
        return new UserDetailsBO(user, resourceList);
    }

    @Override
    public User getByUsername(String username) {
        User user = userCacheService.getByUsername(username);
        if (Objects.nonNull(user)) {
            return user;
        }
        user = lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        if (Objects.isNull(user)) {
            return null;
        }
        userCacheService.set(user);
        return user;
    }

    @Override
    public String login(UserQuery query) {
        UserDetails userDetails = loadUserByUsername(query.getUsername());
        if (passwordEncoder.matches(query.getPassword(), userDetails.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), null, userDetails.getAuthorities()));
            JwtUtil jwtUtil = new JwtUtil(jwtProperties);
            return jwtUtil.generateToken(userDetails);
        }
        throw new ApiException("用户名或密码错误");
    }
}
