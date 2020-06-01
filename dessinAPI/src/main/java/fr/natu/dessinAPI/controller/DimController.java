package fr.natu.dessinAPI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.natu.dessinAPI.GlobalVariable;
import fr.natu.dessinAPI.LoggingController;

@RestController
public class DimController {
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	/*@CrossOrigin
	@GetMapping(value = "getHeight")
	public int getHeight(){
		return GlobalVariable.getNbRow();
		
	}
	
	@CrossOrigin
	@GetMapping(value = "getWidth")
	public int getWidth(){
		return GlobalVariable.getNbColumn();
		
	}
	*/
	@CrossOrigin
	@GetMapping(value = "getDim")
	public int[] getDim() {
		int dim[] = new int[2];
		dim[0] = GlobalVariable.getNbRow();
		dim[1] = GlobalVariable.getNbColumn();
		return dim;
	}
}
