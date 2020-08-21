package com.whkj.project.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 *  全局配置获取属性文件
 */

@Data
@SpringBootConfiguration//标注该文件为配置类，以Bean的方式注入到spring中
@PropertySource(value = {"classpath:blog.properties"})//获取其它配置文件的属性值
@ConfigurationProperties(prefix = "blog")//配置类中的名字必须以blog开头，添加这个注解需要在pom里面添加配置文件自动映射的maven
public class BlogProperties {

    private ShiroProperties shiro = new ShiroProperties();
}
