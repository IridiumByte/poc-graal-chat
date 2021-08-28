package com.iridiumbyte.poc.chat.server.message;

import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.server.channel.Channel;
import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

public class MessageFactory {

	public static Message from(MessageDto messageDto, Username author) {
		return new Message(
				author.toString(),
				extractChannelId(messageDto),
				messageDto.content
		);
	}

	public static Channel.Id extractChannelId(MessageDto messageDto) {
		return new Channel.Id(messageDto.target, messageDto.channelType);
	}

}
