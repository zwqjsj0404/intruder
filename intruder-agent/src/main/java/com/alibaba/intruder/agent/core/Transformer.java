package com.alibaba.intruder.agent.core;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author zili.dengzl
 *
 */
public class Transformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader cl, String arg1, Class<?> arg2,
			ProtectionDomain arg3, byte[] arg4)
			throws IllegalClassFormatException {
		System.out.println("transform class..." + cl);
		return null;
	}

}
