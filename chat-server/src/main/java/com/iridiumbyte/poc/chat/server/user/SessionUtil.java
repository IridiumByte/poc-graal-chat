package com.iridiumbyte.poc.chat.server.user;

import com.iridiumbyte.poc.chat.server.user.ChatUser;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

import javax.websocket.Session;

public class SessionUtil {

	private static final String USERNAME_KEY = "chat-username";

	public static ChatUser saveUserInSession(Session session, Username username) {
		session.getUserProperties().put(USERNAME_KEY, username);
		return new ChatUser(username, session);
	}

	public static Username extractUsername(Session session) {
		return (Username) session.getUserProperties().get(USERNAME_KEY);
	}

}
