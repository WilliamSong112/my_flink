//package org.apache.flink.cep;
//
//import groovy.lang.GroovyClassLoader;
//
//import java.lang.reflect.Method;
////import org.junit.Test;
//
//public class Test {
////	@Test
////	public void testGroovyClassLoader() throws Exception {
////
////
////
////	}
//
//	public static void main(String[] args) {
//		//groovy提供了一种将字符串文本代码直接转换成Java Class对象的功能
//		GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
//		//里面的文本是Java代码,但是我们可以看到这是一个字符串我们可以直接生成对应的Class<?>对象,而不需要我们写一个.java文件
//		Class<?> clazz = groovyClassLoader.parseClass("package com.xxl.job.core.glue;\n" +
//			"\n" +
//			"public class Main {\n" +
//			"\n" +
//			"    public int age = 22;\n" +
//			"    \n" +
//			"    public void sayHello() {\n" +
//			"        System.out.println(\"年龄是:\" + age);\n" +
//			"    }\n" +
//			"}\n");
//
//		Method method = clazz.getDeclaredMethod("sayHello");
//		Object invoke = method.invoke(clazz.newInstance());
//	}
//}
