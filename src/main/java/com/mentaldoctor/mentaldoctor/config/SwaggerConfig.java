package com.mentaldoctor.mentaldoctor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.mentaldoctor.mentaldoctor"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(new ApiInfoBuilder()
                    .description("MentalDocotr接口文档")
                    .contact(new Contact("", "", ""))
                    .version("1.0.0")
                    .title("Api文档")
                    .build());
    }
}
