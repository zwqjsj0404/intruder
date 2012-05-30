package com.alibaba.intruder.agent.transform;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import com.alibaba.intruder.agent.util.Logger;

/**
 * @author zili.dengzl
 * 
 */
public class DefaultTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader cl, String arg1, Class<?> arg2,
			ProtectionDomain arg3, byte[] classBytes)
			throws IllegalClassFormatException {
		Logger.info("DefaultTransformer:: transform class...");
		return null;

	}

}

