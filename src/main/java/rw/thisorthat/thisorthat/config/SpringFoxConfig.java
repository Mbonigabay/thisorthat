package rw.thisorthat.thisorthat.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("rw.thisorthat"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    // private ApiKey apiKey() {
    //     return new ApiKey("JWT", "Authorization", "header");
    // }

    // private SecurityContext securityContext() {
    //     return SecurityContext.builder().securityReferences(defaultAuth()).build();
    // }

    // List<SecurityReference> defaultAuth() {
    //     AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    //     AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    //     authorizationScopes[0] = authorizationScope;
    //     return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    // }

    private ApiInfo apiInfo() {
        return new ApiInfo("TOT REST API",
            "A portal to help you choose the best service for you.",
            "1.0",
            "Terms of service",
            new Contact("Yusuf Mbonigaba", "www.tot.rw", "mbonigabay@gmail.com"),
            "License of API",
            "API license URL",
            Collections.emptyList());
      }
    
}