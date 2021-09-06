package ipron.cloud.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("IPRON Cloud - 전화")
                .description("전화 API 문서입니다.")
                .license("bridgetec")
                .licenseUrl("http://www.bridgetec.co.kr/")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1")
                .build()
        ;
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    @Bean
    public Docket swaggerBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                // Request Content-Type, Response Content-Type에 대한 설정입니다.(선택)
                .consumes(getConsumeContentTypes()).produces(getProduceContentTypes())


                .apiInfo(swaggerInfo())
                .select()

                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("ipron.cloud.web.apicall.controller"))

                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/v1/**"))
                .build()
                .useDefaultResponseMessages(false)
        ;
    }

}


