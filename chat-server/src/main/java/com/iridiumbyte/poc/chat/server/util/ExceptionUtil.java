package com.iridiumbyte.poc.chat.server.util;

import java.util.concurrent.Callable;

public class ExceptionUtil {

	public static <T> T unchecked(Callable<T> throwingException) {
		try {
			return throwingException.call();
		} catch (Exception e) {
			throw new RuntimeException("Unchecked", e);
		}
	}

}
