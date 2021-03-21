package com.wen.sai.common.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 公共分页类
 * </p>
 *
 * @author wenjun
 * @since 2021/1/23
 */
@Data
public class CommonPage<T> {

    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 每页数据条数
     */
    private Integer size;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 数据总条数
     */
    private Long total;

    /**
     * 分页后的数据列表
     */
    private List<T> records;

    /**
     * 将 MyBatis-Plus 分页对象转化成公共分页对象
     *
     * @param page MyBatis-Plus 分页对象
     * @param <T>  分页数据类型
     * @return 公共分页对象
     */
    public static <T> CommonPage<T> convertPage(Page<T> page) {
        CommonPage<T> commonPage = new CommonPage<>();
        commonPage.setCurrent(Convert.toInt(page.getCurrent()));
        commonPage.setSize(Convert.toInt(page.getSize()));
        commonPage.setTotalPage(Convert.toInt(page.getTotal() / page.getSize() + 1));
        commonPage.setTotal(page.getTotal());
        commonPage.setRecords(page.getRecords());
        return commonPage;
    }
}
