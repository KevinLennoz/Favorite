package fr.eql.al35.util;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebclientGenericResponse {
	
	public static <T> List<T> getListResponse(WebClient webclient, String endpoint, T t){
		return webclient.get()
				.uri(endpoint)
				.accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8)
				.retrieve()
				.bodyToFlux((Class<T>) t)
				.collectSortedList()
				.block();
	}

	public static <T> T getResponse(WebClient webclient, String endpoint, T t){	
		return webclient.get()
				.uri(endpoint)
				.accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8)
				.retrieve()
				.bodyToMono((Class<T>) t)
				.block();
	}
}
