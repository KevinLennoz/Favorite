package fr.eql.al35.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.ProductDelegate;
import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.dto.LocationDTO;
import fr.eql.al35.dto.PhotoDTO;
import fr.eql.al35.dto.ProductTypeDTO;
import fr.eql.al35.dto.StockDTO;
import fr.eql.al35.iservice.ProductIService;

@Service
public class ProductService implements ProductIService {

	private final ProductDelegate productDelegate;

	@Autowired
	public ProductService(ProductDelegate productDelegate) {
		this.productDelegate = productDelegate;
	}

	@Override
	public List<ClothDTO> displayAvailableProducts() {
		List<ClothDTO> clothes = productDelegate.getAvailableProducts();
		clothes.stream().forEach(this::updateAvailability);
		return clothes;
	}

	@Override
	public ClothDTO displayProductById(Integer id) {
		ClothDTO cloth = productDelegate.getClothById(id);
		updateAvailability(cloth);
		return cloth;
	}

	@Override
	public List<ProductTypeDTO> displayAllCategories() {
		return productDelegate.getAllProductType();
	}

	@Override
	public List<ClothDTO> displayByProductType(String productTypeName) {
		List<ClothDTO> clothes = productDelegate.getAllByProductType(productTypeName);
		clothes.stream().forEach(this::updateAvailability);
		return clothes;
	}

	@Override
	public List<LocationDTO> displayAllLocations(String productTypeName) {
		ProductTypeDTO type = getProductTypeByName(productTypeName);
		return type != null ? type.getLocations() : Collections.emptyList();
	}

	@Override
	public ProductTypeDTO getProductTypeByName(String productTypeName) {
		Optional<ProductTypeDTO> type = productDelegate.getAllProductType()
				.stream()
				.filter(t -> t.getName().equals(productTypeName))
				.findFirst();

		if (type.isPresent()) {
			return type.get();
		}
		return null;
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

	private void updateAvailability(ClothDTO c) {
		List<StockDTO> stock = c.getStocks();
		c.setAvailable(stock.stream().anyMatch(s -> s.getQuantity() > 0));
	}
}
