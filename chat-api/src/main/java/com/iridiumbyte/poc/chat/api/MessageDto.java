package com.iridiumbyte.poc.chat.api;

public class MessageDto {

	public ChannelType channelType;
	public MessageType messageType;
	public String target;
	public String content;

	public MessageDto() { //TODO: gson to be removed
	}

	public MessageDto(ChannelType channelType, MessageType messageType, String target, String content) {
		this.channelType = channelType;
		this.messageType = messageType;
		this.target = target;
		this.content = content;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public String getTarget() {
		return target;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "MessageDto{" +
				"channelType=" + channelType +
				", messageType=" + messageType +
				", target='" + target + '\'' +
				", content='" + content + '\'' +
				'}';
	}

}
