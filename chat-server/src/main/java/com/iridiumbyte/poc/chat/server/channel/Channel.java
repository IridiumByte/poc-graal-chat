package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.api.server.ChannelId;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.server.user.ChatUser;

public interface Channel {

	ChannelId getId();
	void join(ChatUser chatUser);
	void sendPublicMessage(ServerMessage message);
	void disconnect(ChatUser chatUser);



}
