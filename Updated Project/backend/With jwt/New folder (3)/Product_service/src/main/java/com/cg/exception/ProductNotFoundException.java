package com.cg.exception;

public class ProductNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProductNotFoundException() {
		super();
	}
	public ProductNotFoundException(String msg) {
		super(msg);
	}
}
