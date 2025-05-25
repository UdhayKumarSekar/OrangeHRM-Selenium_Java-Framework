package com.orangehrm.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManagerOHRM {

//	this  method returns logger instances like information for the provided class
	public static Logger getLogger(Class<?> clazz) {
		
		return LogManager.getLogger();
	}
}
