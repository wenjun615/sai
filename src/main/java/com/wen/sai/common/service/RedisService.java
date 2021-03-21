package com.wen.sai.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Redis 操作接口
 * </p>
 *
 * @author wenjun
 * @since 2021/1/24
 */
public interface RedisService {

    /**
     * 设置
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 设置并添加过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间（秒）
     */
    void set(String key, Object value, long time);

    /**
     * 获取
     *
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 删除
     *
     * @param key 键
     * @return 是否成功
     */
    Boolean del(String key);

    /**
     * 批量删除
     *
     * @param keys 键列表
     * @return 删除数据条数
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     *
     * @param key  键
     * @param time 过期时间（秒）
     * @return 是否成功
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     *
     * @param key 键
     * @return 过期时间（秒）
     */
    Long getExpire(String key);

    /**
     * 键是否存在
     *
     * @param key 键
     * @return 是否存在
     */
    Boolean hasKey(String key);

    /**
     * 递增
     *
     * @param key  键
     * @param incr 增量
     * @return 递增后的值
     */
    Long incr(String key, long incr);

    /**
     * 递减
     *
     * @param key  键
     * @param decr 减量
     * @return 递减后的值
     */
    Long decr(String key, long decr);

    /**
     * 设置 Hash 值
     *
     * @param key     键
     * @param hashKey Hash 键
     * @param value   Hash 值
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 设置 Hash 值并添加过期时间
     *
     * @param key     键
     * @param hashKey Hash 键
     * @param value   Hash 值
     * @param time    过期时间（秒）
     * @return 是否成功
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 设置 Hash
     *
     * @param key 键
     * @param map Hash
     */
    void hSetAll(String key, Map<?, ?> map);

    /**
     * 设置 Hash 并添加过期时间
     *
     * @param key  键
     * @param map  Hash
     * @param time 过期时间（秒）
     * @return 是否成功
     */
    Boolean hSetAll(String key, Map<Object, Object> map, long time);

    /**
     * 获取 Hash 值
     *
     * @param key     键
     * @param hashKey Hash 键
     * @return Hash 值
     */
    Object hGet(String key, String hashKey);

    /**
     * 获取 Hash
     *
     * @param key 键
     * @return Hash
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * 删除 Hash 值
     *
     * @param key      键
     * @param hashKeys Hash 键
     * @return 删除元素个数
     */
    Long hDel(String key, Object... hashKeys);

    /**
     * Hash 键是否存在
     *
     * @param key     键
     * @param hashKey Hash 键
     * @return 是否存在
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * Hash 值递增
     *
     * @param key     键
     * @param hashKey Hash 键
     * @param incr    增量
     * @return 递增后的值
     */
    Long hIncr(String key, String hashKey, long incr);

    /**
     * Hash 值递减
     *
     * @param key     键
     * @param hashKey Hash 键
     * @param decr    减量
     * @return 递减后的值
     */
    Long hDecr(String key, String hashKey, long decr);

    /**
     * 添加 Set 元素
     *
     * @param key    键
     * @param values Set 元素
     * @return 添加元素个数
     */
    Long sAdd(String key, Object... values);

    /**
     * 添加 Set 元素并设置过期时间
     *
     * @param key    键
     * @param time   过期时间（秒）
     * @param values Set 元素
     * @return 添加元素个数
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * 获取 Set
     *
     * @param key 键
     * @return Set
     */
    Set<Object> sMembers(String key);

    /**
     * 是否为 Set 元素
     *
     * @param key   键
     * @param value Set 元素
     * @return 是否为 Set 元素
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 删除 Set 元素
     *
     * @param key    键
     * @param values Set 元素
     * @return 删除元素个数
     */
    Long sRemove(String key, Object... values);

    /**
     * 获取 Set 长度
     *
     * @param key 键
     * @return Set 长度
     */
    Long sSize(String key);

    /**
     * 添加 List 元素
     *
     * @param key   键
     * @param value List 元素
     * @return List 长度
     */
    Long lPush(String key, Object value);

    /**
     * 添加 List 元素并设置过期时间
     *
     * @param key   键
     * @param value List 元素
     * @param time  过期时间（秒）
     * @return List 长度
     */
    Long lPush(String key, Object value, long time);

    /**
     * 批量添加 List 元素
     *
     * @param key    键
     * @param values List 元素
     * @return List 长度
     */
    Long lPush(String key, Object... values);

    /**
     * 批量添加 List 元素并设置过期时间
     *
     * @param key    键
     * @param time   过期时间（秒）
     * @param values List 元素
     * @return List 长度
     */
    Long lPush(String key, long time, Object... values);

    /**
     * 获取 List 索引位置元素
     *
     * @param key   键
     * @param index 索引位置，0 开始
     * @return List 元素
     */
    Object lIndex(String key, long index);

    /**
     * 获取 List 指定区间元素
     *
     * @param key   键
     * @param start 起始位置，由 0 开始，-1 表示最后一个，-2 表示倒数第二个，以此类推
     * @param end   终止位置
     * @return List 元素列表
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 移除 List 元素
     *
     * @param key   键
     * @param count >0 从表头（左）删除 count 个 value，<0 反之，=0 删除所有 value
     * @param value List 元素
     * @return List 长度
     */
    Long lRemove(String key, long count, Object value);

    /**
     * 获取 List 长度
     *
     * @param key 键
     * @return List 长度
     */
    Long lSize(String key);
}
