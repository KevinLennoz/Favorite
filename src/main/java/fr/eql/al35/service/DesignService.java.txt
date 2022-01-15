package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Design;
import fr.eql.al35.iservice.DesignIService;

@Service
public class DesignService implements DesignIService{

	@Override
	public List<Design> displayAllDesign(){
		return (List<Design>) designRepo.findAll();
	}
}
