package com.iridiumbyte.poc.chat.server.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFactory {

	public static ObjectMapper defaultObjectMapper() {
		return new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//				.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
	}

}
