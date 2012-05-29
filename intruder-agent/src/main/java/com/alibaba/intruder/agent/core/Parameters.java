package com.alibaba.intruder.agent.core;

import java.net.URL;

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

}
