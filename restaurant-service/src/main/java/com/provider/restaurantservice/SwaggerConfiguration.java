package com.provider.restaurantservice;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2git 
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Restaurant-Service").select()
                .paths(paths()).build().apiInfo(apiInfo());
    }

    private Predicate<String> paths() {
        return or(regex("/provider.*"));
    }

    private ApiInfo apiInfo() {
            return new ApiInfo("Restaurant-Service", "This is demo Restaurant-Service",
                "1.0", "",
                new Contact("", "", ""), "",
                "", Collections.emptyList());
    }
}
