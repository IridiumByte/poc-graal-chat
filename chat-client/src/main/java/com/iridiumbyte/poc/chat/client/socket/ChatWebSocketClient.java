package com.iridiumbyte.poc.chat.client.socket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;

public class ChatWebSocketClient extends WebSocketClient {

	private static final Logger log = LoggerFactory.getLogger(ChatWebSocketClient.class);

	private final WebSocketMessageHandler messageHandler;

	private ChatWebSocketClient(ConnectionProperties connectionProperties, WebSocketMessageHandler messageHandler) {
		super(
				URI.create(connectionProperties.url),
				Map.of("X-Auth", connectionProperties.secret)
		);
		this.messageHandler = messageHandler;
	}

	public static ChatWebSocketClient connect(ConnectionProperties connectionProperties, WebSocketMessageHandler messageHandler) {
		try {
			ChatWebSocketClient client = new ChatWebSocketClient(connectionProperties, messageHandler);
			client.connectBlocking();
			return client;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		log.debug("Established connection with server");
	}

	@Override
	public void onMessage(String msg) {
		log.debug("Message: {}", msg);
		messageHandler.onMessage(msg);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		log.debug("Connection was closed. Reason: {}; Code: {}", reason, code);
	}

	@Override
	public void onError(Exception e) {
		log.error("Error occurred", e);
	}

	@Override
	public void send(String text) {
		log.debug("Sending msg: {}", text);
		super.send(text);
	}

	public interface WebSocketMessageHandler {
		void onMessage(String msg);
	}

}
