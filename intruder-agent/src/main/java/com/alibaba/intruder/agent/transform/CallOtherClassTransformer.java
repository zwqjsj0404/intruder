package com.alibaba.intruder.agent.transform;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import com.alibaba.intruder.agent.util.Logger;

public class CallOtherClassTransformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		Logger.info("CallOtherClassTransformer:: transform class...");
		return null;
	}

}
