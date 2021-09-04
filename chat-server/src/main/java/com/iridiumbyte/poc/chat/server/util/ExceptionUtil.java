package com.iridiumbyte.poc.chat.server.util;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExceptionUtil {

	public static <T> T unchecked(Callable<T> throwingException) {
		try {
			return throwingException.call();
		} catch (Exception e) {
			throw new RuntimeException("Unchecked", e);
		}
	}

}
