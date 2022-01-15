package fr.eql.al35.util;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientGenericResponse {
	
	private static final Logger logger = LoggerFactory.getLogger(WebClientGenericResponse.class);

	public static <T> List<T> getListResponse(WebClient webclient, String endpoint, T t){
		try {
			return (List<T>) webclient.get()
					.uri(endpoint)
					.accept(MediaType.APPLICATION_JSON)
					.acceptCharset(StandardCharsets.UTF_8)
					.retrieve()
					.bodyToFlux(t.getClass())
					.collectSortedList()
					.block();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return Collections.emptyList();
	}

	public static <T> T getResponse(WebClient webclient, String endpoint, T t){	
		try {
			return (T) webclient.get()
					.uri(endpoint)
					.accept(MediaType.APPLICATION_JSON)
					.acceptCharset(StandardCharsets.UTF_8)
					.retrieve()
					.bodyToMono(t.getClass())
					.block();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	public static <T> T postResponse(WebClient webclient, String endpoint, T t){	
		try {
			return (T) webclient.post()
					.uri(endpoint)
					.body(Mono.just(t), t.getClass())
					.accept(MediaType.APPLICATION_JSON)
					.acceptCharset(StandardCharsets.UTF_8)
					.retrieve()
					.bodyToMono(t.getClass())
					.block();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}
	public static <T> T putResponse(WebClient webclient, String endpoint, T t){	
		try {
			return (T) webclient.put()
					.uri(endpoint)
					.body(Mono.just(t), t.getClass())
					.accept(MediaType.APPLICATION_JSON)
					.acceptCharset(StandardCharsets.UTF_8)
					.retrieve()
					.bodyToMono(t.getClass())
					.block();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}
	
	private WebClientGenericResponse() {
	}
}
