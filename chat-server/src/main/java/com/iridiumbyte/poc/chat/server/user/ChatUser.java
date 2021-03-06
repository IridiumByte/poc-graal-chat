package com.iridiumbyte.poc.chat.server.user;

import com.iridiumbyte.poc.chat.api.server.ServerMessage;

import javax.websocket.Session;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ChatUser {

	private final Username username;
	private final Session session;

	public ChatUser(Username username, Session session) {
		this.username = username;
		this.session = session;
	}

	public void sendMessage(ServerMessage message) {
		session.getAsyncRemote().sendObject(message, result -> {
			Throwable exception = result.getException();
			if (exception != null)
				throw new RuntimeException(exception);
		});
	}

	public Username getUsername() {
		return username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChatUser chatUser = (ChatUser) o;
		return username.equals(chatUser.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	public static class Username {

		private final String value;

		public Username(String value) {
			this.value = requireNonNull(value);
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Username username = (Username) o;
			return value.equals(username.value);
		}

		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

	}

}
