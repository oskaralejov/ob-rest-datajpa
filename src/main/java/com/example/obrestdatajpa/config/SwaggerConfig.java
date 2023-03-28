package com.example.obrestdatajpa.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * configuracion Swagger para la generacion de documentacion de la API REST
 *
 * HTML: http://localhost:8081/swagger-ui/
 */

@Configuration
@EnableSwagger2
//@EnableWebMvc

public class SwaggerConfig {

    @Bean

    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiDetails(){

        return new ApiInfo(
                "Spring boot book API REST",
                "library apis rest docs",
                "1.0",
                "http://www.google.com",
                new Contact("Oskar","http://www.google.com","oskar@gmail.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());

    }

}
