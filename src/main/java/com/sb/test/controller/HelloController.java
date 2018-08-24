package com.sb.test.controller;

import com.codahale.metrics.*;
import com.sb.test.config.ListManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-metrics
 * @package: com.sb.test.controller
 * @email: cy880708@163.com
 * @date: 2018/8/24 下午6:04
 * @mofified By:
 */
@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private Meter requestMeter;

    @Autowired
    private Histogram responseSizes;

    @Autowired
    private Counter pendingJobs;

    @Autowired
    private Timer responses;

    @Autowired
    private ListManager listManager;

    @Bean
    public Meter requestMeter(MetricRegistry metrics) {
        return metrics.meter("request");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld() {

        requestMeter.mark();

        pendingJobs.inc();

        responseSizes.update(new Random().nextInt(10));

        final Timer.Context context = responses.time();

        try {
            return "Hello World";
        } finally {
            context.stop();
        }
    }

}
