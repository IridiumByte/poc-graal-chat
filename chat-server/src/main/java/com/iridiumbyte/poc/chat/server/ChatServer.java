package com.iridiumbyte.poc.chat.server;

import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.server.channel.ActiveChannelDao;
import com.iridiumbyte.poc.chat.server.channel.Channel;
import com.iridiumbyte.poc.chat.server.channel.ChannelFactory;
import com.iridiumbyte.poc.chat.server.message.Message;
import com.iridiumbyte.poc.chat.server.message.MessageFactory;
import com.iridiumbyte.poc.chat.server.user.ActiveUserDao;
import com.iridiumbyte.poc.chat.server.user.ChatUser;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

public class ChatServer {

	private final ActiveChannelDao activeChannels;
	private final ActiveUserDao activeUsers;

	public ChatServer(ActiveChannelDao activeChannels, ActiveUserDao activeUsers) {
		this.activeChannels = activeChannels;
		this.activeUsers = activeUsers;
	}

	public void join(ChatUser chatUser) {
		activeUsers.save(chatUser);
	}

	public void join(Username username, Channel.Id channelId) {
		ChatUser user = activeUsers.getByName(username);
		activeChannels.findById(channelId)
				.orElseGet(() -> activeChannels.save(ChannelFactory.createNew(channelId)))
				.join(user);
	}

	public void sendMessage(Username username, MessageDto incoming) {
		Message message = MessageFactory.from(incoming, username);
		activeChannels.findById(message.channelId)
				.orElseThrow()
				.sendPublicMessage(message);
	}

	public void leave(ChatUser chatUser) {


	}

}
