package com.iridiumbyte.poc.chat.server.message;

import com.iridiumbyte.poc.chat.server.channel.Channel;

import java.time.OffsetDateTime;

public class Message {

	public final String author;
	public final String content;
	public final Channel.Id channelId;
	public final OffsetDateTime creationTime;

	public Message(String author, Channel.Id channelId, String content) {
		this.author = author;
		this.content = content;
		this.channelId = channelId;
		creationTime = OffsetDateTime.now();
	}

}
