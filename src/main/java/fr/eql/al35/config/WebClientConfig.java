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
		return buildWebclient(orderWSEndPoint);
	}

	@Bean(name = "productWebclient")
	WebClient webClientProduct() {
		return buildWebclient(productWSEndPoint);
	}

	@Bean(name = "userWebclient")
	WebClient webClientUser() {
		return buildWebclient(userWSEndPoint);
	}
	
	private WebClient buildWebclient(String endPoint) {
		return webclientBuilder
				.baseUrl(endPoint)
				.build();
	}
}
