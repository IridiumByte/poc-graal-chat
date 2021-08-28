package com.iridiumbyte.poc.chat.server;

import javax.websocket.Session;
import java.util.Objects;

public class ChatUser {

	public final String userName;
	private final Session session;

	public ChatUser(String userName, Session session) {
		this.userName = userName;
		this.session = session;
	}

	public void sendMessage(String message) {
		session.getAsyncRemote().sendObject(message, result -> {
			Throwable exception = result.getException();
			if (exception != null)
				throw new RuntimeException(exception);
		});
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChatUser chatUser = (ChatUser) o;
		return userName.equals(chatUser.userName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}

}
