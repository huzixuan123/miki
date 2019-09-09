package cn.miki.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisTools {
    private static final Logger logger = LoggerFactory.getLogger(RedisTools.class);

    private RedisTemplate redisTemplate;

    public RedisTools(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
/** -------------------key相关操作--------------------- */

    /**
    * 删除key
    *
    * @param key
    */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
    * 批量删除key
    *
    * @param keys
    */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
    * 是否存在key
    *
    * @param key
    * @return
    */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
    * 设置过期时间
    *
    * @param key
    * @param timeout
    * @param unit
    * @return
    */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
    * 设置过期时间
    *
    * @param key
    * @param date
    * @return
    */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
    * 查找匹配的key
    *
    * @param pattern
    * @return
    */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
     }

    /**
    * 移除 key 的过期时间，key 将持久保持
    *
    * @param key
    * @return
    */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
    * 返回 key 的剩余的过期时间
    *
    * @param key
    * @return
    */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }


/** -------------------string相关操作--------------------- */

    /**
    * 设置指定 key 的值
    *
    * @param key
    * @param value
    */
    public<T> void set(String key, T value) {
        logger.info("准备写入redi数据，key:{},value:{}", key, value);
        try {
            redisTemplate.opsForValue().set(key, value);
            logger.info("数据缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("缓存数据失败", e);
        }
    }

    /**
    * 获取指定 key 的值
    *
    * @param key
    * @return
    */
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

/** ------------------------list相关操作---------------------------- */

    /**
    * 通过索引获取列表中的元素
    *
    * @param key
    * @param index
    * @return
    */
    public <T> T lIndex(String key, long index) {
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
    * 获取列表指定范围内的元素
    *
    * @param key
    * @param start 开始位置, 0是开始位置
    * @param end   结束位置, -1返回所有
    * @return
    */
    public List<String> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
    * 移出并获取列表的第一个元素
    *
    * @param key
    * @return 删除的元素
    */
    public <T> T lLeftPop(String key) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
    * 移除并获取列表最后一个元素
    *
    * @param key
    * @return 删除的元素
    */
    public <T> T lRightPop(String key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    /**
    * /**
    * 删除集合中值等于value得元素
    *
    * @param key
    * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
    *              index<0, 从尾部开始删除第一个值等于value的元素;
    * @param value
    * @return
    */
    public Long lRemove(String key, long index, String value) {
        return redisTemplate.opsForList().remove(key, index, value);
    }

    /**
    * 获取列表长度
    *
    * @param key
    * @return
    */
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

/** --------------------set相关操作-------------------------- */

    /**
    * set添加元素
    *
    * @param key
    * @param values
    * @return
    */
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
    * set移除元素
    *
    * @param key
    * @param values
    * @return
    */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
    * 移除并返回集合的一个随机元素
    *
    * @param key
    * @return
    */
    public <T> T sPop(String key) {
        return (T) redisTemplate.opsForSet().pop(key);
    }
}
