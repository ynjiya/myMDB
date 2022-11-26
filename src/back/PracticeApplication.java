package com.qwerty.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.Response;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

	@Bean
	public Docket api() {
		final List<Response> globalResponses = Arrays.asList(
				new ResponseBuilder()
						.code("200")
						.description("OK")
						.build(),
				new ResponseBuilder()
						.code("401")
						.description("Unauthorized")
						.build(),
				new ResponseBuilder()
						.code("403")
						.description("Forbidden")
						.build(),
				new ResponseBuilder()
						.code("404")
						.description("Not Found")
						.build());

		final List<Response> globalResponsesPOST = Arrays.asList(
				new ResponseBuilder()
						.code("200")
						.description("OK")
						.build(),
				new ResponseBuilder()
						.code("401")
						.description("Unauthorized")
						.build(),
				new ResponseBuilder()
						.code("403")
						.description("Forbidden")
						.build());


		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//				.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())

				.build().enableUrlTemplating(true)
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalResponses)
				.globalResponses(HttpMethod.DELETE, globalResponses)
				.globalResponses(HttpMethod.PUT, globalResponses)
				.globalResponses(HttpMethod.PATCH, globalResponses)
				.globalResponses(HttpMethod.POST, globalResponsesPOST)

				;
	}
}
