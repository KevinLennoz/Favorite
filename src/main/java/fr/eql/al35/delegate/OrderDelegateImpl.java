package fr.eql.al35.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.util.WebClientGenericResponse;

@Service
public class OrderDelegateImpl implements OrderDelegate {
	
	private WebClient orderWebclient;
	private static final String ORDER_ENDPOINT = "/orders";
	private static final String USER_ENDPOINT = "/users";

	@Autowired
	public OrderDelegateImpl(@Qualifier("orderWebclient") WebClient orderWebclient) {
		this.orderWebclient = orderWebclient;
	}
	
	/*
	 * GET :  "/users/{userId}/orders"
	 */
	@Override
	public List<PurchaseOrderDTO> getAllOrdersByUserId(Integer userId) {
		return WebClientGenericResponse.getListResponse(orderWebclient,
														USER_ENDPOINT + "/" + userId + ORDER_ENDPOINT,
														new PurchaseOrderDTO());
	}
	
	/*
	 * GET :  "/orders"
	 */
	@Override
	public List<PurchaseOrderDTO> getAllOrders() {
		return WebClientGenericResponse.getListResponse(orderWebclient,
														ORDER_ENDPOINT,
														new PurchaseOrderDTO());
	}
	
	/*
	 * GET :  "/orders/{id}"
	 */
	@Override
	public PurchaseOrderDTO getOrderById(Integer id) {
		return WebClientGenericResponse.getResponse(orderWebclient,
													ORDER_ENDPOINT + "/" + id,
													new PurchaseOrderDTO());
	}

	/*
	 * POST :  "/orders"
	 */
	@Override
	public PurchaseOrderDTO saveOrder(PurchaseOrderDTO order) {
		return WebClientGenericResponse.postResponse(orderWebclient,
													 ORDER_ENDPOINT,
													 order);
	}
}
