package com.iridiumbyte.poc.chat.api.client;

public class ClientMessage {

	public final ChannelType channelType;
	public final MessageType messageType;
	public final String target;
	public final String content;

	public ClientMessage(ChannelType channelType, MessageType messageType, String target, String content) {
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
