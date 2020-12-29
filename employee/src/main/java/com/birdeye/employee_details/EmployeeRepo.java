package com.birdeye.employee_details;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	
	@Query(value="Select * from department where department.dep_name=?1",nativeQuery=true)
	Page<String> fetchByDepartment(String Department,Pageable page);
	//API1
	@Query(value="select employee.f_name, department.dep_name from employee,department"
			+ "where department.dep_name=?2 " +" and Dateddiff(GETDATE(),employee.dob)>?1> "
			+ " ",nativeQuery=true)
	List<Employee> fetchByAgeandDepartment(int age,String department);
	
	//List<Employee> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	
	
	//Api 2	
	@Query(value="SELECT department.dep_name from department  INNER JOIN employee ON department.d_id =employee.dep_id GROUP BY employee.dep_id HAVING COUNT(*) >=?1 ",nativeQuery=true)
	Page fetchByDepartmentStrength(int strength,Pageable page);
	
	
	//Api 3
	@Query(value="SELECT employee.f_name from	employee where employee.jdate>?1 and employee.dep_id=1",nativeQuery=true)
	Page<String> fetchTecnologyAfterDate(Date jdate,Pageable page);
	
	
	
	//Api 4
	@Query(value ="select f_name, m_name, l_name from employee where employee.f_name LIKE ?1 or employee.f_name LIKE %?2% or employee.f_name LIKE %?3% or employee.m_name LIKE %?2% or employee.m_name LIKE'%?1%' or employee.m_name LIKE'%?3%' or employee.l_name LIKE'%?1%' or employee.l_name LIKE'%?2%' or employee.l_name LIKE'%?3%'",nativeQuery =true)
	List<Employee> fetchEmployeeByName(String fname,String mname,String lname);
	
	
	@Query(value ="select f_name, m_name, l_name from employee where employee.f_name LIKE ?1 or employee.f_name LIKE '%?2%' or employee.l_name LIKE'%?1%' or employee.l_name LIKE'%?2%' or employee.m_name LIKE'%?1%' or employee.m_name LIKE'%?2%'  ",nativeQuery=true)
	List fetchEmployeeByName(String fname,String lname);
	
	@Query(value ="select f_name, m_name, l_name from employee where employee.f_name LIKE ?1 or employee.m_name LIKE '%?1%' or employee.l_name LIKE '%?1%'   ",nativeQuery =true)
	List fetchEmployeeByName(String fname);
	
	//API 5
	@Query(value="Select f_name, l_name from employee where YEAR(employee.jdate)>=2020 ",nativeQuery=true)
	List fetchEmployeeafter2020();
	
	//Api 6
		@Query(value="select * from employee where employee.eod>?1",nativeQuery=true)
		List<Employee> fetchEmployeeLeftAfterDate(Date date);
//		API7
		@Query(value="select * from employee where manager_id=?1",nativeQuery=true )
		List<Employee> fetchBymanagername(Long id);
			
	
	
}
