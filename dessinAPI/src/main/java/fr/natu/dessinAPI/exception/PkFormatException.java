package fr.natu.dessinAPI.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.natu.dessinAPI.LoggingController;

public class PkFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6348905744661563366L;
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	public PkFormatException(String s) {
		logger.error(s + " is not the good format. The good format is \"line|column\"" );
		
	}
}
