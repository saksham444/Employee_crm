package com.birdeye.employee_details;


import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Table(name="employee")
@Entity
public class Employee {
	
	
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="e_id")
	long 	e_id;
	@Column(name="f_name")
	String fName;
	@Column(name="m_name")
	String mName;
	@Column(name="l_name")
	String lName;
	@Column(name="email")
	String email;
	@Column(name="phone")
	String phone;
	
	@Column(name="active")
	byte active;

	@Column(name="designation") 
	String desgnation;
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="dep_id",referencedColumnName = "d_id") 
	Department department;
	
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="emp_id")
	List<Laptops> laptops;
	
	@Column(name="manager_id")
	Long manager_id;
	
	
	
	@Column(name="timestamp")
	@Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dob")
	Date bdate;
////	@JsonFormat(pattern="dd-mm-yyyy",shape=Shape.STRING)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="jdate")
	Date jod;
//	@JsonFormat(pattern="dd-mm-yyyy",shape=Shape.STRING)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="edate")
	Date eod;
	
	
	
	
//	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//	@JoinColumn(name="rep_id", referencedColumnName = "r_id")
////	@Column(name="reporter_id") 
//	Reporting reporting;
	
	public List<Laptops> getLaptops() {
		return laptops;
	}


	public void setLaptops(List<Laptops> laptops) {
		this.laptops = laptops;
	}
	
	public Employee()
	{
		
	}
	
	
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public Date getJod() {
		return jod;
	}
	public void setJod(Date jod) {
		this.jod = jod;
	}
	public Date getEod() {
		return eod;
	}
	public void setEod(Date eod) {
		this.eod = eod;
	}
	public long getId() {
		return e_id;
	}
	public void setId(long id) {
		this.e_id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	public String getDesgnation() {
		return desgnation;
	}
	public void setDesgnation(String desgnation) {
		this.desgnation = desgnation;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	
	public void addLaptops(Laptops laptop)
	{
		if(laptops==null)
		{
			laptops=new ArrayList<Laptops>();
		}
		laptops.add(laptop);
	}
	
	
}