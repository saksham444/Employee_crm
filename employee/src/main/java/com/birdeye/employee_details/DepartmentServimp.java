package com.birdeye.employee_details;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames= {"department"})
@Transactional
@Service
public class DepartmentServimp implements DepartmentService {




	HashMap<String,Department> cache;
	public DepartmentServimp() {

		this.cache = new HashMap<String,Department>() ;
	}






	@Autowired
	DepartmentRepo depRepo;

	private boolean addDep=false;

	@Override

	@Cacheable(value="findAll")  
	public List<Department> findAll() throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("I am in function");
		Thread.sleep(2000);
		System.out.println(addDep); 
		
		return depRepo.findAll() ;
	}



	@Override
	@CacheEvict(value="findAll",key="#id")
	public void  deleteById(Long id) {

		depRepo.deleteById(id);
	}
	@CacheEvict(allEntries=true,beforeInvocation=true)
	@CachePut(key="#result.getId()",value="findAll")
	public Department add(String name, Long hid) {

		Department department=new Department(name,hid);
	
		System.out.println(addDep);
		depRepo.save(department);

		return department;
	}



	@Cacheable(value="findAll",key="#id")
	@Override
	public Department findById(Long id) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return depRepo.findById(id).get();

	}






	int flag=0;
	@Override
	public Collection<Department> simpleFindAll() throws InterruptedException {


		if(flag==0) {
			List<Department>departments=depRepo.findAll();

			for(Department dep: departments)
			{	
				System.out.println(dep.depName);
				cache.put(dep.depName,dep);
			}
			flag=1;
		}

		return  cache.values();

	}



	//@Override
	//public Department simplefindByName(String name) {
	//	// TODO Auto-generated method stub
	//	if( cache.containsKey(name))
	//			return cache.get(name);
	//	else {
	//		
	//		
	//		Department dep=depRepo.findByDepName(name);
	//		if(dep!=null)
	//		cache.put(name,dep);
	//		return dep;
	//	}
	//	
	//}



	@Override
	public String simpledeleteByName(String name ) {
		if(cache.containsKey(name))
		{	System.out.println(" not removed from cache");
		cache.remove(name);
		System.out.println("removed from cache");
		depRepo.deleteByDepName(name);;
		System.out.println("removed from db");
		return "Department deleted with name " +name;
		}
		if(depRepo.findByDepName(name)!=null) {
			System.out.println("in 2 if not emoved from db");
			depRepo.deleteByDepName(name);
			return "Department deleted with id " +name;
		}

		return "Department name not present";
	}



	@Override
	public Collection<Department> simpleAdd(String name,Long dhead) {
		// TODO Auto-generated method stub

		if(cache.containsKey(name))
		{
			return 	 cache.values();
		}

		depRepo.save(new Department(name,dhead));
		cache.put(name,depRepo.findByDepName(name));//ye puchna hai
		return  cache.values();
	}



	@Override
	public Department simpleFindByName(String name) {
		// TODO Auto-generated method stub

		if(cache.containsKey(name))
		{
			System.out.println("in cache"+name);
			return cache.get(name);}
		else {
			Department dep=depRepo.findByDepName(name);
			if(dep!=null)
			{
				cache.put(name, dep);
			}
			return dep;
		}


	}



}
