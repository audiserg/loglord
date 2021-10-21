package com.example.demoaopcompose.aspect

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class LogAspect {
    // @Before("execution(* notifyAnalytics(..))||@annotation(LogAOP)")
    // @Before("execution(@LogAOP * *(..))")
    // @Before("execution(* *(..))") not work
    @Before("@within(LogAOP)")
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
