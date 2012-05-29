package com.alibaba.intruder.agent.util;

import com.alibaba.intruder.agent.core.Parameters;

/**
 * a very very simple logger
 * 
 * @author zili.dengzl
 * 
 */
public class Logger {
	public enum LEVEL {
		error, info, debug
	};

	private static LEVEL level = LEVEL.info;

	public static LEVEL getLevel() {
		return level;
	}

	public static void setLevel(LEVEL level) {
		Logger.level = level;
	}

	public static void info(String msg) {
		sysoutLog(msg, LEVEL.info);
	}

	public static void debug(String msg) {
		sysoutLog(msg, LEVEL.debug);
	}

	public static void error(String msg) {
		sysoutLog(msg, LEVEL.error);
	}

	public static void error(String msg, Throwable e) {
		sysoutLog(msg, LEVEL.error);
		e.printStackTrace();
	}

	private static void sysoutLog(String msg, LEVEL requirelevel) {
		if (level.equals(requirelevel)) {
			System.out.println(msg);
		}
	}

	private static void initLog(Parameters parameters) {
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
