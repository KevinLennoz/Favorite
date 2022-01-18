package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.ProductDelegate;
import fr.eql.al35.dto.CustomDTO;
import fr.eql.al35.dto.OrderLineDTO;
import fr.eql.al35.iservice.ArticleIService;

@Service
public class ArticleService implements ArticleIService{

	private ProductDelegate productDelegate;

	@Autowired
	private ArticleService(ProductDelegate productDelegate) {
		this.productDelegate = productDelegate;
	}
	
	@Override
	public void updateCustomsInfos(OrderLineDTO orderLine) {
		List<CustomDTO> customs = orderLine.getCustoms();
		
		for (CustomDTO custom : customs) {
			
			int designId = custom.getDesign().getId();
			
			if (designId != 0) {
				custom.setDesignId(designId);
			    custom.setDesign(productDelegate.getDesignById(designId));
			}
			
		}
	}
}
