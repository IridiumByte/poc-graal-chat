package com.iridiumbyte.poc.chat.server;

import java.time.OffsetDateTime;

public class Message {

	private String username;
	private String content;
	private OffsetDateTime creationTime;

	public Message(String username, String content) {
		this.username = username;
		this.content = content;
		creationTime = OffsetDateTime.now();
	}

}
