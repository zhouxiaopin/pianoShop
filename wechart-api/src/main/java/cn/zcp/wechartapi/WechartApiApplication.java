package cn.zcp.wechartapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//扫描 mybatis dao 包路径
@MapperScan(basePackages = {"cn.zcp.wechart.base.mapper", "cn.zcp.wechart.business.mapper"})
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"cn.zcp"})
@EnableTransactionManagement
public class WechartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechartApiApplication.class, args);
	}
}
