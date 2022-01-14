package com.sv.crud.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sv.crud.entity.Employee;

@Repository
@Transactional
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	public List<Employee> findByEmpNombre(String empNombre);
	
	
	public List<Employee> findByEmpDui(String empDui);
	
}
