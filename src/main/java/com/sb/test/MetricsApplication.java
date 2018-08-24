package com.sb.test;

import com.codahale.metrics.ConsoleReporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-metrics
 * @package: com.sb.test
 * @email: cy880708@163.com
 * @date: 2018/8/24 下午5:52
 * @mofified By:
 */
@SpringBootApplication
public class MetricsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MetricsApplication.class, args);
        // 启动Reporter
        ConsoleReporter reporter = ctx.getBean(ConsoleReporter.class);
        reporter.start(5, TimeUnit.SECONDS);
    }

}
