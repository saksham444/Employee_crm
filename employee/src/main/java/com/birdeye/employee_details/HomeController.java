package com.birdeye.employee_details;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.birdeye.employeeRepo.EmployeeRepo;
//import com.birdeye.entity.Employee;
@TrackExecutionTime	
@Controller
public class HomeController  {
	
	private static Logger my_logger=Logger.getLogger(HomeController.class.getName());
	
	
	
	
	@Autowired
	EmployeeserviceInterf empService;
	
	
	private void checkFunction()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {   
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		my_logger.info("HEllo ji IN CHECK");
	}
	
	
	@ResponseBody
	@RequestMapping("/")
	public void check()
	{	
		checkFunction();
		my_logger.info("HEllo ji");
	}
	
	
	@ResponseBody
	@PostMapping("/add")
	String addEmployee(@RequestParam("f_name") String f_name,@RequestParam("m_name") String m_name,@RequestParam("l_name") String l_name,
			@RequestParam("email") String email,@RequestParam("department") Long department,@RequestParam("manager") long manager,
			@RequestParam("jod") String jod,@RequestParam("eod") String eod,@RequestParam("bdate") String bdate,@RequestParam("number") String number,@RequestParam("designation") String designation,@RequestParam("laptop") String laptop_name) throws ParseException
	{	
				return empService.addEmployee(f_name, m_name, l_name, email, department, manager, jod, eod, bdate, number, designation, laptop_name) ;
	}
	
	@ResponseBody
	@DeleteMapping("/delete")
	public String delEmp(@RequestParam("eid") long eid )
	{	
	
		my_logger.info("In delete"+eid);
		empService.deleteById(eid);
		return "deleted employee with id " + eid;
	}
	
	//api 1
	@ResponseBody
	@RequestMapping("/departmentandage")
	public List<Employee> findByDepandAge(@RequestParam("department") String department,@RequestParam("age") int age)
	{
	
		List<Employee> emp=empService.fetchByAgeandDepartment(age,department);
		
		return emp;
		
	}
	
	//API 2
	@ResponseBody
	@RequestMapping("/strength")
	public Page<String> findByDepStrength(@RequestParam("strength") int strength,Pageable page)
	{
		return  empService.fetchByDepartmentStrength(strength,page);
	}
	//API 3
	@RequestMapping("technologyemployeeafterdate")
	@ResponseBody
	public Page<String> findTechAfterDate(@RequestParam("date") String date,Pageable page)
	{
		Date jdate;
		try {
		  jdate=new SimpleDateFormat("yyyy/dd/mm").parse(date); 
		}
		catch(Exception e)
		{
			jdate=new Date(2020,01,01);
		}
		
		return empService.fetchTecnologyAfterDate(jdate,page);
		
	}
	
	//API 4
	@RequestMapping("name")
	@ResponseBody
	public List findByName(@RequestParam("name") String fullName){
		
		String[] names=fullName.split(" ");
		
		for(int i=0;i<names.length;i++)
		{	names[i]="%" + names[i].toLowerCase() + "%";
			my_logger.info(names[i]);
		}
		return empService.findEmployeeByName(names);
	}
	
	
	//API 5
	@TrackExecutionTime	
	@RequestMapping("allEmployee")
	@ResponseBody
	public Page<Employee> findAllEmployee(Pageable page)
	{	
		my_logger.info("in all employee function");
		return empService.findAll(page);
	}
	
	
//	@RequestMapping("oldEmployee")
//	@ResponseBody
//	public List<Employee> fetchOldestEmployee(@RequestParam("department") String department)
//	{
//		
//		return empService.fetchOldestEmployee(department);
//	}
//	
//	@RequestMapping("youngEmployee")
//	@ResponseBody
//	public List<Employee> fetchYoungetEmployee( @RequestParam("department") String department )
//	{
//		
//		return empService.fetchYoungestEmployee(department);
//	}
//	
//	@RequestMapping("allEmployeeA")
//	@ResponseBody
//	public List fetchEmployeeByA()
//	{
//		
//		return empService.fetchEmpByA();
//	}
//	
//	@RequestMapping("allEmployeeBy25")
//	@ResponseBody
//	public List<Employee> fetchEmpByAge25()
//	{
//		
//		return empService.fetchEmpByAge25();
//	}
//	
//	@RequestMapping("depCount")
//	@ResponseBody
//	 public int fetchHeadCountDep(@RequestParam("department") String department)
//	{
//		return empService.fetchHeadCountDep(department);
//	}
	 

	
	
	@ResponseBody
    @GetMapping(value = "getUsersByThread")
    public  ResultHolder getUsers(@RequestParam("department") String department) throws Exception {
       
		
		long start= System.currentTimeMillis();
    	CompletableFuture<List<Employee>> oldEmp=empService.fetchOldestEmployee(department);
        CompletableFuture<List<Employee>> youngEmp=empService.fetchYoungestEmployee(department);
        CompletableFuture<List<Employee>> nameA=empService.fetchEmpByA();
        CompletableFuture<Integer> headCount = empService.fetchHeadCountDep(department);
        long finishComp= System.currentTimeMillis();
//        CompletableFuture.allOf(users1,users2,users3).join();
        
        long compTime=finishComp-start;
        my_logger.info("Completable finish time "+ compTime);
        
        
        
        my_logger.info("api done");

        start= System.currentTimeMillis();
        
        Future<ResultHolder> obj=  CompletableFuture.completedFuture(new ResultHolder())
                .thenCombineAsync(oldEmp, ResultHolder::withAResult)
                .thenCombineAsync(youngEmp, ResultHolder::withBResult)
                .thenCombineAsync(nameA, ResultHolder::withCResult)
                .thenCombineAsync(headCount, ResultHolder::withDResult);
         finishComp= System.currentTimeMillis();
        compTime=finishComp-start;
        
        my_logger.info("Completable finish time api "+ compTime);
        
        return obj.get();
	}

//	@ResponseBody
//    @GetMapping(value = "getUsersByThread")
//	 public  ResultHolder getUsersExecutor(@RequestParam("department") String department) throws InterruptedException, ExecutionException {
//		
//		ExecutorService executorService = Executors.newFixedThreadPool(5);
//		
//		Future<L> task2Future = executorService.submit(()->{
//			  
//			  System.out.println(String.format("starting expensive task thread %s", Thread.currentThread().getName()));
//			  Double returnedValue = someExpensiveRemoteCall();
//			  return returnedValue;
//			});
//		
//		
//		return null;
//		 
//	}
	

	
	
	
	
	
//	@RequestMapping("leaverequest")
//	@ResponseBody
//	public String registerEod(@RequestParam("id")int id,@RequestParam("date") String ldate) throws ParseException
//	{
//		Date date=new SimpleDateFormat("dd/mm/yyyy").parse(ldate);
//		Employee emp=empService.getOne(id);
//		emp.setEod(date);
//		empService.save(emp);
//		return "Leave reuest is approved for "+ emp.getfName() + "on "+emp.getEod() ;
//	}
	//API 6
	@RequestMapping("leaveafterdate")
	@ResponseBody
	public List<Employee> findEmployeeLeftAfterDate(@RequestParam("date") String ldate) throws ParseException
	{
		Date date = new SimpleDateFormat("dd/mm/yyyy").parse(ldate);
		
		return empService.findEmployeeLeftAfterDate(date);
		
	}
	
	//API 7
	@RequestMapping("after2020")
	@ResponseBody
	public List findEmployeeAfter2020( )
	{
		return empService.findEmployeeafter2020();
	}
	
	//API 8
	@ResponseBody
	@RequestMapping("managerid")
	public List<Employee> findByManagerId(@RequestParam("id") Long id )
	{
		return empService.fetchBymanagername(id);
	}
	@ResponseBody
	@RequestMapping("empid")
	public Optional<Employee> findById1(@RequestParam("id") long id )
	{
		return (Optional<Employee>) empService.findByempId(id);
	}
	
//	@Autowired
//	DepartmentRepo depServ;
	
//	@ResponseBody
//	@RequestMapping("depall")
////	@Cacheable(value="cacheStudentInfo", key="id")
//	@Cacheable(value="departmentInfo") 
//	List depAll() throws InterruptedException
//	{	Thread.sleep(2000);
//	my_logger.info("In cacheble ");
//		return depServ.findAll();
//	}

	
	@Autowired
	DepartmentService depServ;
	
	@ResponseBody	
@RequestMapping("depall")

List<Department> depAll() throws InterruptedException 
{	
my_logger.info("In cacheble ");
	return depServ.findAll();
}

@ResponseBody
@RequestMapping("depadd")
  
public Department updateDepartment(@RequestParam("name") String name,@RequestParam("hid") Long hid)  
{
	return depServ.add(name,hid);
	
}

@ResponseBody
@RequestMapping("depdel")
 
public String updateDepartment(@RequestParam("did") long did)  
{
	 depServ.deleteById(did);
	 return "employee deleted with id" +did;
}


@ResponseBody
@RequestMapping("depid")
public Department findById(@RequestParam("did") long did)
{
	return depServ.findById(did);
	
}
	
@ResponseBody	
@RequestMapping("simpledepall")

Collection<Department> simpleDepAll() throws InterruptedException 
{	
my_logger.info("In simple ");
return depServ.simpleFindAll();
}

@ResponseBody
@RequestMapping("simpledepadd")

public Collection<Department> simpleUpdateDepartment(@RequestParam("name") String name,@RequestParam("hid") Long hid)  
{
return depServ.simpleAdd(name,hid);

}

@ResponseBody
@RequestMapping("simpledepdel")

public String simpeUpdateDepartment(@RequestParam("name") String name)  
{
 depServ.simpledeleteByName(name);
 return "employee deleted with id" +name;
}


@ResponseBody
@RequestMapping("simpledepname")
public Department simpleFindByName(@RequestParam("name") String name)
{
return depServ.simpleFindByName(name);

}
	
	
	
	
	
	
	
	
//	@RequestMapping("techdepartment")
//	List<Department> allDepartment()
//	{
//		return departmentRepo.findBydep_name("technology");
//	}
//	
//	List<Employee> showDepartmentEmployee(@RequestParam("department") String department)
//	{
//		departmentRepo.f
//	}
	
	
	
	

	
	
	

}



//

