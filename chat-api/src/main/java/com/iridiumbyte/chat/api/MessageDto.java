package com.iridiumbyte.chat.api;

public class MessageDto {

	public final MessageType messageType;
	public final String target;
	public final String content;

	public MessageDto(MessageType messageType, String target, String content) {
		this.messageType = messageType;
		this.target = target;
		this.content = content;
	}

}
