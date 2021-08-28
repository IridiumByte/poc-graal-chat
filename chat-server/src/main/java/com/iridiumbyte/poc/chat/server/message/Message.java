package com.iridiumbyte.poc.chat.server.message;

import com.iridiumbyte.poc.chat.server.channel.Channel;

import java.time.OffsetDateTime;

public class Message {

	public String author;
	public String content;
	public Channel.Id channelId;
	public OffsetDateTime creationTime;

	public Message() { //TODO: gson to be removed
	}

	public Message(String author, Channel.Id channelId, String content) {
		this.author = author;
		this.content = content;
		this.channelId = channelId;
		creationTime = OffsetDateTime.now();
	}

	public Channel.Id getChannelId() {
		return channelId;
	}

}
