package com.birdeye.employee_details;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeserviceInterf {
	
	
	public String addEmployee( String f_name, String m_name, String l_name,
			 String email, Long department, long manager,
			 String jod,String eod, String bdate, String number, String designation, String laptop_name) throws ParseException;
	
	public List<Employee> fetchByAgeandDepartment(int age,String department);
	public Page fetchByDepartmentStrength(int strength,Pageable page);
	public Page<String> fetchTecnologyAfterDate(Date jdate,Pageable page);
	public List<Employee> findEmployeeLeftAfterDate(Date date);
	public List findEmployeeByName(String[] names) ;
	public List findEmployeeafter2020();
	public List<Employee> fetchBymanagername(Long id);
	Page<Employee> findAll(Pageable page);
	public void deleteById(long eid);

	public Optional<Employee> findByempId(long id);

	public CompletableFuture<List<Employee>> fetchOldestEmployee(String department);

	public CompletableFuture<List<Employee>> fetchYoungestEmployee(String department);

	public CompletableFuture<List<Employee>> fetchEmpByA();

	public CompletableFuture<List<Employee>> fetchEmpByAge25();

	public CompletableFuture<Integer> fetchHeadCountDep(String department);




}
