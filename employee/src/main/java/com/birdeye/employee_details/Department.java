package com.birdeye.employee_details;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity

public class Department implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="d_id")
	Long id;
	
	@Column(name="dep_name")
	String depName;
	
	
	@Column(name="dep_head")
	Long dep_head;
	

	@OneToMany(mappedBy="department",cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
	private List<Employee> empList;
	
	public Department()
	{
		
	}
	public Department(String dep_name, Long dep_head) {
		super();
		this.depName = dep_name;	
		this.dep_head = dep_head;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDep_name() {
		return depName;
	}

	public void setDep_name(String dep_name) {
		this.depName = dep_name;
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
