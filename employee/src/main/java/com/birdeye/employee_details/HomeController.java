package com.birdeye.employee_details;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.birdeye.employeeRepo.EmployeeRepo;
//import com.birdeye.entity.Employee;

@Controller
//@RequestMapping("employee")
public class HomeController {
	
	private static Logger my_logger=Logger.getLogger(HomeController.class.getName());
	
	
	
	
	@Autowired
	EmployeeserviceInterf empService;
	
	
	
	
	
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
	String delEmp(@RequestParam("eid") long eid )
	{	my_logger.info("In delete"+eid);
		empService.deleteById(eid);
		return "deleted employee with id " + eid;
	}
	
	//api 1
	@ResponseBody
	@RequestMapping("/departmentandage")
	List<Employee> findByDepandAge(@RequestParam("department") String department,@RequestParam("age") int age)
	{
	
		List<Employee> emp=empService.fetchByAgeandDepartment(age,department);
		
		return emp;
		
	}
	
	//API 2
	@ResponseBody
	@RequestMapping("/strength")
	Page<String> findByDepStrength(@RequestParam("strength") int strength,Pageable page)
	{
		return  empService.fetchByDepartmentStrength(strength,page);
	}
	//API 3
	@RequestMapping("technologyemployeeafterdate")
	@ResponseBody
	Page<String> findTechAfterDate(@RequestParam("date") String date,Pageable page)
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
	public List findByName(@RequestParam String fullName){
		
		String[] names=fullName.split(" ");
		
		for(int i=0;i<names.length;i++)
		{	names[i]="%" + names[i].toLowerCase() + "%";
			my_logger.info(names[i]);
		}
		return empService.findEmployeeByName(names);
	}
	
	
	//API 5
	@RequestMapping("allEmployee")
	@ResponseBody
	Page<Employee> findAllEmployee(Pageable page)
	{	
		my_logger.info("in all employee function");
		return empService.findAll(page);
	}
	
	
	
	
	@RequestMapping("leaverequest")
	@ResponseBody
	public String registerEod(@RequestParam("id")int id,@RequestParam("date") String ldate) throws ParseException
	{
		Date date=new SimpleDateFormat("dd/mm/yyyy").parse(ldate);
		Employee emp=empService.getOne(id);
		emp.setEod(date);
		empService.save(emp);
		return "Leave reuest is approved for "+ emp.getfName() + "on "+emp.getEod() ;
	}
	//API 6
	@RequestMapping("leaveafterdate")
	@ResponseBody
	public List<Employee> findEmployeeLeftAfterDate(@RequestParam("date") String ldate) throws ParseException
	{
		Date date=new SimpleDateFormat("dd/mm/yyyy").parse(ldate);
		
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
	@RequestMapping("managerid")
	@ResponseBody
	public List<Employee> findByManagerId(@RequestParam Long id )
	{
		return empService.fetchBymanagername(id);
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

