package com.ali.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootConfiguration
@EnableSwagger2
public class BeetlsqlModuleSwaggerConfig {
    @Bean
    public Docket subjectDataView_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("学科综合分析")
                .apiInfo(apiInfo("学科数据总览","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/subjectDataView/**"))
                .build();
    }

    @Bean
    public Docket teacherInfo_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("教师画像")
                .apiInfo(apiInfo("教师画像","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/teacherInfo/**"))
                .build();
    }

    @Bean
    public Docket teachingStaff_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("师资队伍")
                .apiInfo(apiInfo("师资队伍","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/teachingStaff/**"))
                .build();
    }

    private ApiInfo apiInfo(String title,String version,String desc){
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .description(desc)
                .build();
    }
}
