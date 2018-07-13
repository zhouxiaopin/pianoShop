package cn.zcp.wechartapi.business.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component()
@ConfigurationProperties(prefix = "redis")
@PropertySource("classpath:properties/redis.properties")
public class RedisProperty {
    public static class Pool{
        private int maxIdle;
        private int minIdle;
        private int maxActive;
        private long maxWait;
        private boolean testOnBorrow;

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public long getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(long maxWait) {
            this.maxWait = maxWait;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }
    }
    private String host;
    private int port;
    private String pass;
    private int database0;
    private int database1;
    private int database2;
    private int database3;
    private int database4;
    private int database5;
    private int database6;
    private int database7;
    private int database8;
    private int database9;
    private int database10;
    private int database11;
    private int database12;
    private int database13;
    private int database14;
    private int database15;
    private boolean usePool;
    private int timeout;
    private Pool pool;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getDatabase0() {
        return database0;
    }

    public void setDatabase0(int database0) {
        this.database0 = database0;
    }

    public int getDatabase1() {
        return database1;
    }

    public void setDatabase1(int database1) {
        this.database1 = database1;
    }

    public int getDatabase2() {
        return database2;
    }

    public void setDatabase2(int database2) {
        this.database2 = database2;
    }

    public int getDatabase3() {
        return database3;
    }

    public void setDatabase3(int database3) {
        this.database3 = database3;
    }

    public int getDatabase4() {
        return database4;
    }

    public void setDatabase4(int database4) {
        this.database4 = database4;
    }

    public int getDatabase5() {
        return database5;
    }

    public void setDatabase5(int database5) {
        this.database5 = database5;
    }

    public int getDatabase6() {
        return database6;
    }

    public void setDatabase6(int database6) {
        this.database6 = database6;
    }

    public int getDatabase7() {
        return database7;
    }

    public void setDatabase7(int database7) {
        this.database7 = database7;
    }

    public int getDatabase8() {
        return database8;
    }

    public void setDatabase8(int database8) {
        this.database8 = database8;
    }

    public int getDatabase9() {
        return database9;
    }

    public void setDatabase9(int database9) {
        this.database9 = database9;
    }

    public int getDatabase10() {
        return database10;
    }

    public void setDatabase10(int database10) {
        this.database10 = database10;
    }

    public int getDatabase11() {
        return database11;
    }

    public void setDatabase11(int database11) {
        this.database11 = database11;
    }

    public int getDatabase12() {
        return database12;
    }

    public void setDatabase12(int database12) {
        this.database12 = database12;
    }

    public int getDatabase13() {
        return database13;
    }

    public void setDatabase13(int database13) {
        this.database13 = database13;
    }

    public int getDatabase14() {
        return database14;
    }

    public void setDatabase14(int database14) {
        this.database14 = database14;
    }

    public int getDatabase15() {
        return database15;
    }

    public void setDatabase15(int database15) {
        this.database15 = database15;
    }

    public boolean isUsePool() {
        return usePool;
    }

    public void setUsePool(boolean usePool) {
        this.usePool = usePool;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }
}
