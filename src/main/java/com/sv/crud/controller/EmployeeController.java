package com.sv.crud.controller;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sv.crud.dto.DtoEmployee;
import com.sv.crud.dto.Mensaje;
import com.sv.crud.entity.Employee;
import com.sv.crud.service.EmployeeService;
import com.sv.crud.util.Utilidades;
import com.sv.crud.util.ValidExceptionData;

@RestController
@RequestMapping("/empleado")
@CrossOrigin
public class EmployeeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService empService;
	

	@GetMapping(path = "/",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll(){
		List<Employee> listEmp = null;
		listEmp = empService.mostrarTodo();
		return new ResponseEntity<List<Employee>>(listEmp,HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/buscarEmpleado/{idEmp}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findEmployeeId(@PathVariable("idEmp") Long id){
		Employee emp = null;
		
		if(id>=1) {
			emp = empService.buscarId(id);
			return new ResponseEntity<>(emp,HttpStatus.OK);
		}
		else {
			LOG.info(" El parametro proporcionado no es correcto "+id);
			throw new IllegalArgumentException("El parametro porporcionado no es el correcto "+id);
		}

	}
	
	@PostMapping(path = "/agregarEmpleado",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addEmployee(@Valid @RequestBody DtoEmployee empDto,BindingResult result){
		if(result.hasErrors()) {
			throw new ValidExceptionData(result);
		}
		
		String nombre = empDto.getEmpNombre().toUpperCase();
		String contrasenia = "12345";
		
		LOG.info("Contraseña por defecto "+contrasenia);
		
		Employee emp = new Employee();
		
		emp.setContrasenia(Base64.getEncoder().encodeToString(contrasenia.getBytes()));
		emp.setCorreo(empDto.getCorreo().trim());
		emp.setEmpDui(empDto.getEmpDui().trim());
		emp.setEmpNombre(nombre.trim());
		emp.setEmpAlias(Utilidades.crearAlias(nombre));
		
		LOG.info("Realizando proceso para guardar Empleado ");
		boolean verificar = empService.guardarEmpleado(emp);
		if(verificar) {
			HttpStatus httpStatus = HttpStatus.CREATED;
			Mensaje msj = new Mensaje("Empleado creado correctamente",httpStatus.value(),new Date());
			return new ResponseEntity<>(msj,httpStatus);
		}
		else {
			throw new DuplicateKeyException("El empleado con el nombre "+ nombre +" ya se encuentra registrado");
		}
	}
	
	@PostMapping(path = "/actualizarEmpleado",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> UpdateEmployee(@Valid @RequestBody DtoEmployee empDto,BindingResult result ){
		if(result.hasErrors()) {
			throw new ValidExceptionData(result);
		}
		if(empDto.getEmpId()<0 || empDto.getEmpId() == null ) {
			throw new NoSuchElementException("El campo ID se encuentra vacio favor verificar");
		}
		
		LOG.info("Contraseña: "+empDto.getContrasenia());
		
		Employee emp = new Employee();
		emp.setEmpId(empDto.getEmpId());
		emp.setEmpNombre(empDto.getEmpNombre().trim().toUpperCase());
		emp.setEmpAlias(empDto.getEmpAlias());
		emp.setContrasenia(empDto.getContrasenia());
		emp.setEmpDui(empDto.getEmpDui());
		emp.setCorreo(empDto.getCorreo());
		
		LOG.info("empleado a guardar "+emp.toString());
		
		
		boolean verifica = empService.updateEmployee(emp);
		if(verifica) {
			HttpStatus httpStatus = HttpStatus.OK;
			Mensaje msj = new Mensaje("Empleado Actualizado correctamente", httpStatus.value(), new Date());
			return new ResponseEntity<>(msj,httpStatus);
		}
		else{
			throw new DuplicateKeyException("Datos de empleado incorrectos verificar datos del empleado");
		}	
	}	
	
	@GetMapping(path = "/borrarEmpleado/{idEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEmpleado(@PathVariable("idEmp") Long id){
		HttpStatus httpStatus = HttpStatus.OK;
		Mensaje msj = new Mensaje();
		LOG.info("ID a buscar "+id);
		
		if(id>=1 && id != null ) {
			boolean verificar = empService.deleteEmployee(id);
			if(verificar) {
				msj = new Mensaje("Usuario Eliminado correctamente", httpStatus.value(), new Date());
			}
			else{
				throw new DuplicateKeyException("Datos de empleado incorrectos verificar datos del empleado");
			}	
		}
		else {
			throw new IllegalArgumentException("El parametro porporcionado no es el correcto "+id);

		}
		return new ResponseEntity<>(msj,httpStatus);
	}
	
	
}
