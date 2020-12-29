package com.birdeye.employee_details;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity

public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="d_id")
	Long Id;
	
	@Column(name="dep_name")
	String dep_name;
	
	
	@Column(name="dep_head")
	Long dep_head;
	

	@OneToMany(mappedBy="department",cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
	private List<Employee> empList;
	
	public Department()
	{
		
	}
	public Department(String dep_name, Long dep_head) {
		super();
		this.dep_name = dep_name;	
		this.dep_head = dep_head;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public Long getDep_head() {
		return dep_head;
	}

	public void setDep_head(Long dep_head) {
		this.dep_head = dep_head;
	}


	public void addEmployee(Employee employee)
	{
		if(empList==null)
		{
			empList=new ArrayList<>();
		}
		empList.add(employee);
		
		employee.setDepartment(this);
	}
	
	public List<Employee> showAllEmployee()
	{
		return empList;
	}
	
	
	
	
}
