package com.sv.crud.dto;

import java.util.Date;
import java.util.List;

public class ErrorResponse {

	private Integer estatus;
	
	private String mensaje;
	
	private Date timestap;
	
	List<String> errors;
	
	
	public ErrorResponse() {
	}
	
	public ErrorResponse(String mensaje) {
		super();
		this.mensaje = mensaje;
	}



	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getTimestap() {
		return timestap;
	}

	public void setTimestap(Date timestap) {
		this.timestap = timestap;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
