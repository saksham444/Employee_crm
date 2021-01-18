package com.birdeye.employee_details;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepo extends JpaRepository<Department,Long> {

public Department findByDepName(String name);
public void deleteByDepName(String name);


@Query(value="Select dep from Department dep where dep.depName=?1",nativeQuery=false)
List<Department> fetchByDepartment(String Department,Pageable page);



	
}
