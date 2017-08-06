package com.ck.aopTest.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAopSave
{
	@Pointcut("execution(public * com.ck.aopTest.dao.quizarium.*.save*(..))")
	private void aopTest() {

	}

	@Before("aopTest()")
	public void before() {
		System.out.println("[before]调用dao方法前拦截" + new Date().getTime());
	}

	@After("aopTest()")
	public void after() {
		//System.out.println("[after]"+user);
		System.out.println("[after]调用dao方法之后拦截" + new Date().getTime());
	}

	@Around("aopTest()")
	public void around(ProceedingJoinPoint pdj) {
		System.out.println("[around]调用dao之前的环绕拦截" + new Date().getTime());
		try
		{
			pdj.proceed();
		} catch (Throwable e)
		{
			e.printStackTrace();
		}
		System.out.println("[around]调用dao之后的环绕拦截" + new Date().getTime());
	}

}
