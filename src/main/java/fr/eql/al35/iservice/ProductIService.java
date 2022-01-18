package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.dto.ProductTypeDTO;

public interface ProductIService {

	List<ClothDTO> displayAllProducts();

	List<ClothDTO> displayAvailableProducts();

	List<ProductTypeDTO> displayAllCategories();

	List<ClothDTO> displayByProductType(ProductTypeDTO productType);

	List<DesignDTO> displayAllDesign();

	ClothDTO upDate(Integer id, ClothDTO product);

	void setDeleteProduct(Integer id);

	ClothDTO addProduct(ClothDTO product);

	ClothDTO displayProductById(Integer id);

}
