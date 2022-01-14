package com.sv.crud.dto;

import java.util.Date;

public class Mensaje {

	private String msj;
	private Integer estado;
	private Date timestap;

	public Mensaje(String msj) {
		this.msj = msj;
	}
	
	public Mensaje(String msj, Integer estado, Date timestap) {
		this.msj = msj;
		this.estado = estado;
		this.timestap = timestap;
	}
	
	

	public Mensaje() {
		super();
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getTimestap() {
		return timestap;
	}

	public void setTimestap(Date timestap) {
		this.timestap = timestap;
	}
	
	
}
