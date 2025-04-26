package com.Demo.CloudVendorRESTAPI;

//import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.Demo.CloudVendorRESTAPI")
public class CloudVendorRestapiApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloudVendorRestapiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Cloud Vendor REST API")
						.version("1.0")
						.description("API documentation for Cloud Vendor REST API")
						.contact(new Contact()
								.name("Jagadeeswar Reddy S")
								.email("suravaramjagadeeswarreddy@gmail.com")
								.url("https://myportfolio0539.netlify.app/")))
						.servers(List.of(
								new Server().url("http://localhost:8080").description("Local Server")
						));
	}



}
