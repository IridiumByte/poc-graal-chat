package com.iridiumbyte.poc.chat.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChatServer {

	private final ConcurrentMap<String, ChatRoom> rooms;

	public ChatServer() {
		this.rooms = new ConcurrentHashMap<>();
	}

	public void join(String chatRoom, ChatUser chatUser) {
		rooms.computeIfAbsent(chatRoom, ChatRoom::new)
				.join(chatUser);
	}

	public void sendMessage(String chatRoom, ChatUser chatUser, String message) {
		rooms.get(chatRoom).sendPublicMessage(chatUser.userName + ": " + message);
	}

	public void leave(String chatRoom, ChatUser chatUser) {
		ChatRoom room = rooms.get(chatRoom);
		room.disconnect(chatUser);
		if (room.isEmpty()) {
			rooms.remove(chatRoom);
		}
	}

}
