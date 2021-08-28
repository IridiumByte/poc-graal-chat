package com.iridiumbyte.poc.chat.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatClient {

	private static final Logger log = LoggerFactory.getLogger(ChatClient.class);

	private final String username;
	private final ChatWebSocketClient webSocketClient;

	public ChatClient(String uri, String username) {
		this.webSocketClient = ChatWebSocketClient.connect(uri, this::consumeMessage);
		this.username = username;
	}

	public void sendMessage(String message) {
		webSocketClient.send(message);
	}

	void consumeMessage(String message) {
		log.info("Received message: {}", message);
	}

}
