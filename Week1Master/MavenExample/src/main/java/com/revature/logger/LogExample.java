package com.revature.logger;

import org.apache.log4j.Logger;

public class LogExample {
	private static Logger log = Logger.getLogger(LogExample.class);
	public static void main(String[] args) {
		log.trace("This is a message at TRACE");
		log.debug("A debug log");
		log.info("An info log");
		log.warn("Warning");
		log.error("Something went wrong.");
		log.fatal("Something went very wrong.");
	}

}
