package fr.eql.al35.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class WebClientConfig {

	@Value("${favorite.orderws.endpoint}")
	private String orderWSEndPoint;

	@Value("${favorite.productws.endpoint}")
	private String productWSEndPoint;

	@Value("${favorite.userws.endpoint}")
	private String userWSEndPoint;

	Builder webclientBuilder = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

	@Bean(name = "orderWebclient")
	WebClient webClientOrder() {
		return webclientBuilder
				.baseUrl(orderWSEndPoint)
				.build();
	}

	@Bean(name = "productWebclient")
	WebClient webClientProduct() {
		return webclientBuilder
				.baseUrl(productWSEndPoint)
				.build();
	}

	@Bean(name = "userWebclient")
	WebClient webClientUser() {
		return webclientBuilder
				.baseUrl(userWSEndPoint)
				.build();
	}
}
