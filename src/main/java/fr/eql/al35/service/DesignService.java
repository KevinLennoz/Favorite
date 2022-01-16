package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.ProductDelegate;
import fr.eql.al35.dto.DesignDTO;
import fr.eql.al35.iservice.DesignIService;

@Service
public class DesignService implements DesignIService {

    private final ProductDelegate productDelegate;

    @Autowired
    public DesignService(ProductDelegate productDelegate) {
        super();
        this.productDelegate = productDelegate;
    }

    @Override
    public List<DesignDTO> displayAllDesign(){
        return productDelegate.getAllDesigns();
    }
}
