package com.kaansenada.publisherprototype;

import com.kaansenada.publisherprototype.repository.BookRepository;
import com.kaansenada.publisherprototype.service.impl.BookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Publisher Prototype API",description = "API for Publisher Prototype",version = "1.0"
		)
)
public class PublisherPrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherPrototypeApplication.class, args);
	}

}
