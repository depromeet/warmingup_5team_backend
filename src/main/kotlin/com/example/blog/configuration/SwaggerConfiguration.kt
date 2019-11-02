package com.example.blog.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    fun swaggerApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.example.blog.controller"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false) // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }

    private fun swaggerInfo(): ApiInfo {
        return ApiInfoBuilder().title("어디있냥 API 목록")
            .description("어디있냥의 API 목록입니다.")
            .version("1")
            .build()
    }
}