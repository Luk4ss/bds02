package com.devsuperior.bds02.services.exceptions;

public class DbConstraintException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DbConstraintException(String msg) {
		super(msg);
	}

}
