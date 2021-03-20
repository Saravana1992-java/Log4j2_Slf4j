package com.logicbig.example;

public class LoggerLines {

	private String title = "";
	private String fileName = "";
	private String loggerName = "";
	private String loggerLevel = "";
	private String appenderRef = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLoggerLevel() {
		return loggerLevel;
	}

	public void setLoggerLevel(String loggerLevel) {
		this.loggerLevel = loggerLevel;
	}

	public String getAppenderRef() {
		return appenderRef;
	}

	public void setAppenderRef(String appenderRef) {
		this.appenderRef = appenderRef;
	}

	@Override
	public String toString() {
		return "LoggerLines [title=" + title + ", fileName=" + fileName + ", loggerName=" + loggerName
				+ ", loggerLevel=" + loggerLevel + ", appenderRef=" + appenderRef + "]";
	}

}
