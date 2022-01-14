package com.sv.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.sv.crud.dao.EmployeeDao;
import com.sv.crud.entity.Employee;


public class insertado implements CommandLineRunner {

	@Autowired
	private EmployeeDao empDao;

	@Override
	public void run(String... args) throws Exception {
		Employee emp = new Employee();
		emp.setCorreo("tublimbli@gmail.com");
		emp.setEmpAlias("tb123");
		emp.setEmpDui("000258953-0");
		emp.setEmpNombre("tu blimbli");
		
		empDao.save(emp);
		
		Employee empUno = new Employee();
		empUno.setCorreo("jajajaj123@gmail.com");
		empUno.setEmpAlias("ja123");
		empUno.setEmpDui("20235752-0");
		empUno.setEmpNombre("JAjajajaja ajajai");
		
		empDao.save(empUno);
	}


}
