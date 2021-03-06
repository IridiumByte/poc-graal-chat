package com.iridiumbyte.poc.chat.server.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.server.util.ObjectMapperFactory;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import static com.iridiumbyte.poc.chat.server.util.ExceptionUtil.unchecked;

public class MessageEncoder implements Encoder.Text<ServerMessage> {

	private final ObjectMapper objectMapper = ObjectMapperFactory.defaultObjectMapper();

	public MessageEncoder() {
	}

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(ServerMessage object) {
		return unchecked(() -> objectMapper.writeValueAsString(object));
	}

}
