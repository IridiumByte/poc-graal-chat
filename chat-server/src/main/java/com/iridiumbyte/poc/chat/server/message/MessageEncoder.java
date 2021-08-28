package com.iridiumbyte.poc.chat.server.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iridiumbyte.poc.chat.server.util.GsonFactory;
import com.iridiumbyte.poc.chat.server.util.GsonOffsetDateTimeConverter;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.time.OffsetDateTime;

public class MessageEncoder implements Encoder.Text<Message> {

	private final Gson gson = GsonFactory.defaultGson();

	public MessageEncoder() {
	}

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(Message object) throws EncodeException {
		return gson.toJson(object);
	}

}
