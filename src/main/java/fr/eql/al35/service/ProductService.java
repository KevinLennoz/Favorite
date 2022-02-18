package fr.eql.al35.service;

import java.time.LocalDateTime;
import java.util.*;


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
	public List<ClothDTO> displayAllProducts() {
		List<ClothDTO> clothes = productDelegate.getAllProducts();
		for (ClothDTO cloth : clothes) {
			Integer totalInStock = 0;
			for (StockDTO stock : cloth.getStocks()) {
				totalInStock += stock.getQuantity();
				cloth.setQuantityInStock(totalInStock);
			}
		}
		return clothes;
	}

	@Override
	public List<ClothDTO> displayAvailableProducts() {
		List<ClothDTO> clothes = productDelegate.getAvailableProducts();
		clothes.forEach(this::updateAvailability);
		return clothes;
	}

	@Override
	public ClothDTO displayProductById(Integer id) {
		ClothDTO cloth = productDelegate.getClothById(id);
		updateAvailability(cloth);
		Integer totalInStock = 0;
		for (StockDTO stock : cloth.getStocks()) {
			totalInStock += stock.getQuantity();
			cloth.setQuantityInStock(totalInStock);
		}
		return cloth;
	}

	@Override
	public List<ProductTypeDTO> displayAllCategories() {
		return productDelegate.getAllProductType();
	}

	@Override
	public List<ClothDTO> displayByProductType(String productTypeName) {
		List<ClothDTO> clothes = productDelegate.getAllByProductType(productTypeName);
		clothes.forEach(this::updateAvailability);
		return clothes;
	}

	@Override
	public List<ClothDTO> displayByProductTypeIgnoringCase(String productTypeName) {
		String category = "";
		switch (productTypeName.toLowerCase()) {
		case "pull":
			category = "Pulls";
			break;
		case "chemisier":
		case "chemise":
			category = "Chemises";
			break;
		case "pantalon":
			category = "Pantalons";
			break;
		case "manteaux":
		case "veste":
			category = "Manteaux & Vestes";
			break;
		case "tshirt":
			category = "T-Shirts";
			break;
		default:
			break;
		}

		List<ClothDTO> clothes = productDelegate.getAllByProductType(category);
		clothes.forEach(this::updateAvailability);
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

		return type.orElse(null);
	}

	@Override
	public ClothDTO updateStock(Integer stockId, Integer newQuantity, Integer productId) {
		ClothDTO updatedCloth = productDelegate.getClothById(productId);
		updatedCloth.getStocks().forEach(stock -> {
			addClothWithIdToStock(stock, productId);
			if (Objects.equals(stock.getId(), stockId)) {
				stock.setQuantity(stock.getQuantity() + newQuantity);
			}
		});
		productDelegate.updateStocks(updatedCloth.getStocks());
		return updatedCloth;
	}

	@Override
	public List<DesignDTO> displayAllDesign() {
		return productDelegate.getAllDesigns();
	}

	@Override
	public ClothDTO updateProduct(Integer productId, ClothDTO updatedCloth) {
		updatedCloth.setPhotos(productDelegate.getClothById(productId).getPhotos());
		updatedCloth.setId(productId);
		updatedCloth.setRefCreationDate(LocalDateTime.now());
		return productDelegate.updateCloth(updatedCloth);
	}

	@Override
	public void deleteProduct(Integer id) {
		ClothDTO cloth = displayProductById(id);
		if(cloth != null) {
			cloth.setRefDeletionDate(LocalDateTime.now());
		}
		productDelegate.updateCloth(cloth);
	}

	@Override
	public ClothDTO addProduct(ClothDTO cloth) {
		cloth.setRefCreationDate(LocalDateTime.now());
		List<PhotoDTO> photos = new ArrayList<>();
		PhotoDTO photoPantalon = new PhotoDTO();
		photoPantalon.setPath("PANTALON_BEIGE_1.jpg");
		photoPantalon.setDescription("PANTALON_BEIGE_1");
		photos.add(photoPantalon);
		cloth.setPhotos(photos);
		ClothDTO createdCloth = productDelegate.saveCloth(cloth);
		createdCloth.getStocks().forEach(stock -> addClothWithIdToStock(stock, createdCloth.getId()));
		productDelegate.updateStocks(createdCloth.getStocks());
		return createdCloth;
	}

	private void updateAvailability(ClothDTO c) {
		List<StockDTO> stock = c.getStocks();
		c.setAvailable(stock.stream().anyMatch(s -> s.getQuantity() > 0));
	}

	private void addClothWithIdToStock(StockDTO stock, Integer clothId) {
		ClothDTO clothDTO = new ClothDTO();
		clothDTO.setId(clothId);
		stock.setCloth(clothDTO);
	}
}
