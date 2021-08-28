package com.iridiumbyte.poc.chat.client.socket;

import com.iridiumbyte.poc.chat.api.ChannelType;
import com.iridiumbyte.poc.chat.api.MessageDto;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChatWebSocketClient extends WebSocketClient {

	private static final Logger log = LoggerFactory.getLogger(ChatWebSocketClient.class);

	private final MessageHandler messageHandler;
	private Random random = new Random(); // TODO: 28.08.2021 remove

	private ChatWebSocketClient(String uri, MessageHandler messageHandler) {
		super(URI.create(uri), Map.of("X-Auth", "mn-secret")); // TODO: 28.08.2021
		this.messageHandler = messageHandler;
	}

	public static ChatWebSocketClient connect(String uri, MessageHandler messageHandler) {
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
		// TODO: 28.08.2021 deserialize
		messageHandler.onMessage(new MessageDto(ChannelType.ROOM, null, "Room A", msg));
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
