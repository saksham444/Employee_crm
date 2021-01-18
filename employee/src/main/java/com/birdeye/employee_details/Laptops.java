package com.birdeye.employee_details;

import java.io.Serializable;

import javax.persistence.*;

@Entity

public class Laptops implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="l_id")
	Long p_id;
	
	@Column(name="name")
	String name;

	
	
	public Laptops()
	{
		
	}
	
	public Laptops(String name) {
		super();
		this.name = name;
	}
	
	
	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
	
	
}
