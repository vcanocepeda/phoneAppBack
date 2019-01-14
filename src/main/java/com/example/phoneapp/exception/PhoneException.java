package com.example.phoneapp.exception;

/**
 * Exception thrown when the phone id is not present in the DataBase
 * 
 * @author Vicente Cano Cepeda
 */

public class PhoneException extends RuntimeException {

	private static final long serialVersionUID = 1223277040345236851L;
	
	/**
	 * Constructor.
	 * 
	 * @param {@link String} message
	 *            
	 */
	public PhoneException(String message) {
		super(message);
	}

}
