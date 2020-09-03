package com.whkj.project.common.configure;

import com.whkj.project.common.properties.BlogProperties;
import com.whkj.project.common.properties.SwitchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * springBoot 容器启动自动加载后自动监听
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MyCommand implements CommandLineRunner {

    @Value("${server.port}")
    private String port;

    private final BlogProperties blogProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("启动项目打开默认浏览器！");



        InetAddress address = InetAddress.getLocalHost();
        String url = String.format("http://%s:%s", address.getHostAddress(), port);
        blogProperties.isAutoOpenBrowser();



        System.out.println("开始自动加载指定的页面");
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8080/index");//可以指定自己的路径
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
