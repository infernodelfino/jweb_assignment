package fa.training.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jFactory {
	private static Logger logger = Logger.getLogger(Log4jFactory.class);

	public static Logger getLogger() {
		PropertyConfigurator.configure(Log4jFactory.class.getResourceAsStream("log4j.properties"));
		return logger;
	}

}