package fr.eql.al35.delegate;

import java.util.ArrayList;
import java.util.List;

import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.dto.OrderLineForProductWSDTO;
import fr.eql.al35.dto.ProductTypeDTO;

public interface ProductDelegate {

	List<ClothDTO> getAvailableProducts();

	ClothDTO getClothById(Integer clothId);

	List<ProductTypeDTO> getAllProductType();

	List<ClothDTO> getAllByProductType(String productTypeName);

	List<DesignDTO> getAllDesigns();

	DesignDTO getDesignById(Integer designId);

	ClothDTO saveCloth(ClothDTO cloth);

	ClothDTO updateCloth(ClothDTO cloth);

    ArrayList<OrderLineForProductWSDTO> updateStocks(List<OrderLineForProductWSDTO> orderLines);

}
