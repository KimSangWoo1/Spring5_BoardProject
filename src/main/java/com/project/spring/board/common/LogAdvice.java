package com.project.spring.board.common;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.logging.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	@Around(value = "execution(* com.project.spring.board..*Controller.*(..))"
					+"or execution(*  com.project.spring.board..*service..*Impl.*(..))"
					+"or execution(*  com.project.spring.board..*Mapper.*(..))")
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
			name="Mapper : ";
		}

		long end = System.currentTimeMillis();
		
		
		System.out.println(name+type+"."+joinPoint.getSignature().getName()+"()");
		System.out.println("Argument/Parameter : "+ Arrays.toString(joinPoint.getArgs()));
		System.out.println("Running Time : "+(end-start));
		System.out.println("-----------------------------------------------");

		// 타겟 메소드의 리턴값을 다시 반환한다. 필요 시 수정도 가능하다.
		return result;
	}
}
