package com.iridiumbyte.poc.chat.server.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iridiumbyte.poc.chat.server.util.ObjectMapperFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import static com.iridiumbyte.poc.chat.server.util.ExceptionUtil.unchecked;

public class MessageEncoder implements Encoder.Text<Message> {

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
	public String encode(Message object) {
		return unchecked(() -> objectMapper.writeValueAsString(object));
	}

}
