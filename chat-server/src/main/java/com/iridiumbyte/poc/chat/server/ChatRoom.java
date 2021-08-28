package com.iridiumbyte.poc.chat.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRoom {

	private final String name;
	private final List<ChatUser> connectedUsers;
	private final List<String> messagesHistory;

	public ChatRoom(String name) {
		this.name = name;
		connectedUsers = Collections.synchronizedList(new ArrayList<>());
		messagesHistory = Collections.synchronizedList(new ArrayList<>());
	}

	public void join(ChatUser chatUser) {
		connectedUsers.add(chatUser);
		sendPublicMessage("User " + chatUser.userName + " joined to room " + name);
	}

	public void sendPublicMessage(String message) {
		messagesHistory.add(message);
		connectedUsers.forEach(user -> user.sendMessage(message));
	}

	public void disconnect(ChatUser chatUser) {
		connectedUsers.remove(chatUser);
		sendPublicMessage("User " + chatUser.userName + " left room " + name);
	}

	public boolean isEmpty() {
		return connectedUsers.isEmpty();
	}

}
