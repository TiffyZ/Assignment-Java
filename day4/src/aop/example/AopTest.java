package aop.example;

import aop.JdkAOPInvocationHandler;
import aop.MethodInvocation;
import aop.advice.*;

import java.lang.reflect.Proxy;

public class AopTest {
    public static void main(String[] args) {
        StudentService ss = (StudentService) Proxy.newProxyInstance(
                AopTest.class.getClassLoader(),
                new Class[]{StudentService.class},
                new JdkAOPInvocationHandler(new StudentServiceImpl(), new StudentAspect())
        );
        ss.print();
    }
}

/**
 * List = [after1 func, before1 func, before2 func, after2 func]
 *
 *      before1, before2, original instance, after1 / 2, after1 /2
 */
class StudentAspect {

    @After
    public void after1() {
        System.out.println("after one");
    }
    @Before
    public void before1() {
        System.out.println("before one");
    }

    @Around
    public Object around1(MethodInvocation mi) throws Throwable{
        System.out.println("before around one");
        Object res = mi.proceed();
        System.out.println("after around one");
        return res;
    }

    @AfterReturn
    public void afterReturn1(MethodInvocation mi){
        System.out.println("after return 1");
    }

    @AfterThrow
    public void afterThrow1(MethodInvocation mi){
        System.out.println("after throw 1");
    }

}

/**
 *  homework:
 *      provide two annotations in this aop library
 *      1. @AfterThrow : trigger aspect logic only if original method / function throws exception
 *      2. @AfterReturn : trigger aspect logic after the original method returns data
 */