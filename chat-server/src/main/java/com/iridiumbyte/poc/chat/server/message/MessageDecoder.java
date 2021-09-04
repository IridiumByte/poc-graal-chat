package com.iridiumbyte.poc.chat.server.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.server.util.ExceptionUtil;
import com.iridiumbyte.poc.chat.server.util.ObjectMapperFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<MessageDto> {

	private final ObjectMapper objectMapper = ObjectMapperFactory.defaultObjectMapper();

	@Override
	public MessageDto decode(String s) {
		return ExceptionUtil.unchecked(() -> objectMapper.readValue(s, MessageDto.class));
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

	@Override
	public void init(EndpointConfig config) {

	}

	@Override
	public void destroy() {

	}

}
