package fr.natu.dessinAPI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.natu.dessinAPI.GlobalVariable;
import fr.natu.dessinAPI.LoggingController;
import fr.natu.dessinAPI.exception.PkFormatException;
import fr.natu.dessinAPI.model.Box;
import fr.natu.dessinAPI.model.Table;
import fr.natu.dessinAPI.persistence.BoxRepository;

@RestController
public class TableController {
	
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	@Autowired
	BoxRepository boxRepository;
	/*
	 * Get the color of a box
	 * id is "line|column"	 
	 */
	
	@CrossOrigin
	@GetMapping(value = "color/{id}")
	public String getColor(@PathVariable String id)  {
		String[] pks = id.split("[|]");
		if (pks.length != 2) {
			new PkFormatException(id);
			return null;
		}
		int lin;
		int col; 
		try {
			lin = (Integer.parseInt(pks[0]));
			col = (Integer.parseInt(pks[1]));
		} catch (Exception e) {
			logger.error("impossible to parse the value in integer");
			return null;
		}
 		logger.debug(boxRepository.findByLineAndColumn(lin, col));			
		return boxRepository.findByLineAndColumn(lin, col);
	}
	@CrossOrigin
	@PostMapping(value = "initialize")
	public void initializeTable() {
		for (int i = 0 ; i < GlobalVariable.getNbRow() ; i++){
			for (int j = 0 ; j < GlobalVariable.getNbColumn() ; j++){
				if (null == boxRepository.findByLineAndColumn(i, j)) {
					boxRepository.postColor(i, j, "777777");
				}
			}
		}
		logger.info("tableau initialisé en base de données");
		
	}
	@CrossOrigin
	@PutMapping(value = "color")
	public ResponseEntity<Void> setColor(@RequestBody Box box) {
		int lin = box.getLine();
		int col = box.getColumn();
		String color = box.getColor();
		ResponseEntity<Void> resp = box.verify();
		if (null != resp) {
			return resp;
		}
		
		boxRepository.putColor(lin, col, color);
		logger.info("color changed at position :" + Integer.toString(lin) + ", " + Integer.toString(col));
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "getAll")
	public Table getAll() {
		
		
		return new Table(boxRepository.getAll());
	}
	
}
