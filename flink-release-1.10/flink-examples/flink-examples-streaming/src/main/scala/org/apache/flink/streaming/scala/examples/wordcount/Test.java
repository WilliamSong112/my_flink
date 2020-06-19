package org.apache.flink.streaming.scala.examples.wordcount;


import groovy.lang.GroovyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.flink.streaming.scala.examples.wordcount.ceputil;
public class Test {

//	@Test
//	public void testGroovyClassLoader() throws Exception {
//
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
//		method.invoke(clazz.newInstance());
//
//	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
		//字符串转java
		//groovy提供了一种将字符串文本代码直接转换成Java Class对象的功能
		GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
		//里面的文本是Java代码,但是我们可以看到这是一个字符串我们可以直接生成对应的Class<?>对象,而不需要我们写一个.java文件
		Class<?> clazz = groovyClassLoader.parseClass("package com.xxl.job.core.glue;\n" +
			"\n" +
			"import org.apache.flink.streaming.scala.examples.wordcount.ceputil;" +
			"public class Main {\n" +
			"\n" +
			"    public int age = 22;\n" +
			"    \n" +
			"    public void sayHello() {\n" +
			"        System.out.println(\"年龄是:\" + age);\n" +
			"ceputil.sayhello();" +
			"    }\n" +
			"}\n");
		Class<?> clazz2 = groovyClassLoader.parseClass("import com.hhz.flink.cep.pojo.LoginEvent\n" +
			"import com.hhz.flink.cep.patterns.conditions.LogEventCondition\n" +
			"import org.apache.flink.cep.scala.pattern.Pattern\n" +
			"import org.apache.flink.streaming.api.windowing.time.Time\n" +
			"def getP(){\n" +
			"    return Pattern.<LoginEvent>begin(\"begin\")\n" +
			"    .where(new LogEventCondition(\"getField(eventType)==\\\"fail\\\"\"))\n" +
			"    .next(\"next\").where(new LogEventCondition(\"getField(eventType)==\\\"fail\\\"\"))\n" +
			"    .times(2).within(Time.seconds(3))\n" +
			"}");

		Method method = clazz.getDeclaredMethod("sayHello");
		try {
			method.invoke(clazz.newInstance());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
//		ceputil.sayhello();
	}
}
