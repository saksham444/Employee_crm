package com.birdeye.employee_details;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {

//	
//	List<Department> findBydep_name(String dep_name);
	
	
}
