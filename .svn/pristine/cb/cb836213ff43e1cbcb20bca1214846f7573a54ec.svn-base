package com.project.spring.board.common;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.mybatis.logging.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
@Aspect //AOP 기능 사용
public class LogAdvice {
	/*
	 @Around : 메서드 실행 전체를 앞, 뒤로 감싸서 특정한 기능을 실행할 수 있는 강력한 타입의 Advice
	 excution : Pointcut을 지정하는 문법으로 AspectJ 언어 문법
	 @Around return 타입은 반드시 Object
	 ProceedingJoinPoint : JoinPoint의 하위 인터페이스
	 proceed() : Advice를 실항하거나 , 실제 target 객체의 메서드를 실행하는 기능
	*/
	
	@Around(value = "execution(* com.project.spring.board.controller..*Controller.*(..))"
			+" or execution(*  com.project.spring.board.service..*Impl.*(..))"
			+" or execution(*  com.project.spring.board.mapper..*Mapper.*(..))")
	public Object afterGetFortuneAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("afterGetFortuneAdvice is executed after " + joinPoint.getSignature().toShortString());

		long start = System.currentTimeMillis();

		// 타겟 메소드의 실행하고 결과 값을 받아온다.
		Object result = joinPoint.proceed();

		String type= joinPoint.getSignature().getDeclaringTypeName();
		String name = "";
		
		if(type.contains("Controller")) {
			name="Controller : ";
		}else if(type.contains("Service")) {
			name="Service : ";
		}else if(type.contains("Mapper")) {
			name="Mapper : "; //Mepper 
		}

		long end = System.currentTimeMillis();
		
		//getSignature() : 실행하는 대상 객체의 메서드에 대한 정보를 가져옴
		System.out.println(name+type+"."+joinPoint.getSignature().getName()+"()");
		System.out.println("Argument/Parameter : "+ Arrays.toString(joinPoint.getArgs()));
		System.out.println("Running Time : "+(end-start));
		System.out.println("-----------------------------------------------");

		// 타겟 메소드의 리턴값을 다시 반환한다. 필요 시 수정도 가능하다.
		return result;
	}
	
	@Before("@annotation(Logging)")
	public void beforeLogPerf(JoinPoint joinPoint) throws Throwable{
		System.out.println("@Before :"+joinPoint.getSignature().getName()+" : before excute :");
	}
	
	@After("@annotation(Logging)")
	public void afterLogPerf(JoinPoint joinPoint) throws Throwable{
		System.out.println("@After :"+joinPoint.getSignature().getName()+" : After excute :");
	}
	
}


/* # AOP 용어
 * 1. Aspect :공통 관심사에 대한 추상적인 명칭, 예를 들어 로깅이나 보안, 트랜잭션과 같은 기능 자체에 대한 용어
 * 2. Advice : 실제로 기능을 구현한 객체
 * 3. Join points : 공통 관심사를 적용할 수 있는 대상, Spring AOP에서는 각 객체의 메서드가 해당됨
 * 4. Pointcuts : 여러 메서드 중 실제 Advice가 적용될 메서드
 * 5. target : 대상 메서드가 가지는 객체
 * 6. Proxy : Advice가 적용되었을 때 만들어지는 객체
 * 7. Introduction : target에는 없는 새로운 메서드나 인스턴스 변수를 추가하는 기능
 * 8. Weaving : Adivice나 target이 결합되어서 프록시 객체를 만드는 과정
 */