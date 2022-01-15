package fr.eql.al35.util;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientGenericResponse {

	public static <T> List<T> getListResponse(WebClient webclient, String endpoint, T t){
		return (List<T>) webclient.get()
				.uri(endpoint)
				.accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8)
				.retrieve()
				.bodyToFlux(t.getClass())
				.collectSortedList()
				.block();
	}

	public static <T> T getResponse(WebClient webclient, String endpoint, T t){	
		return (T) webclient.get()
				.uri(endpoint)
				.accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8)
				.retrieve()
				.bodyToMono(t.getClass())
				.block();
	}

	public static <T> T postResponse(WebClient webclient, String endpoint, T t){	
		return (T) webclient.post()
				.uri(endpoint)
				.body(Mono.just(t), t.getClass())
				.accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8)
				.retrieve()
				.bodyToMono(t.getClass())
				.block();
	}
	public static <T> T putResponse(WebClient webclient, String endpoint, T t){	
		return (T) webclient.put()
				.uri(endpoint)
				.body(Mono.just(t), t.getClass())
				.accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8)
				.retrieve()
				.bodyToMono(t.getClass())
				.block();
	}
	
	private WebClientGenericResponse() {
	}
}
