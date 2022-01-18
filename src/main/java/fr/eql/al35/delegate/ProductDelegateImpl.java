package fr.eql.al35.delegate;

import java.util.ArrayList;
import java.util.List;

import fr.eql.al35.dto.OrderLineForProductWSDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.dto.ProductTypeDTO;
import fr.eql.al35.util.WebClientGenericResponse;

@Service
public class ProductDelegateImpl implements ProductDelegate {

	private final WebClient productWebclient;
	private static final String PRODUCT_ENDPOINT = "/clothes";

	@Autowired
	public ProductDelegateImpl(@Qualifier("productWebclient") WebClient productWebclient) {
		this.productWebclient = productWebclient;
	}

	/*
	 * GET :  "/clothes/available"
	 */
	@Override
	public List<ClothDTO> getAvailableProducts() {

		List<ClothDTO> clothes = WebClientGenericResponse.getListResponse(productWebclient,
														PRODUCT_ENDPOINT + "/available",
														new ClothDTO());
		clothes.forEach(c -> c.setProductType(new ProductTypeDTO()));
		return clothes;
	}

	/*
	 * GET :  "/clothes/{clothId}"
	 */
	@Override
	public ClothDTO getClothById(Integer clothId) {
		return WebClientGenericResponse.getResponse(productWebclient,
													PRODUCT_ENDPOINT  + "/" + clothId,
													new ClothDTO());
	}

	/*
	 * GET :  "/product-types"
	 */
	@Override
	public List<ProductTypeDTO> getAllProductType() {
		return WebClientGenericResponse.getListResponse(productWebclient,
														"/product-types",
														new ProductTypeDTO());
	}

	/*
	 * GET :  "/clothes/product-types/{productTypeName}"
	 */
	@Override
	public List<ClothDTO> getAllByProductType(String productTypeName) {
		return WebClientGenericResponse.getListResponse(productWebclient,
														PRODUCT_ENDPOINT + "/product-types/" + productTypeName,
														new ClothDTO());
	}

	/*
	 * GET :  "/designs"
	 */
	@Override
	public List<DesignDTO> getAllDesigns() {
		return WebClientGenericResponse.getListResponse(productWebclient,
														"/designs",
														new DesignDTO());
	}

	/*
	 * GET :  "/designs/{designId}"
	 */
	@Override
	public DesignDTO getDesignById(Integer designId) {
		return WebClientGenericResponse.getResponse(productWebclient,
				"/designs/" + designId,
				new DesignDTO());
	}

	/*
	 * POST :  "/clothes"
	 */
	@Override
	public ClothDTO saveCloth(ClothDTO cloth) {
		return WebClientGenericResponse.postResponse(productWebclient,
													 PRODUCT_ENDPOINT,
													 new ClothDTO());
	}

	/*
	 * PUT :  "/clothes"
	 */
	@Override
	public ClothDTO updateCloth(ClothDTO cloth) {
		return WebClientGenericResponse.putResponse(productWebclient,
													PRODUCT_ENDPOINT,
													new ClothDTO());
	}

	@Override
	public ArrayList<OrderLineForProductWSDTO> updateStocks(List<OrderLineForProductWSDTO> orderLines) {
		return WebClientGenericResponse.putResponse(productWebclient,
													"/stocks",
													new ArrayList<>());
	}

}
