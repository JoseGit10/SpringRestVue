package com.sv.crud.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class DtoEmployee implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private Long empId;
	
	@NotBlank(message = "El nombre no puede ser vacio o nulo")
	private String empNombre;
	
	@NotBlank(message = "El campo dui no puede ser vacio o nulo")
	private String empDui;
	
	
	private String empAlias;
	
	@NotBlank(message = "El campo correo no puede ser vacio o nulo" )
	@Email(message = "colocar un formato de tipo email")
	private String correo;

	private String contrasenia;
	
	public DtoEmployee() {
	}
	
	

	public DtoEmployee(Long empId, @NotBlank(message = "El nombre no puede ser vacio o nulo") String empNombre,
			@NotBlank(message = "El campo dui no puede ser vacio o nulo") String empDui,
			@NotBlank(message = "El campo correo no puede ser vacio o nulo") @Email(message = "colocar un formato de tipo email") String correo) {
		super();
		this.empId = empId;
		this.empNombre = empNombre;
		this.empDui = empDui;
		this.correo = correo;
	}



	public DtoEmployee(@NotBlank(message = "El nombre no puede ser vacio o nulo") String empNombre,
			@NotBlank(message = "El campo dui no puede ser vacio o nulo") String empDui,
			@NotBlank(message = "El campo correo no puede ser vacio o nulo") @Email(message = "colocar un formato de tipo email") String correo) {
		super();
		this.empNombre = empNombre;
		this.empDui = empDui;
		this.correo = correo;
	}

	public DtoEmployee(Long empId, @NotBlank(message = "El nombre no puede ser vacio o nulo") String empNombre,
			@NotBlank(message = "El campo dui no puede ser vacio o nulo") String empDui, String empAlias,
			@NotBlank(message = "El campo correo no puede ser vacio o nulo") @Email(message = "colocar un formato de tipo email") String correo,
			String contrasenia) {
		super();
		this.empId = empId;
		this.empNombre = empNombre;
		this.empDui = empDui;
		this.empAlias = empAlias;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpNombre() {
		return empNombre;
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}


	public String getEmpDui() {
		return empDui;
	}

	public void setEmpDui(String empDui) {
		this.empDui = empDui;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEmpAlias() {
		return empAlias;
	}

	public void setEmpAlias(String empAlias) {
		this.empAlias = empAlias;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


}
