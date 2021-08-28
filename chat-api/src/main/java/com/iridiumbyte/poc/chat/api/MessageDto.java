package com.iridiumbyte.poc.chat.api;

public class MessageDto {

	public final ChannelType channelType;
	public final MessageType messageType;
	public final String target;
	public final String content;

	public MessageDto(ChannelType channelType, MessageType messageType, String target, String content) {
		this.channelType = channelType;
		this.messageType = messageType;
		this.target = target;
		this.content = content;
	}

}
