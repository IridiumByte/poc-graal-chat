package com.iridiumbyte.poc.chat.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iridiumbyte.poc.chat.server.util.ObjectMapperFactory;

import javax.inject.Singleton;

public class ObjectMapperConfig {

	@Singleton
	public ObjectMapper objectMapper() {
		return ObjectMapperFactory.defaultObjectMapper();
	}

}
