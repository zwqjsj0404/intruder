package com.alibaba.intruder.agent.util;


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

	public static void initLog(String logLevelConf) {

		if (null == logLevelConf) {
			System.out.println("loglevel is null, use default level info ");
			return;
		}

		try {
			Logger.setLevel(LEVEL.valueOf(logLevelConf));
			System.out.println("set log level to "+ logLevelConf);
		} catch (Exception e) {
			System.out.println(logLevelConf
					+ " is not a valid format, use default level info ");
		}
	}
}
