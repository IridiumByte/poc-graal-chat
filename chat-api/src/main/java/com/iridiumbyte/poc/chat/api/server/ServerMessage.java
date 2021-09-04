package com.iridiumbyte.poc.chat.api.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerMessage {

	public final String author;
	public final String content;
	public final ChannelId channelId;
	public final OffsetDateTime creationTime;

	@JsonCreator
	public ServerMessage(
			@JsonProperty("author") String author,
			@JsonProperty("channelId") ChannelId channelId,
			@JsonProperty("content") String content) {
		this.author = author;
		this.content = content;
		this.channelId = channelId;
		creationTime = OffsetDateTime.now();
	}

}
