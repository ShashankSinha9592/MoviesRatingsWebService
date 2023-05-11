package com.movies.MoviesRatingsWebService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Movies Ratings API", version = "1.0", description = "Movies Ratings Information"),
		servers = {
				@Server(url = "/", description = "Movies Ratings Swagger URL")
		}
)
@SpringBootApplication
public class MoviesRatingsWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesRatingsWebServiceApplication.class, args);
	}

}
