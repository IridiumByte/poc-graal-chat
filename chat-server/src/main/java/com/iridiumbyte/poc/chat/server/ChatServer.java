package com.iridiumbyte.poc.chat.server;

import com.iridiumbyte.poc.chat.api.client.ClientMessage;
import com.iridiumbyte.poc.chat.api.server.ChannelId;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.server.channel.ActiveChannelDao;
import com.iridiumbyte.poc.chat.server.channel.Channel;
import com.iridiumbyte.poc.chat.server.channel.ChannelFactory;
import com.iridiumbyte.poc.chat.server.message.MessageFactory;
import com.iridiumbyte.poc.chat.server.user.ActiveUserDao;
import com.iridiumbyte.poc.chat.server.user.ChatUser;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

import java.util.Collection;

import static com.iridiumbyte.poc.chat.server.message.MessageFactory.serverMessage;
import static java.util.stream.Collectors.toList;

public class ChatServer {

	private final ActiveChannelDao activeChannels;
	private final ActiveUserDao activeUsers;

	public ChatServer(ActiveChannelDao activeChannels, ActiveUserDao activeUsers) {
		this.activeChannels = activeChannels;
		this.activeUsers = activeUsers;
	}

	public void join(ChatUser chatUser) {
		activeUsers.save(chatUser);
		chatUser.sendMessage(serverMessage("Connected as " + chatUser.getUsername()));
	}

	public void join(Username username, ChannelId channelId) {
		ChatUser user = activeUsers.getByName(username);
		activeChannels.findById(channelId)
				.orElseGet(() -> activeChannels.save(ChannelFactory.createNew(channelId)))
				.join(user);
	}

	public void sendMessage(Username username, ClientMessage incoming) {
		ServerMessage message = MessageFactory.from(incoming, username);
		activeChannels.findById(message.channelId)
				.orElseThrow()
				.sendPublicMessage(message);
	}

	public void leave(Username username) {
		ChatUser user = activeUsers.getByName(username);
		activeChannels.findAll().forEach(channel -> channel.disconnect(user));
		activeUsers.deleteByName(username);
	}

	public Collection<ChannelId> listPublicChannels() {
		return activeChannels.findAll().stream()
				.filter(Channel::isPublic)
				.map(Channel::getId)
				.collect(toList());
	}

}
