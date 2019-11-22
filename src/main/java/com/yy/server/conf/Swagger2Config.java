package com.yy.server.conf;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ChenXiangpeng
 * @ClassName Swagger2Config
 * @date：2019/11/13
 * @version: V1.0.0
 * @description：
 */
@Profile({"dev", "yyy"})
@EnableSwagger2
@Configuration
@Component
@PropertySource(value = "classpath:application.properties",encoding = "UTF-8")
public class Swagger2Config {
    @Value("${application.name}")
    private String applicationName;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //withMethodAnnotation(ApiOperation.class)
                .apis(RequestHandlerSelectors.basePackage("com.yy.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title(applicationName)
                .description("swagguuer-bootstrap-ui-demo RESTful APIs")
                .termsOfServiceUrl("http://www.xx.com")
                .version("1.0")
                .build();
    }
}
