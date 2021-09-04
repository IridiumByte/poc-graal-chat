package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.api.server.ChannelId;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.server.user.ChatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.iridiumbyte.poc.chat.server.message.MessageFactory.serverMessage;

public class RoomChannel implements Channel {

	private static final Logger log = LoggerFactory.getLogger(RoomChannel.class);

	private final ChannelId channelId;
	private final List<ChatUser> connectedUsers;

	public RoomChannel(ChannelId channelId) {
		this.channelId = channelId;
		connectedUsers = Collections.synchronizedList(new ArrayList<>());
	}

	@Override
	public ChannelId getId() {
		return channelId;
	}

	@Override
	public void join(ChatUser chatUser) {
		connectedUsers.add(chatUser);
		sendPublicMessage(serverMessage("User " + chatUser.getUsername() + " joined to room " + channelId.channelName, channelId));
	}

	@Override
	public void disconnect(ChatUser chatUser) {
		log.debug("Disconnecting user: " + chatUser.getUsername());
		connectedUsers.remove(chatUser);
		sendPublicMessage(serverMessage("User " + chatUser.getUsername() + " left room " + channelId.channelName, channelId));
	}

	@Override
	public void sendPublicMessage(ServerMessage message) {
		connectedUsers.forEach(user -> user.sendMessage(message));
	}

}
