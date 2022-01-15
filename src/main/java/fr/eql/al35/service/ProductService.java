package fr.eql.al35.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.ProductDelegate;
import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.dto.PhotoDTO;
import fr.eql.al35.dto.ProductTypeDTO;
import fr.eql.al35.iservice.ProductIService;

@Service
public class ProductService implements ProductIService {

	private final ProductDelegate productDelegate;

	@Autowired
	public ProductService(ProductDelegate productDelegate) {
		this.productDelegate = productDelegate;
	}

	@Override
	public List<ClothDTO> displayAllProducts() {
		return productDelegate.getAllProducts();
	}

	@Override
	public List<ClothDTO> displayAvailableProducts() {
		return productDelegate.getAvailableProducts();
	}

	@Override
	public ClothDTO displayProductById(Integer id) {
		return productDelegate.getClothById(id);
	}

	@Override
	public List<ProductTypeDTO> displayAllCategories() {
		return productDelegate.getAllProductType();
	}

	@Override
	public List<ClothDTO> displayByProductType(ProductTypeDTO productType) {
		return productDelegate.getAllByProductType(productType.getName());
	}

	@Override
	public List<DesignDTO> displayAllDesign() {
		return productDelegate.getAllDesigns();
	}

	@Override
	public ClothDTO upDate(Integer id, ClothDTO cloth) {
		cloth.setId(id);
		cloth.setRefCreationDate(LocalDateTime.parse(LocalDateTime.now().toString(), 
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		return productDelegate.saveCloth(cloth);
	}

	@Override
	public void setDeleteProduct(Integer id) {
		ClothDTO cloth = displayProductById(id);
		if(cloth != null) {
			cloth.setRefDeletionDate(LocalDateTime.parse(LocalDateTime.now().toString(), 
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		}
	}

	@Override
	public ClothDTO addProduct(ClothDTO cloth) {
		cloth.setRefCreationDate(LocalDateTime.parse(LocalDateTime.now().toString(), 
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		List<PhotoDTO> photos = new ArrayList<>();
		PhotoDTO photoPantalon = new PhotoDTO();
		photoPantalon.setPath("PANTALON_BEIGE_1.jpg");
		photoPantalon.setDescription("PANTALON_BEIGE_1");
		photos.add(photoPantalon);
		cloth.setPhotos(photos);
		return productDelegate.saveCloth(cloth);
	}
}
