package fr.eql.al35.delegate;

import java.util.List;

import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.dto.OrderLineForProductWSDTO;
import fr.eql.al35.dto.ProductTypeDTO;
import fr.eql.al35.dto.StockDTO;

public interface ProductDelegate {

	List<ClothDTO> getAllProducts();

	List<ClothDTO> getAvailableProducts();

	ClothDTO getClothById(Integer clothId);

	List<ProductTypeDTO> getAllProductType();

	List<ClothDTO> getAllByProductType(String productTypeName);

	List<DesignDTO> getAllDesigns();

	DesignDTO getDesignById(Integer designId);

	ClothDTO saveCloth(ClothDTO cloth);

	ClothDTO updateCloth(ClothDTO cloth);

    List<StockDTO> updateStocks(List<OrderLineForProductWSDTO> orderLines);

}
