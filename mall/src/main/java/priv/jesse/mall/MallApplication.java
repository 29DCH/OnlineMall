package priv.jesse.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import priv.jesse.mall.handler.SpiderHandler;

import javax.annotation.PostConstruct;


/**
 * 程序启动入口
 *
 * @ServletComponentScan 设置启动时spring能够扫描到我们自己编写的servlet和filter, 用于Druid监控
 * @MapperScan("com.imlaidian.springbootdemo.dao") 扫描mybatis Mapper接口
 * @EnableScheduling 启用定时任务
 * @EnableTransactionManagement 开启事务
 *
 * @author LJQ
 */
@ServletComponentScan
@EnableConfigurationProperties
@EnableTransactionManagement
@SpringBootApplication
public class MallApplication {
//	@Autowired
//	private SpiderHandler spiderHandler;
	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}
//	@PostConstruct
//	public void task() {
//		spiderHandler.spiderData();
//	}
}
