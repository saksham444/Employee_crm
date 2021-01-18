package com.birdeye.employee_details;

import java.util.List;

public class ResultHolder  {
	
	ResultHolder()
	{
		
	}
	
	private List<Employee> aResult;
    private List<Employee> bResult;
    private List<Employee> cResult;
    private int dResult;
	public void setaResult(List<Employee> aResult) {
		this.aResult = aResult;
	}
	public void setbResult(List<Employee> bResult) {
		this.bResult = bResult;
	}
	public void setcResult(List<Employee> cResult) {
		this.cResult = cResult;
	}
	public void setdResult(int dResult) {
		this.dResult = dResult;
	}
	

    // Getters

    public List<Employee> getaResult() {
		return aResult;
	}
	public List<Employee> getbResult() {
		return bResult;
	}
	public List<Employee> getcResult() {
		return cResult;
	}
	public int getdResult() {
		return dResult;
	}
	
	
	public ResultHolder withAResult(final List<Employee> aResult) {
       
    	this.aResult = aResult;
        System.out.println(aResult);
    	return this;
    }

    public ResultHolder withBResult(final List<Employee> bResult) {
        this.bResult = bResult;
        System.out.println(bResult);
        return this;
    }

    public ResultHolder withCResult(final List<Employee> cResult) {
        this.cResult = cResult;
        System.out.println(cResult);
        return this;
    }

    public ResultHolder withDResult(final int dResult) {
    	
        this.dResult = dResult;
        System.out.println(dResult);
        return this;
    }

}
