package com.alibaba.intruder.agent.core;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.Arrays;

import com.alibaba.intruder.agent.core.Parameters.Type;
import com.alibaba.intruder.agent.util.Logger;
import com.alibaba.intruder.agent.util.Logger.LEVEL;
import com.alibaba.intruder.agent.util.ParameterReader;

/**
 * now support load a new class into the classloader who load the target class,
 * or transform the target class by bytecode
 * 
 * AgentMain will be load to the AppClassloader of JDK
 * 
 * the classloader of
 * 
 * @author zili.dengzl
 * 
 */
public class AgentMain {

	static Parameters parameters;

	public static void agentmain(String filePath, Instrumentation inst)
			throws Exception, UnmodifiableClassException, InterruptedException {
		System.out.println("agentmain start...");

		parameters = ParameterReader.readParameters(filePath);

		initLog();

		loadAgentForClass(inst);

		Logger.info("Agent done!");
	}

	private static void loadAgentForClass(Instrumentation inst) {
		Class<?>[] classes = inst.getAllLoadedClasses();
		for (Class<?> c : classes) {
			if (isTargetClass(c)) {
				try {
					Logger.info("transform class:" + c);
					handle(inst, c);
				} catch (Exception e) {
					Logger.error("error while transform" + c.getName(), e);
				}
				break;
			}
		}
	}

	private static void handle(Instrumentation inst, Class<?> c)
			throws Exception {
		
		URLClassLoader ucl = (URLClassLoader) c.getClassLoader();
		addURLToClassLoader(ucl);

		if (parameters.getType().equals(Type.loadNewClass)) {

			Logger.info("URLClassLoader " + ucl + " loaded "
					+ parameters.getNewClassFullName());
			Class<?> clazz = ucl.loadClass(parameters.getNewClassFullName());
			Method method = clazz.getMethod("execute");
			method.invoke(clazz.newInstance());

		} else if (parameters.getType().equals(Type.transformClass)) {

			ClassFileTransformer transformer = new Transformer();
			inst.addTransformer(transformer, true);
			inst.retransformClasses(c);
			inst.removeTransformer(transformer);
		}

	}

	/**
	 * c's ClassLoader must be URLClassLoader, or terminate\
	 * 
	 * @param c
	 * @return
	 * @throws Exception
	 */
	private static URLClassLoader addURLToClassLoader(URLClassLoader ucl)
			throws Exception {
		// URLClassLoader ucl = new URLClassLoader(parameters.getNewClassPath(),
		// ucl); DO NOT DELETE

		Logger.debug("before add:" + Arrays.asList(ucl.getURLs()).toString());

		for (Class<?> clazz = ucl.getClass(); clazz != null
				&& clazz != SecureClassLoader.class; clazz = clazz
				.getSuperclass()) {

			try {
				Method addURLMethod = clazz.getDeclaredMethod("addURL",
						URL.class);
				addURLMethod.setAccessible(true);

				for (URL url : parameters.getNewClassPath()) {
					addURLMethod.invoke(ucl, url);
				}

				Logger.debug("after add:"
						+ Arrays.asList(ucl.getURLs()).toString());

				return ucl;
			} catch (Exception e) {
				// ignore
				// e.printStackTrace();
			}

		}

		throw new Exception("method addURL() is not found");
	}

	private static boolean isTargetClass(Class<?> c) {
		return c.getName().equalsIgnoreCase(parameters.getTargetClassName());
	}

	private static void initLog() {
		String levelConf = parameters.getLoglevel();

		if (null == levelConf) {
			Logger.setLevel(LEVEL.info);
			return;
		}

		try {
			Logger.setLevel(LEVEL.valueOf(levelConf));
		} catch (Exception e) {
			System.out.println(levelConf
					+ " is not a valid format, log level is set to info ");
			Logger.setLevel(LEVEL.info);
		}
	}
}
