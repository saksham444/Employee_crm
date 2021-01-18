package com.birdeye.employee_details;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface DepartmentService {

	public List<Department> findAll() throws InterruptedException;
	
	public void deleteById(Long id);
	
	public Department add(String name, Long hid) ;
	
	
	public Department findById(Long id);

	//---------------------------------------------------------
	Collection<Department> simpleFindAll() throws InterruptedException;
	
//	Department simplefindByName(String name);
	
	
	String simpledeleteByName(String name);

	public Collection<Department> simpleAdd(String name, Long hid) ;
	
	Department simpleFindByName(String name);
}
