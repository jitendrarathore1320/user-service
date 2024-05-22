package com.advantal.configuration;
//package com.advantal.configuration;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Predicate;
//
//import javax.annotation.RegEx;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.util.Predicates;
//import org.springframework.web.bind.annotation.RestController;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//	private ApiKey apiKey() {
//		return new ApiKey("JWT", "Authorization", "header");
//	}
//
//	private SecurityContext securityContext() {
//		return SecurityContext.builder().securityReferences(defaultAuth()).build();
//	}
////
//	private List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//	}
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
////				.apis(RequestHandlerSelectors.basePackage("com.advantal.userService.controller"))
//				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//				.paths(Predicate.not(PathSelectors.regex("/error.*")))
//				.build();
//	}
//
//	private ApiInfo apiInfo() {
//
//		return new ApiInfoBuilder().title("AMWAL ADMIN SERVICE").description("Amwal Admin Service Management Rest Api")
//				.contact(new Contact("Advantal", "https://advantaltechnologies.com/", "advantal@gmail.com"))
//				.license("Apache Tomcat").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
//				.build();
//	}
//	
//
//}
