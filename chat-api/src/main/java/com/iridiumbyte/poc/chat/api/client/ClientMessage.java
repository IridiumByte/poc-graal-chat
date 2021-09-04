package com.iridiumbyte.poc.chat.api.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientMessage {

	public final ChannelType channelType;
	public final MessageType messageType;
	public final String target;
	public final String content;

	@JsonCreator
	public ClientMessage(
			@JsonProperty("channelType") ChannelType channelType,
			@JsonProperty("messageType") MessageType messageType,
			@JsonProperty("target") String target,
			@JsonProperty("content") String content) {
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
		return "ClientMessage{" +
				"channelType=" + channelType +
				", messageType=" + messageType +
				", target='" + target + '\'' +
				", content='" + content + '\'' +
				'}';
	}

}
