package com.iridiumbyte.poc.chat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{room}/{username}")
@ApplicationScoped
public class ChatEndpoint {

	private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);

	private final ChatServer chatServer = new ChatServer();

	@OnOpen
	public void onOpen(Session session, @PathParam("room") String room, @PathParam("username") String username) {
		chatServer.join(room, user(username, session));
	}

	@OnClose
	public void onClose(Session session, @PathParam("room") String room, @PathParam("username") String username) {
		chatServer.leave(room, user(username, session));
	}

	@OnError
	public void onError(Session session, @PathParam("room") String room, @PathParam("username") String username, Throwable throwable) {
		log.error("Some error", throwable);
		chatServer.leave(room, user(username, session));
	}

	@OnMessage
	public void onMessage(Session session, @PathParam("room") String room, @PathParam("username") String username, String message) {
		chatServer.sendMessage(room, user(username, session), message);
	}

	private ChatUser user(String username, Session session) {
		return new ChatUser(username, session);
	}

}
