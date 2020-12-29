package com.birdeye.employee_details;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService implements EmployeeserviceInterf {

	@Autowired
	EmployeeRepo empRepo;
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	private Logger my_logger=Logger.getLogger(getClass().getName());
	
	public String addEmployee( String f_name, String m_name, String l_name,
			 String email, Long department, long manager,
			 String jod,String eod, String bdate, String number, String designation, String laptop_name) throws ParseException
	{
		Employee emp=new Employee();
		emp.setActive((byte)1);
		
		 Date date=new SimpleDateFormat("dd/MM/yyyy").parse(bdate);  
		emp.setBdate(date);
		try {
			if(eod!="") {
		 date=new SimpleDateFormat("dd/MM/yyyy").parse(eod);
		emp.setEod(date);
			}
		}
		catch(Exception e)
		{
			emp.setEod(null);
		}

		try {
			 date=new SimpleDateFormat("dd/MM/yyyy").parse(jod);
			emp.setJod(date);
	
			}
		catch(Exception e)
		{
			emp.setJod(null);
		}
			
		
		emp.setEmail(email);
		emp.setCreationDateTime(new Date());
		emp.setDesgnation(designation);
		emp.setfName(f_name);
		emp.setManager_id(manager);
		emp.setlName(l_name);
	
		emp.setmName(m_name);
		emp.setPhone(number);
		
		Department dep= departmentRepo.getOne(department);
		emp.setDepartment(dep);
		Laptops laptop=new Laptops(laptop_name);
		emp.addLaptops(laptop);
		dep.addEmployee(emp);
		departmentRepo.save(dep);
		
		return "Employee saved";

	}
	
	//API1
	@Override
		public List<Employee> fetchByAgeandDepartment(int age,String department)
		{
			return empRepo.fetchByAgeandDepartment(age, department);
		}
	
	//API2
		@Override
	public Page fetchByDepartmentStrength(int strength,Pageable page)
	{
		return empRepo.fetchByDepartmentStrength(strength,page);
	}
	//API3
	@Override
	public Page<String> fetchTecnologyAfterDate(Date jdate,Pageable page)
	{
		return empRepo.fetchTecnologyAfterDate(jdate,page);
	}
	
	//API6
	@Override
	public List<Employee> findEmployeeLeftAfterDate(Date date)
	{
		return empRepo.fetchEmployeeLeftAfterDate(date);
	}
//API 4
	@Override
	public List findEmployeeByName(String[] names) {
		if(names.length==3) {
		
			my_logger.info("3 arguments passed");
			
		return empRepo.fetchEmployeeByName(names[0],names[1],names[2]);
		}
		else if (names.length==2)
		{	my_logger.info("2 arguments passed");
		
			return empRepo.fetchEmployeeByName(names[0],names[1]);
		}
		else {
			my_logger.info("1 arguments passed");
			
			return empRepo.fetchEmployeeByName(names[0]);
		}
	}
	//API 7
	@Override
	public List findEmployeeafter2020()
	{
		return empRepo.fetchEmployeeafter2020();
	}
	@Override
	public //API 8
	List<Employee> fetchBymanagername(Long id){
		return empRepo.fetchBymanagername(id);
	}

	@Override
	public void deleteById(long eid) {
		empRepo.deleteById((int)eid);
		
	}

	@Override
	public Page<String> fetchByDepartment(String Department, Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> fetchEmployeeByName(String fname, String mname, String lname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List fetchEmployeeByName(String fname, String lname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List fetchEmployeeByName(String fname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List fetchEmployeeafter2020() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> fetchEmployeeLeftAfterDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Employee> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Employee> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Employee> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Employee> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Employee> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
