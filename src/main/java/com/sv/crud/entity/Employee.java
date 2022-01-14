package com.sv.crud.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(schema ="soadev")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="emp_id",insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
	@SequenceGenerator(name="EMP_SEQ",sequenceName = "EMP_SEQ",schema = "soadev",allocationSize = 1)
	private Long empId;
	
	@Column(name="emp_name")
	@NotBlank(message = "El campo de nombre no puede ser nullo o vacio ")
	private String empNombre;
	
	@Column(name="emp_alias")
	private String empAlias;
	
	@Column(name="emp_dni")
	@NotBlank(message = "El campo Dui no puede ser nullo o vacío")
	private String empDui;
	
	@Column(name="emp_email")
	@Email(message = "El campo tiene que contener un formato de correo")
	@NotBlank(message = "El campo de correo no puede ser nullo o vacio")
	private String correo;
	
	@Column(name="emp_password")	
	private String contrasenia;

	public Employee() {
		super();
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

	public String getEmpAlias() {
		return empAlias;
	}

	public void setEmpAlias(String empAlias) {
		this.empAlias = empAlias;
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
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Employee: \n Id: " + empId + "\n nombre:" + empNombre + "\n alias: " + empAlias + "\n Dui: " + empDui
				+ "\n correo: " + correo + "\n contraseña: "+contrasenia;
	}
	
}
