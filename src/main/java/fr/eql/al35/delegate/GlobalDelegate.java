package fr.eql.al35.delegate;

import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.dto.UserDTO;

import java.util.List;

public interface GlobalDelegate {

	void saveOrder(PurchaseOrderDTO order);

	List<PurchaseOrderDTO> getOrdersByUser(Integer userId);

	PurchaseOrderDTO getOrderById(Integer orderId);

    List<UserDTO> getAllUsers();

	UserDTO getUserById(Integer userId);
}
