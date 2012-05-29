package com.alibaba.intruder.agent.util;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.intruder.agent.core.Parameters;
import com.alibaba.intruder.agent.core.Parameters.Type;

/**
 * ParameterReader, read parameters from the giving file path
 * 
 * all Exception is throw to caller
 * 
 * @author zili.dengzl
 * 
 */
public class ParameterReader {
	public static Properties readProperties(String filePath) throws Exception {
		Properties props = new Properties();
		FileInputStream in = new FileInputStream(filePath);
		props.load(in);
		Logger.debug(props.toString());
		return props;
	}

	public static Parameters readParameters(String filePath) throws Exception {
		Parameters params = new Parameters();
		Properties props = readProperties(filePath);

		params.setLoglevel(props.getProperty("logLevel"));
		params.setType(Type.valueOf(props.getProperty("type")));
		params.setTargetClassName(props.getProperty("targetClassName"));

		params.setNewClassPath(readURLs(props));
		params.setNewClassFullName(props
				.getProperty("loadNewClass.newClassFullName"));

		return params;
	}

	private static URL[] readURLs(Properties props)
			throws MalformedURLException {
		String classPathStr = props.getProperty("loadNewClass.newClassPath");
		String[] classpaths = classPathStr.split(":");
		List<URL> urls = new ArrayList<URL>();
		for (String cp : classpaths) {
			urls.add(new URL("file:" + cp));
		}
		return urls.toArray(new URL[urls.size()]);
	}
}
