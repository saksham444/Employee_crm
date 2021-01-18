package com.birdeye.employee_details;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javassist.bytecode.SignatureAttribute.MethodSignature;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging


	Logger logger=LoggerFactory.getLogger(MyDemoLoggingAspect.class);
	
	@Pointcut(" @annotation(com.birdeye.employee_details.TrackExecutionTime )")
	public void functionAnnotatedTime() {}

	@Pointcut("execution(public * *(..)) && within(@com.birdeye.employee_details.TrackExecutionTime * )")
	public void publicMethodinClassTime() {}
	
	@Pointcut("execution( * com.birdeye.employee_details.HomeController.*(..))")
	public void all() {}

	@Pointcut("functionAnnotatedTime() || publicMethodinClassTime()")
	public void publicMethodInsideAClassMarkedWithAtMonitor() {}

	@Around(" publicMethodInsideAClassMarkedWithAtMonitor()")
	public Object trackTime1(ProceedingJoinPoint pjp) throws Throwable {
		long stratTime=System.currentTimeMillis();
		try {
		Object obj=pjp.proceed();
		long endTime=System.currentTimeMillis();
		logger.info("Method name"+pjp.getSignature()+" time taken to execute : "+(endTime-stratTime));
		return obj;
		}catch(Exception e){
		long endTime=System.currentTimeMillis();
		logger.info("Method name"+pjp.getSignature()+" time taken to execute : and had exception  "+(endTime-stratTime));
		return null;
		}
		
	}
	
	
	@Before("all()")
	public void allFunctions()
	{	String requestKey=UUID.randomUUID().toString();
		MDC.put("id", requestKey);
		
		
	}
	
	
//	
//	@Around(" @annotation(com.birdeye.employee_details.TrackExecutionTime )")
//	public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
//		long stratTime=System.currentTimeMillis();
//		Object obj=pjp.proceed();
//		long endTime=System.currentTimeMillis();
//		logger.info("Method name"+pjp.getSignature()+" time taken to execute : "+(endTime-stratTime));
//		return obj;
//	}
//
//	
//	
//	
//	@Around("execution(public * *(..)) && within(@com.birdeye.employee_details.TrackExecutionTime * )")
//	public Object trackTime2(ProceedingJoinPoint pjp) throws Throwable {
//		long stratTime=System.currentTimeMillis();
//		Object obj=pjp.proceed();
//		long endTime=System.currentTimeMillis();
//		logger.info("Method name"+pjp.getSignature()+" time taken to execute : "+(endTime-stratTime));
//		return obj;
//	}

	
	
	
	
	
	
//	@Pointcut("execution(* com.birdeye.employee_details.HomeController.fetch*(*))")//pointcut for all getters
//	public void forEmploee_detailsgettersmethod() {}
	
//	
//	@Pointcut("execution(* com.birdeye.employee_details.HomeController.set*(*))")//pointcut for all getters
//	public void forEmploee_detailssettersmethod() {}
////	
	
//	
//	
//	

	
	
	
//	@Before("execution(* com.birdeye.employee_details.*.allEmployee())")
//	public void beforeAddAccountAdvice() {
//		
//		System.out.println("\n=====>>> Executing @Before advice on addEmployee()");
//		
//	}
//	
//	
//	
//	@Before("forEmploee_detailsgettersmethod()")
//	public void forgetters() {
//		
//		System.out.println("\n=====>>> Executing @Before advice on getters");
//		
//	}
//	
//	@Before("forEmploee_detailssettersmethod()")
//	public void forsetters() {
//			
//			System.out.println("\n=====>>> Executing @Before advice on setters");
//	
//	
//	}
//		
//	@Before("forEmploee_detailsall() && !(forEmploee_detailssettersmethod() || forEmploee_detailsgettersmethod())")
//	public void notForGettersSetters(JoinPoint theJoinPoint) {
//		
//		
//			MethodSignature methodSig=(MethodSignature) theJoinPoint.getSignature();
//			
//			Object []args=theJoinPoint.getArgs();
//			
//			
//			System.out.println("\n=====>>> Executing @Before advice on " + methodSig + "with args" + args);
//			
//			
//			
//			
//		}
//	
//
////	@AfterReturning("execution(* com.birdeye.employee_details.*.*(..)")
//	
//	@AfterReturning(pointcut="execution(forEmployeeDetails())",returning="result")
//	public void afterReturnAll(JoinPoint theJoinPoint,List<Employee> result)
//	{
//		System.out.println(result);
//	}
	
//	@AfterException(pointcut="execution(forEmployeeDetails())",throwing="theExc")
//	public void afterReturnAll(JoinPoint theJoinPoint,Throwable theExc)
//	{
//		System.out.println(theExc );
//	}
	
	
	
	
	
		
	
	
}



