package cn.zcp.bgmange.business.utils.redis;

import cn.zcp.bgmange.base.utils.BaseRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Redis15 extends BaseRedisUtil {
    @Autowired
    @Qualifier("redisTemplate15")
    protected RedisTemplate<String, Object> redisTemplate;
    @Override
    protected RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}
