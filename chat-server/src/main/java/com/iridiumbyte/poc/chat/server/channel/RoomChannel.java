package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.server.message.Message;
import com.iridiumbyte.poc.chat.server.user.ChatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.iridiumbyte.poc.chat.server.message.MessageFactory.serverMessage;

public class RoomChannel implements Channel {

	private static final Logger log = LoggerFactory.getLogger(RoomChannel.class);

	private final Channel.Id id;
	private final List<ChatUser> connectedUsers;

	public RoomChannel(Channel.Id channelId) {
		this.id = channelId;
		connectedUsers = Collections.synchronizedList(new ArrayList<>());
	}

	@Override
	public Id getId() {
		return id;
	}

	@Override
	public void join(ChatUser chatUser) {
		connectedUsers.add(chatUser);
		sendPublicMessage(serverMessage("User " + chatUser.getUsername() + " joined to room " + id.channelName, id));
	}

	@Override
	public void disconnect(ChatUser chatUser) {
		log.debug("Disconnecting user: " + chatUser.getUsername());
		connectedUsers.remove(chatUser);
		sendPublicMessage(serverMessage("User " + chatUser.getUsername() + " left room " + id.channelName, id));
	}

	@Override
	public void sendPublicMessage(Message message) {
		connectedUsers.forEach(user -> user.sendMessage(message));
	}

}
