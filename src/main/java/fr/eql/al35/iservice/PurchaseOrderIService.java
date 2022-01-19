package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.dto.StockDTO;

public interface PurchaseOrderIService {
	
	PurchaseOrderDTO createPurchaseOrder(Cart cart, PurchaseOrderDTO order);
	
	void savePurchaseOrder(PurchaseOrderDTO order);
	
	List<PurchaseOrderDTO> getAllByUserId(Integer userId);
	
	PurchaseOrderDTO getPurchaseOrderById(Integer id);
	
	List<PurchaseOrderDTO> getAllPurchaseOrders();

    List<StockDTO> updateStocks(List<StockDTO> stockDTOS);

}
