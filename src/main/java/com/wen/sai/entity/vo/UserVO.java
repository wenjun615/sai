package com.wen.sai.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户显示层对象
 * </p>
 *
 * @author wenjun
 * @since 2021-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    private String token;
}
