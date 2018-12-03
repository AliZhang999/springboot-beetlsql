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
    public Docket homePage_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("首页")
                .apiInfo(apiInfo("首页","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/homePage/**"))
                .build();
    }

    @Bean
    public Docket subjectOverView_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("学科分析")
                .apiInfo(apiInfo("学科分析","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/subjectAnalysis/**"))
                .build();
    }

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
    public Docket majorInfo_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("专业画像")
                .apiInfo(apiInfo("专业画像","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/majorInfo/**"))
                .build();
    }

    @Bean
    public Docket schoolInfo_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("学校画像")
                .apiInfo(apiInfo("学校画像","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/schoolInfo/**"))
                .build();
    }

    /*@Bean
    public Docket teachingStaff_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("师资队伍")
                .apiInfo(apiInfo("师资队伍","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/teachingStaff/**"))
                .build();
    }*/

    @Bean
    public Docket teachingStaff_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("师资队伍")
                .apiInfo(apiInfo("师资队伍","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/dynamicAnalysis/szdw/**"))
                .build();
    }

    @Bean
    public Docket personnelTraining_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("人才培养")
                .apiInfo(apiInfo("人才培养","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/personnelTraining/**"))
                .build();
    }

    @Bean
    public Docket platformBuilding_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("平台建设")
                .apiInfo(apiInfo("平台建设","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/platformBuilding/**"))
                .build();
    }

    @Bean
    public Docket researchAward_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("科研获奖")
                .apiInfo(apiInfo("科研获奖","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/researchAward/**"))
                .build();
    }

    @Bean
    public Docket subjectResearch_api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("学科研究")
                .apiInfo(apiInfo("学科研究","1.0",""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/subjectResearch/**"))
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
