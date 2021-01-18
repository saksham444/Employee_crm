package com.birdeye.employee_details;

import java.util.*;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
	
	
	
	
	

	//API1
	@Query(value="select emp from Employee emp ,Department dep "
			+ "where dep.depName=?2 " +" and Dateddiff(GETDATE(),emp.bdate)>?1 "
			+ " ",nativeQuery=false)
	List<Employee> fetchByAgeandDepartment(int age,String department);
	
	//List<Employee> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	
	
	//Api 2	
	@Query(value="SELECT department.dep_name from department  INNER JOIN employee ON department.d_id =employee.dep_id GROUP BY employee.dep_id HAVING COUNT(*) >=?1 ",nativeQuery=true)
	Page fetchByDepartmentStrength(int strength,Pageable page);
	
	
	//Api 3
	@Query(value="SELECT e.fName from	Employee e where e.jod>?1 and e.department.id=1",nativeQuery=false)
	Page<String> fetchTecnologyAfterDate(Date jdate,Pageable page);
	
	
	
	//Api 4
	@Query(value ="select f_name, m_name, l_name from employee where employee.f_name LIKE ?1 or employee.f_name LIKE %?2% or employee.f_name LIKE %?3% or employee.m_name LIKE %?2% or employee.m_name LIKE'%?1%' or employee.m_name LIKE'%?3%' or employee.l_name LIKE'%?1%' or employee.l_name LIKE'%?2%' or employee.l_name LIKE'%?3%'",nativeQuery =true)
	List<Employee> fetchEmployeeByName(String fname,String mname,String lname);
	
	
	@Query(value ="select f_name, m_name, l_name from employee where employee.f_name LIKE ?1 or employee.f_name LIKE '%?2%' or employee.l_name LIKE'%?1%' or employee.l_name LIKE'%?2%' or employee.m_name LIKE'%?1%' or employee.m_name LIKE'%?2%'  ",nativeQuery=true)
	List fetchEmployeeByName(String fname,String lname);
	
	@Query(value ="select f_name, m_name, l_name from employee where employee.f_name LIKE ?1 or employee.m_name LIKE '%?1%' or employee.l_name LIKE '%?1%'   ",nativeQuery =true)
	List fetchEmployeeByName(String fname);
	
	//API 5
	@Query(value="Select emp.fName, emp.lName from Employee emp where YEAR(emp.jod)>=2020 ",nativeQuery=false)
	List fetchEmployeeafter2020();
	
	//Api 6
		@Query(value="select emp from Employee emp where emp.eod>?1",nativeQuery=false)
		List<Employee> fetchEmployeeLeftAfterDate(Date date);
//		API7
		@Query(value="select emp from Employee emp where emp.manager_id=?1",nativeQuery=false )
		List<Employee> fetchBymanagername(Long id);
			
//		API8
		@Query(value="select emp from Employee emp where emp.department.depName=?1 ORDER BY emp.bdate",nativeQuery=false )
		@QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "1"))
		List<Employee> fetchOldestEmployee(String department);
		
//		API9
		@Query(value="select  emp from Employee emp where emp.department.depName=?1 ORDER BY emp.jod DESC",nativeQuery=false )
		@QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "1"))
		List<Employee> fetchYoungestEmployee(String department);
		
//		API 10 		
		@Query(value="select COUNT(*) from Employee emp where emp.department.depName=?1",nativeQuery=false )
		int fetchHeadCountDep(String department);
		
		
		
//     	API 11	
		@Query(value ="select e from Employee e where e.fName LIKE 'A%' or e.fName LIKE 'a%'")
		List<Employee> fetchEmpByA();
		
//		API 12				
		@Query(value="select emp from Employee emp where Dateddiff(GETDATE(),emp.bdate)>=25 ",nativeQuery=false)
		List<Employee> fetchEmpByAge25();
	
}
