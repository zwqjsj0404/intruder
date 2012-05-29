/**
 * Copyright 2012 zili.dengzl. All Rights Reserved
 */
package sample.test;


/**
 * @author zili.dengzl
 * @time 2012-5-29 обнГ9:45:46
 * 
 */
public class SampleIntruderClass {
	public void execute(String args) throws Exception {
		System.out.println("args=" + args);
		System.out.println("SampleIntruderClass's classloader is " + SampleIntruderClass.class.getClassLoader());
	}

}
