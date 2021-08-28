package com.iridiumbyte.poc.chat.server;

import com.google.gson.Gson;
import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.api.MessageType;
import com.iridiumbyte.poc.chat.server.channel.InMemoryActiveChannelDao;
import com.iridiumbyte.poc.chat.server.message.MessageEncoder;
import com.iridiumbyte.poc.chat.server.user.ChatUser;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;
import com.iridiumbyte.poc.chat.server.user.InMemoryActiveUserDao;
import com.iridiumbyte.poc.chat.server.user.SecretManager;
import com.iridiumbyte.poc.chat.server.user.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

import static com.iridiumbyte.poc.chat.server.message.MessageFactory.extractChannelId;
import static com.iridiumbyte.poc.chat.server.user.SessionUtil.extractUsername;

@ServerEndpoint(value = "/chat", configurator = ServerHandshakeConfigurator.class, encoders = MessageEncoder.class)
@ApplicationScoped
public class ChatEndpoint {

	private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);

	private final SecretManager secretManager = new SecretManager(
			Map.of("mk-secret", new Username("mk"), "mn-secret", new Username("mn"))
	);
	private final ChatServer chatServer = new ChatServer(new InMemoryActiveChannelDao(), new InMemoryActiveUserDao());
	private final Gson gson = new Gson();

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		String secret = (String) config.getUserProperties().get("X-Auth");
		Username user = secretManager.authenticate(secret);
		log.info("New session for " + user);
		ChatUser chatUser = SessionUtil.saveUserInSession(session, user);
		chatServer.join(chatUser);
	}

	@OnClose
	public void onClose(Session session) {
//		chatServer.leave(room, user(username, session));
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		log.error("Some error", throwable);
//		chatServer.leave();
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		MessageDto incoming = gson.fromJson(message, MessageDto.class);
		Username username = extractUsername(session);
		if (incoming.messageType == MessageType.JOIN) {
			chatServer.join(username, extractChannelId(incoming));
		} else {
			chatServer.sendMessage(username, incoming);
		}
	}

}
