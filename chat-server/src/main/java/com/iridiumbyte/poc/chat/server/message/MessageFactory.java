package com.iridiumbyte.poc.chat.server.message;

import com.iridiumbyte.poc.chat.api.client.ClientMessage;
import com.iridiumbyte.poc.chat.api.server.ChannelId;
import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

public class MessageFactory {

	public static ServerMessage from(ClientMessage clientMessage, Username author) {
		return new ServerMessage(
				author.toString(),
				extractChannelId(clientMessage),
				clientMessage.getContent()
		);
	}

	public static ChannelId extractChannelId(ClientMessage clientMessage) {
		return new ChannelId(clientMessage.getTarget(), clientMessage.getChannelType());
	}

	public static ServerMessage serverMessage(String message) {
		return new ServerMessage(
				"SERVER",
				null,
				message
		);
	}

	public static ServerMessage serverMessage(String message, ChannelId channelId) {
		return new ServerMessage(
				"SERVER",
				channelId,
				message
		);
	}

}
