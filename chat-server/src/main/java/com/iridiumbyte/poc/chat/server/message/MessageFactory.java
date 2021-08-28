package com.iridiumbyte.poc.chat.server.message;

import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.server.channel.Channel;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

public class MessageFactory {

	public static Message from(MessageDto messageDto, Username author) {
		return new Message(
				author.toString(),
				extractChannelId(messageDto),
				messageDto.getContent()
		);
	}

	public static Channel.Id extractChannelId(MessageDto messageDto) {
		return new Channel.Id(messageDto.getTarget(), messageDto.getChannelType());
	}

	public static Message serverMessage(String message) {
		return new Message(
				"SERVER",
				null,
				message
		);
	}

	public static Message serverMessage(String message, Channel.Id channelId) {
		return new Message(
				"SERVER",
				channelId,
				message
		);
	}

}
