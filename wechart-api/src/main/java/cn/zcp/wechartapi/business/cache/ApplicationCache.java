package cn.zcp.wechartapi.business.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
@Order(2)
public class ApplicationCache implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
    @Autowired
    private ServletContext servletContext;
    @Override
    public void run(String... args) throws Exception {
        loadCaches(args);
    }

    private void loadCaches(String... args){
    }


}
