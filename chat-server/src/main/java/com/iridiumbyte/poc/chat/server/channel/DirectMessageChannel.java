package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.server.message.Message;
import com.iridiumbyte.poc.chat.server.user.ChatUser;

public class DirectMessageChannel implements Channel {

	public DirectMessageChannel() {
	}

	@Override
	public Id getId() {
		return null;
	}

	@Override
	public void join(ChatUser chatUser) {

	}

	@Override
	public void sendPublicMessage(Message message) {

	}

	@Override
	public void disconnect(ChatUser chatUser) {

	}

}
