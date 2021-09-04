package com.iridiumbyte.poc.chat.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iridiumbyte.poc.chat.api.client.ChannelType;
import com.iridiumbyte.poc.chat.api.client.ClientMessage;
import com.iridiumbyte.poc.chat.api.client.MessageType;
import com.iridiumbyte.poc.chat.api.server.ChannelId;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.client.model.ChatRoom;
import com.iridiumbyte.poc.chat.client.socket.ChatWebSocketClient;
import com.iridiumbyte.poc.chat.client.socket.ConnectionProperties;
import com.iridiumbyte.poc.chat.client.socket.MessageHandler;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ChatClient {

	private static final Logger log = LoggerFactory.getLogger(ChatClient.class);

	private final ChatWebSocketClient webSocketClient;
	private final ObjectMapper objectMapper;

	private final MessageHandler messageHandler;
	private final OkHttpClient client = new OkHttpClient();

	public ChatClient(ConnectionProperties connectionProperties, MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
		this.objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		this.webSocketClient = ChatWebSocketClient.connect(connectionProperties, this::acceptMessage);
	}

	public void sendMessage(ChannelType type, String targetRoom, String content) {
		ClientMessage message = new ClientMessage(type, MessageType.SEND, targetRoom, content);
		webSocketClient.send(serialize(message));
	}

	public void joinRoom(String targetRoom) {
		ClientMessage message = new ClientMessage(ChannelType.ROOM, MessageType.JOIN, targetRoom, null);
		webSocketClient.send(serialize(message));
	}

	public List<ChatRoom> fetchRoomList() {
		Request request = new Request.Builder()
				.url("http://localhost:8080/chat/channels")
				.build();

		try {
			Response response = client.newCall(request).execute();
			String body = response.body().string();
			log.info(body);
			List<ChannelId> channels = objectMapper.readValue(body, new TypeReference<>() {});

			return channels.stream()
					.map(channel -> new ChatRoom(channel.channelType, channel.channelName))
					.collect(toList());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private void acceptMessage(String message) {
		ServerMessage serverMessage = deserialize(message, ServerMessage.class);
		if ("SERVER".equalsIgnoreCase(serverMessage.author)) {
			return;
		}

		messageHandler.onMessage(serverMessage);
	}

	private String serialize(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private <T> T deserialize(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

}
