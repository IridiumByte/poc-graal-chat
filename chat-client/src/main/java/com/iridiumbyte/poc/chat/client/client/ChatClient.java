package com.iridiumbyte.poc.chat.client.client;

import com.iridiumbyte.poc.chat.api.client.ChannelType;
import com.iridiumbyte.poc.chat.api.client.ClientMessage;
import com.iridiumbyte.poc.chat.api.client.MessageType;
import com.iridiumbyte.poc.chat.client.socket.ChatWebSocketClient;
import com.iridiumbyte.poc.chat.client.socket.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ChatClient {

	private static final Logger log = LoggerFactory.getLogger(ChatClient.class);

	private final ChatWebSocketClient webSocketClient;

	private List<String> rooms;

	public ChatClient(String uri, MessageHandler messageHandler) {
		this.webSocketClient = ChatWebSocketClient.connect(uri, messageHandler);
		fetchRoomList();
	}

	public void sendMessage(ChannelType type, String targetRoom, String content) {
		ClientMessage message = new ClientMessage(type, MessageType.SEND, targetRoom, content);
		// TODO: 28.08.2021 serialize and send
	}

	public void joinRoom(String targetRoom) {
		ClientMessage message = new ClientMessage(ChannelType.ROOM, MessageType.JOIN, targetRoom, null);
		// TODO: 28.08.2021 serialize and send
	}

	public List<String> getRooms() {
		return rooms;
	}

	private void fetchRoomList() {
		// TODO: 28.08.2021
		rooms = List.of("Room A", "Room B (joined)");
	}

}
