package com.iridiumbyte.poc.chat.server;

import com.iridiumbyte.poc.chat.server.channel.InMemoryActiveChannelDao;
import com.iridiumbyte.poc.chat.server.user.InMemoryActiveUserDao;

import javax.inject.Singleton;

public class ChatServerConfiguration {

	@Singleton
	public ChatServer chatServer() {
		return new ChatServer(new InMemoryActiveChannelDao(), new InMemoryActiveUserDao());
	}

}
