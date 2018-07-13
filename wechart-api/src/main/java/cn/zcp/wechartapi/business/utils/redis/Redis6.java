package cn.zcp.wechartapi.business.utils.redis;

import cn.zcp.wechartapi.base.utils.BaseRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Redis6 extends BaseRedisUtil {
    @Autowired
    @Qualifier("redisTemplate6")
    protected RedisTemplate<String, Object> redisTemplate;
    @Override
    protected RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}
