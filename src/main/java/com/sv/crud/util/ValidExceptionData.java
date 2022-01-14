package com.sv.crud.util;

import org.springframework.validation.BindingResult;

public class ValidExceptionData extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final transient BindingResult result;
	
	public ValidExceptionData(BindingResult result) {
		super();
		this.result = result;
	}
	
	public ValidExceptionData(String mensaje, BindingResult result) {
		super(mensaje);
		this.result = result;
	}
	
	public BindingResult getResult() {
		return result;
	}
	
}
