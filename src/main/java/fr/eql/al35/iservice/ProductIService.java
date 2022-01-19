package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.dto.*;

public interface ProductIService {

	List<ClothDTO> displayAllProducts();

	List<ClothDTO> displayAvailableProducts();

	List<ProductTypeDTO> displayAllCategories();

	List<ClothDTO> displayByProductType(String productTypeName);

	List<DesignDTO> displayAllDesign();

	ClothDTO updateProduct(Integer productId, ClothDTO product);

	void deleteProduct(Integer id);

	ClothDTO addProduct(ClothDTO product);

	ClothDTO displayProductById(Integer id);

	List<LocationDTO> displayAllLocations(String productTypeName);

	ProductTypeDTO getProductTypeByName(String productTypeName);

	ClothDTO updateStock(Integer stockId, Integer newQuantity, Integer productId);
}
