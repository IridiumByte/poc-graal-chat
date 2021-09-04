package com.iridiumbyte.poc.chat.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iridiumbyte.poc.chat.api.client.ChannelType;
import com.iridiumbyte.poc.chat.api.client.ClientMessage;
import com.iridiumbyte.poc.chat.api.client.MessageType;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.client.socket.ChatWebSocketClient;
import com.iridiumbyte.poc.chat.client.socket.ConnectionProperties;
import com.iridiumbyte.poc.chat.client.socket.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatClient {

	private static final Logger log = LoggerFactory.getLogger(ChatClient.class);

	private final ChatWebSocketClient webSocketClient;
	private final ObjectMapper objectMapper;

	private final MessageHandler messageHandler;

	public ChatClient(ConnectionProperties connectionProperties, MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
		this.objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		this.webSocketClient = ChatWebSocketClient.connect(connectionProperties, this::acceptMessage);
		fetchRoomList();
	}

	public void sendMessage(ChannelType type, String targetRoom, String content) {
		ClientMessage message = new ClientMessage(type, MessageType.SEND, targetRoom, content);
		webSocketClient.send(serialize(message));
	}

	public void joinRoom(String targetRoom) {
		ClientMessage message = new ClientMessage(ChannelType.ROOM, MessageType.JOIN, targetRoom, null);
		webSocketClient.send(serialize(message));
	}

	private void acceptMessage(String message) {
		ServerMessage serverMessage = deserialize(message, ServerMessage.class);
		if ("SERVER".equalsIgnoreCase(serverMessage.author)) {
			return;
		}

		messageHandler.onMessage(serverMessage);
	}

	private void fetchRoomList() {
		// TODO: 28.08.2021
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
			log.info("ObjectMapper: {}", objectMapper);
			return objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

}
