package com.iridiumbyte.poc.chat.server.message;

import com.google.gson.Gson;
import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.server.util.GsonFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<MessageDto> {

	private final Gson gson = GsonFactory.defaultGson();

	public MessageDecoder() {
	}

	@Override
	public MessageDto decode(String s) throws DecodeException {
		return gson.fromJson(s, MessageDto.class);
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
