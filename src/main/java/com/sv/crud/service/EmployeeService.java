package com.sv.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.crud.dao.EmployeeDao;
import com.sv.crud.entity.Employee;

@Service
public class EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeDao empAdmin;
		
	public List<Employee> mostrarTodo(){
		List<Employee> listEmp = new ArrayList<>();
		
		listEmp = empAdmin.findAll();
		if(listEmp.isEmpty()) {
			LOG.info("[MostrarTodo] La lista se encuentra vacia");
		}
		else {
			LOG.info("[MostrarTodo] registro de datos "+listEmp.size());
		}
		return listEmp;
	}
	
	public Employee buscarId(Long idEmp) {
		Optional<Employee> empOp = null;
		Employee emp = null;
		LOG.info("[buscarId] ID a validar "+idEmp);
		if(empAdmin.existsById(idEmp)) {
			empOp = empAdmin.findById(idEmp);
			emp = empOp.get();
			LOG.info("[buscarId] Empleado encontrado " +emp.toString());
		}
		else {
			LOG.info("[buscarId] Empleado no existe id proporcionado "+ idEmp);
			throw new NoSuchElementException("No existe el usuario con el id "+ idEmp); 
		}
		return emp;
	}
	
	
	public boolean guardarEmpleado(Employee emp) {
		LOG.info("[guardarEmpleado] Iniciando proceso para guardar empleado");
		boolean verificar = false;
		
		LOG.info("[guardarEmpleado] Verificando si existe el nombre y el dui del empleado");
		if(!empAdmin.findByEmpNombre(emp.getEmpNombre()).isEmpty() && !empAdmin.findByEmpDui(emp.getEmpDui()).isEmpty() ) {
			LOG.info("[guardarEmpleado] Favor validar los campos enviados en la peticion");
		}
		else {
			empAdmin.save(emp);
			verificar = true;
			LOG.info("[guardarEmpleado] Empleado guardado con exito");
		}
		
		return verificar;	
	}
	
	public boolean updateEmployee(Employee emp) {
		boolean verificar = false;
		
		if(empAdmin.findByEmpNombre(emp.getEmpNombre()).get(0).getEmpId() == emp.getEmpId()) {
			if(!empAdmin.findByEmpDui(emp.getEmpDui()).isEmpty() && empAdmin.findByEmpDui(emp.getEmpDui()).get(0).getEmpId() == emp.getEmpId()) {
				LOG.info("[updateEmployee] Actualizando empleado");
				empAdmin.save(emp);
				LOG.info("[updateEmployee] Actualizacion OK");
				verificar = true;
			}
			else {
				LOG.info("[updateEmployee] Existe el Dui ya existe, favor verificar"); 
			}
			
		}
		else {
			LOG.info("[updateEmployee] Existe el nombre en la base de datos, favor verificar el nombre");
		}
		return verificar;
	}
	
	
	public boolean deleteEmployee(Long id) {
		boolean verificar = false;
		if(empAdmin.existsById(id)) {
			empAdmin.deleteById(id);
			verificar = true;
		}
		else {
			throw new NoSuchElementException("No existe el usuario con el id "+ id); 
		}
		return verificar;
	}

	
}
