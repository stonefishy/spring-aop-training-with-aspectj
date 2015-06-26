package spring.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyLogging {

	@Pointcut("execution(* spring.test.aop.MyData.saveData(..))")
	private void saveDataPointcut() {

	}

	@Before("saveDataPointcut()")
	public void beforeSaveData() {
		System.out.println("The Data is going to be saved");
	}

	@After("saveDataPointcut()")
	public void afterSaveData() {
		System.out.println("The Data has been saved");
	}

	@Before("execution(* spring.test.aop.MyData.getData())")
	public void beforeGetData() {
		System.out.println("Ready to get the data");
	}

	@After("execution(* spring.test.aop.*.getData())")
	public void afterGetData() {
		System.out.println("The data has been got");
	}

	@AfterReturning(pointcut = "execution(* spring.test.aop.MyData.getData())", returning = "returnValue")
	public void afterReturningAdvice(Object returnValue) {
		System.out.println(String.format("The return value is: %s ",
				returnValue.toString()));
	}

	@AfterThrowing(pointcut = "execution(* spring.test.aop.*.concatData(..))", throwing = "ex")
	public void throwingAdvice(NullPointerException ex) {
		System.out.println(String.format("Throw exception is: %s",
				ex.getMessage()));
	}

	@Around("saveDataPointcut()")
	public void aroundAdvice(ProceedingJoinPoint point) throws Throwable {
		System.out.println("Saving Data....");

		point.proceed();

		System.out.println("Saved Data!");
	}

}
