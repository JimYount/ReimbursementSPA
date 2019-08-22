package com.revature.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class LogUtil {
	public static void logException(Exception except, @SuppressWarnings("rawtypes") Class logClass) {
		Logger log = Logger.getLogger(logClass);
		log.error(except.getClass() + ": " + except.getMessage());
		for (StackTraceElement sTrace : except.getStackTrace()) {
			log.warn(sTrace.getLineNumber() + ": " + sTrace.getClassName());
		}
	}

	public static void rollback(SQLException except, Connection connect, @SuppressWarnings("rawtypes") Class logClass) {
		logException(except, logClass);
		try {
			connect.rollback();
		} catch (Exception except1) {
			logException(except1, logClass);
		}
	}
}
