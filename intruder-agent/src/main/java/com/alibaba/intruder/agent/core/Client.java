package com.alibaba.intruder.agent.core;

import java.io.File;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author zili.dengzl
 * 
 *         <code>java  client.Client java-instrument.jar 1234</<code>
 * 
 * since client is package into java-instrument.jar ,we should use 
 * <code>java  -jar java-instrument.jar java-instrument.jar 1234</<code> aslo
 */
public class Client {

	/**
	 * @param args
	 *            :args[0]=agent-jar-path,args[1]=properties file,args[2]=pid
	 * 
	 */
	public static void main(String[] args) throws Exception {
		if (null == args || args.length < 3) {
			printUsage();
			return;
		}

		String jarFile = new File(args[0]).getAbsolutePath();
		String argsFile = args[1];
		String pid = args[2];

		// attach
		VirtualMachine vm = VirtualMachine.attach(pid);
		vm.loadAgent(jarFile, argsFile);

		System.out.println("attach to " + pid + " finish!");
	}

	private static void printUsage() {
		System.out
				.println("Usage: java -jar intruder-agent-0.1.jar intruder-agent-0.1.jar <argFilePath> <pid>");
		System.out.println("argFilePath: absolute path of a properties file");

		System.out
				.println("other problems, please contact zili.dengzl@alibaba-inc.com,dragon.caol@alibaba.com");

	}

}
