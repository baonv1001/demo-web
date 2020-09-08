package com.webapp.webdemo.config;

import com.webapp.webdemo.constants.CommonConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket restApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(Collections.singletonList(
                        new ParameterBuilder()
                        .name(CommonConstants.HeaderConstants.APP_TOKEN)
                        .modelRef(new ModelRef("string"))
                        .parameterType(CommonConstants.HeaderConstants.HEADER)
                        .required(true)
                        .hidden(true)
                        .defaultValue(CommonConstants.HeaderConstants.BEARER_TOKEN)
                        .build()
                ))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
