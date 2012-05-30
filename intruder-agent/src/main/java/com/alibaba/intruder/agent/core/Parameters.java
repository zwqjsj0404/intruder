package com.alibaba.intruder.agent.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.intruder.agent.util.Logger;

/**
 * @author zili.dengzl
 * 
 */
public class Parameters {
	public enum Type {
		loadNewClass, transformClass
	};

	private Type type;
	private String targetClassName;
	private String loglevel;

	private URL[] newClassPath;
	private String newClassFullName;
	private String newClassExecuteMethodArgs;

	private String transformer;
	private List<File> transformerLibFiles;

	public List<File> getTransformerLibFiles() {
		return transformerLibFiles;
	}

	public void setTransformerLibFiles(List<File> transformerLibFiles) {
		this.transformerLibFiles = transformerLibFiles;
	}

	public String getTransformer() {
		return transformer;
	}

	public void setTransformer(String transformer) {
		this.transformer = transformer;
	}

	public String getNewClassExecuteMethodArgs() {
		return newClassExecuteMethodArgs;
	}

	public void setNewClassExecuteMethodArgs(String newClassExecuteMethodArgs) {
		this.newClassExecuteMethodArgs = newClassExecuteMethodArgs;
	}

	public String getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public URL[] getNewClassPath() {
		return newClassPath;
	}

	public void setNewClassPath(URL[] newClassPath) {
		this.newClassPath = newClassPath;
	}

	public String getNewClassFullName() {
		return newClassFullName;
	}

	public void setNewClassFullName(String newClassFullName) {
		this.newClassFullName = newClassFullName;
	}

	/**
	 * read util of parameters
	 */
	private static final String NEW_CLASSPATH_SPLIT = "##";

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

		// basic
		params.setLoglevel(props.getProperty("logLevel"));
		params.setType(Type.valueOf(props.getProperty("type")));
		params.setTargetClassName(props.getProperty("targetClassName"));

		// loadNewClass
		params.setNewClassPath(readURLs(props));
		params.setNewClassFullName(props
				.getProperty("loadNewClass.newClassFullName"));
		params.setNewClassExecuteMethodArgs(props
				.getProperty("loadNewClass.executeMethodArgs"));

		// transformClass
		params.setTransformer(props.getProperty("transformClass.transformer"));
		params.setTransformerLibFiles(readLibFiles(props));

		return params;
	}

	private static List<File> readLibFiles(Properties props) {
		List<File> files = new ArrayList<File>();
		String filePathStr = props.getProperty("transformClass.libFilePath");
		String[] filePaths = filePathStr.split(NEW_CLASSPATH_SPLIT);
		for (String filePath : filePaths) {
			files.add(new File(filePath));
		}
		return files;
	}

	private static URL[] readURLs(Properties props)
			throws MalformedURLException {
		String classPathStr = props.getProperty("loadNewClass.newClassPath");
		String[] classpaths = classPathStr.split(NEW_CLASSPATH_SPLIT);
		List<URL> urls = new ArrayList<URL>();

		for (String cp : classpaths) {
			File tmpFile = new File(cp);
			urls.add(new URL("file:" + tmpFile.getAbsolutePath()));
		}

		return urls.toArray(new URL[urls.size()]);
	}

}
