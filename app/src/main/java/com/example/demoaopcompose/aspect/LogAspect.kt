package com.example.demoaopcompose.aspect

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class LogAspect {
    // @Before("execution(* notifyAnalytics(..))||@annotation(LogAOP)")
    // @Before("execution(@LogAOP * *(..))") any method with AOP?
    // @Before("call( * com.example.demoaopcompose.UI.*(..))")// to the any function in UI........v
     //@Before("call( * androidx.navigation.NavController.*(..))")// to the call point in my code.v
     @Before("call( * androidx.navigation.*.*(..))")// .....................................v
    // @Before("call(* *.navigate(..))") // any method call not work with google
    // @Before("within(*.NavController)") // any method call
    // @Before("call(* navigate(..))") // not work
    // @Before("execution(* *(..))&& @annotation(LogAOP)") // not work
    // @Before("set(* *)&& @annotation(LogAOP)") // not work
    // @Before("get(* navController)") // not work

    // @Before("@within(LogAOP)")
    // @Before("@within(LogAOP) || @annotation(LogAOP)")
    fun before(joinPoint: JoinPoint) {
        Log.d(
            ">>AOP",
            "ON: ${joinPoint.target} FUN: ${joinPoint.signature.name} ARGS: ${extractArgs(joinPoint.args)}"
        )
    }

    private fun extractArgs(args: Array<Any>): String {
        return args.joinToString()
    }
}
