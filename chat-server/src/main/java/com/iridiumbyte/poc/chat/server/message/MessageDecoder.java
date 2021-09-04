package com.iridiumbyte.poc.chat.server.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iridiumbyte.poc.chat.api.client.ClientMessage;
import com.iridiumbyte.poc.chat.server.util.ExceptionUtil;
import com.iridiumbyte.poc.chat.server.util.ObjectMapperFactory;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<ClientMessage> {

	private final ObjectMapper objectMapper = ObjectMapperFactory.defaultObjectMapper();

	@Override
	public ClientMessage decode(String s) {
		return ExceptionUtil.unchecked(() -> objectMapper.readValue(s, ClientMessage.class));
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
