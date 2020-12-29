//package com.birdeye.employee_details;
//
//import java.util.List;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Component;
//
//import javassist.bytecode.SignatureAttribute.MethodSignature;
//
//@Aspect
//@Component
//public class MyDemoLoggingAspect {
//
//	// this is where we add all of our related advices for logging
//	
//	// let's start with an @Before advice
//
//	@Pointcut("execution(* com.birdeye.employee_details.HomeController.fetch*(*))")//pointcut for all getters
//	public void forEmploee_detailsgettersmethod() {}
//	
////	
////	@Pointcut("execution(* com.birdeye.employee_details.HomeController.set*(*))")//pointcut for all getters
////	public void forEmploee_detailssettersmethod() {}
//////	
////	@Pointcut("execution(* com.birdeye.employee_details.HomeController.*(*))")//pointcut for all getters
////	public void forEmploee_detailsall() {}
//	
////	
////	
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
////	
////
//////	@AfterReturning("execution(* com.birdeye.employee_details.*.*(..)")
////	
////	@AfterReturning(pointcut="execution(forEmployeeDetails())",returning="result")
////	public void afterReturnAll(JoinPoint theJoinPoint,List<Employee> result)
////	{
////		System.out.println(result);
////	}
//	
////	@AfterException(pointcut="execution(forEmployeeDetails())",throwing="theExc")
////	public void afterReturnAll(JoinPoint theJoinPoint,Throwable theExc)
////	{
////		System.out.println(theExc );
////	}
//	
//	
//		
//	
//	
//}
//
//
//
