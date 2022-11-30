package com.lsm1998.football.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Knife4jConfig
{
    @Bean(value = "defaultApi2")
    public Docket defaultApi2()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("emos-football RESTful APIs")
                        .description("单体架构版")
                        .termsOfServiceUrl("http://www.lsm1998.com/")
                        .contact(new Contact("lsm1998", "", "487005831@qq.com"))
                        .version("1.0")
                        .build())
                // 分组名称
                .groupName("v1.0")
                .select()
                // RestController
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
