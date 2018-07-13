package cn.zcp.wechartapi.business.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class StaticCache implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(StaticCache.class);


    @Override
    public void run(String... args) throws Exception {
        loadCaches();
    }

    public void loadCaches(){}

}
