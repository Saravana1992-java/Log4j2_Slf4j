package com.logicbig.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {

	private static Logger LOGGER = LoggerFactory.getLogger(MyClass.class);

	public static void writeSB() {
		try {
			// LOGGER = LoggerFactory.getLogger("Batch.EDMDocUpload");
			List<String> inputLoggerList = Files.readAllLines(Paths.get("C:/temp/myfile.txt"));
			for (String line : inputLoggerList) {
				if (line == null || line.isEmpty()) {
					System.out.println("log4j2Logger.append(System.lineSeparator());");
				} else {
					System.out.println("log4j2Logger.append(\"" + line + "\");");
				}
				System.out.println("log4j2Logger.append(System.lineSeparator());");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void checkDelimitterLines(){
		StringBuilder log4j2Logger = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			log4j2Logger.append("sample text "+i);
			log4j2Logger.append(System.lineSeparator());
			log4j2Logger.append("sample text "+i+1);
			log4j2Logger.append(System.lineSeparator());
		}
		String content = log4j2Logger.toString();
		System.out.println(content);
		
		String path = "C:\\temp\\out.txt";
		try {
			Files.write( Paths.get(path), content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String getTemplate() {
		StringBuilder log4j2Logger = new StringBuilder();
		log4j2Logger.append("appender.%s.type = RollingFile");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.name = %s");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.fileName = ${%s}");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.filePattern = ${%s}.%d{yyyy-MM-dd}");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.layout.type = PatternLayout");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.layout.pattern = %-10.30X{server} %-4.4X{user} %d{ISO8601} %p %m%n");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.policies.type = Policies");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.policies.time.type = TimeBasedTriggeringPolicy");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.policies.time.interval = 1");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.policies.time.modulate = true");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.strategy.type = DefaultRolloverStrategy");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("appender.%s.strategy.max = 15");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.name = Batch.CovgTransformation");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.level = ${%s.level}");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.additivity = ${additivity}");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.appenderRefs = %s,stdout");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.appenderRefs.level = ${%s.level}");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.appenderRef.%s.ref = %s");
		log4j2Logger.append(System.lineSeparator());
		log4j2Logger.append("logger.%s.appenderRef.stdout.ref = STDOUT");
		log4j2Logger.append(System.lineSeparator());
		return log4j2Logger.toString();
	}

	public static void appendSyslogLog4j2() {
		LOGGER.info("mtd name = log4j1Tologg4j2");
		String inputFileName = "C:/temp/log4j2.properties";
		String outputFileName = "C:/temp/log4j-TEST.properties";
		try {
			List<String> inputLoggerList = Files.readAllLines(Paths.get(inputFileName));
			List<String> inputLoggerList2 = new ArrayList<>();
			StringBuilder data = new StringBuilder();
			for (String line : inputLoggerList) {
				if (line.contains(",stdout")) {
					line = line + ",syslog";
				}
				if (line.contains(".appenderRef.stdout.ref = STDOUT")) {
					String syslogAppenderRef = line.replace(".appenderRef.stdout.ref = STDOUT",
							".appenderRef.syslog.ref = syslog");
					line = line + System.lineSeparator() + syslogAppenderRef;
				}
				inputLoggerList2.add(line);
				data.append(line);
			}
			File outputFile = new File(outputFileName);
			if (outputFile.createNewFile()) {
				LOGGER.info("mtd name = log4j1Tologg4j2 " + outputFile.getName() + " was created.");
			} else {
				outputFile.delete();
				LOGGER.info("mtd name = log4j1Tologg4j2 " + outputFile.getName() + " was deleted.");
				boolean b = outputFile.createNewFile();
				LOGGER.info("mtd name = log4j1Tologg4j2 " + outputFile.getName() + " was created. " + b);
			}
			// FileUtils.writeLines(new File("output.txt"), encoding, list);
			// writToFile(outputFileName,data.toString());
			writToFile(outputFileName, inputLoggerList2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writToFile(String fileName, String data) {
		try {
			Files.write(Paths.get(fileName), data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writToFile(String fileName, List<String> list) {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (String str : list) {
				writer.write(str + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void log4j1Tologg4j2() {
		LOGGER.info("mtd name = log4j1Tologg4j2");
		String log4j2Logger = getTemplate();
		// String inputFileName = "C:/temp/input.txt";
		// String inputFileName = "C:/temp/input_minimal.txt";
		String inputFileName = "C:/temp/inputs.txt";
		try {
			List<String> inputLoggerList = Files.readAllLines(Paths.get(inputFileName));
			int count = 1;
			List<LoggerLines> loggerLineList = new ArrayList<LoggerLines>();
			LoggerLines loggerLine = null;
			for (String line : inputLoggerList) {
				if (line != null && !line.isEmpty()) {
					if (count == 1) {
						loggerLine = new LoggerLines();
					} else if (count % 7 == 0) {
						loggerLineList.add(loggerLine);
						loggerLine = new LoggerLines();
					}
					if (line.contains("######################")) {
						loggerLine.setTitle(line);
					} else if (line.contains("log4j.category.")) {
						String s = "log4j.category.";
						String loggerName = line.substring(s.length(), line.indexOf("="));
						String loggerLevel = line.substring(line.indexOf("=") + 1, line.indexOf(","));
						String appenderRef = line.substring(line.indexOf(",") + 1);
						loggerLine.setLoggerName(loggerName.trim());
						loggerLine.setLoggerLevel(loggerLevel.trim());
						loggerLine.setAppenderRef(appenderRef.trim());
					} else if (line.contains("/ECOS_NAS/")) {
						String fileName = line.substring(line.indexOf("/ECOS_NAS/"));
						fileName = fileName.replace("${app.environment}", "${appEnv}");
						fileName = fileName.replace("${jboss.jvm.name}", "${jbossJVMname}");
						loggerLine.setFileName(fileName);
					}
					count++;
				}
			}
			System.out.println("loggerLines size = " + loggerLineList.size());
			List<Integer> filters = new ArrayList<Integer>(Arrays.asList(5));
			for (int i = 1; i <= 5; i++) {
				if (filters.contains(i)) {
					log4j1Tologg4j2Processor(i, loggerLineList, log4j2Logger);
				}
				// System.out.println("i = " + i + " filters = " + filters);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void log4j1Tologg4j2Processor(int option, List<LoggerLines> loggerLineList, String log4j2Logger) {
		StringBuilder sb = new StringBuilder();
		String formattedString;
		switch (option) {
		case 1:
			for (LoggerLines loggerLines : loggerLineList) {
				sb.append(loggerLines.getTitle());
				sb.append(System.lineSeparator());
				formattedString = log4j2Logger.replace("%s", loggerLines.getAppenderRef());
				formattedString = formattedString.replace("Batch.CovgTransformation", loggerLines.getLoggerName());
				sb.append(formattedString);
				sb.append(System.lineSeparator());
			}
			break;
		case 2:
			sb = new StringBuilder();
			for (LoggerLines loggerLines : loggerLineList) {
				formattedString = String.format("property.%s.level = %s", loggerLines.getAppenderRef(),
						loggerLines.getLoggerLevel());
				sb.append(formattedString);
				sb.append(System.lineSeparator());
			}
			break;
		case 3:
			sb = new StringBuilder();
			for (LoggerLines loggerLines : loggerLineList) {
				formattedString = String.format("property.%s = %s", loggerLines.getAppenderRef(),
						loggerLines.getFileName());
				sb.append(formattedString);
				sb.append(System.lineSeparator());
			}
			break;
		case 4:
			for (LoggerLines loggerLines : loggerLineList) {
				sb.append(",");
				sb.append(loggerLines.getAppenderRef());
			}
			break;
		case 5:
			for (LoggerLines loggerLines : loggerLineList) {
				LOGGER = LoggerFactory.getLogger(loggerLines.getLoggerName());
				String LoggerName = loggerLines.getLoggerName();
				
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("Sample info message "+LoggerName);
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Sample debug message "+LoggerName);
				}
				if (LOGGER.isErrorEnabled()) {
					LOGGER.error("Sample error message "+LoggerName);
				}
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Sample trace message "+LoggerName);
				}

				// System.out.println(loggerLines.toString());
			}
			break;

		default:
			break;
		}
		System.out.println(sb.toString());
	}

}