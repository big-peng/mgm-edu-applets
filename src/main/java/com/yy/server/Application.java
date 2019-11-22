package com.yy.server;//特别注意，下面的是 tk.MapperScan

import com.yy.server.filter.XssFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;
import java.io.File;


@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.yy.server.mapper")
@SpringBootApplication(scanBasePackages = "com.yy.server",exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    @Value("${tmp.upload.path}")
    private String updateTempPath;

    @Value("${global.file.max.size}")
    private String fileMaxSize;

    private Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("服务启动完成!");
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(updateTempPath);
        //文件最大
        factory.setMaxFileSize(fileMaxSize); //KB,MB
        /// 设置总上传数据总大小
        //factory.setMaxRequestSize("102400KB");
        File tempFile = new File(updateTempPath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        return factory.createMultipartConfig();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        multipartResolver.setResolveLazily(true);
        multipartResolver.setMaxUploadSize(Integer.parseInt(fileMaxSize));
        multipartResolver.setMaxInMemorySize(Integer.parseInt(fileMaxSize));
        return multipartResolver;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationXss() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("XssFilter");
        registration.setOrder(2);
        return registration;
    }





}
