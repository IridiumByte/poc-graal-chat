package com.iridiumbyte.poc.chat.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class ChatWebSocketClient extends WebSocketClient {

	private static final Logger log = LoggerFactory.getLogger(ChatWebSocketClient.class);

	private final MessageHandler messageHandler;

	private ChatWebSocketClient(String uri, MessageHandler messageHandler) {
		super(URI.create(uri));
		this.messageHandler = messageHandler;
	}

	static ChatWebSocketClient connect(String uri, MessageHandler messageHandler) {
		try {
			ChatWebSocketClient client = new ChatWebSocketClient(uri, messageHandler);
			client.connectBlocking();
			return client;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		log.info("Established connection with server");
	}

	@Override
	public void onMessage(String msg) {
		messageHandler.onMessage(msg);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		log.info("Connection was closed. Reason: {}; Code: {}", reason, code);
	}

	@Override
	public void onError(Exception e) {
		log.error("Error occurred", e);
	}

}
