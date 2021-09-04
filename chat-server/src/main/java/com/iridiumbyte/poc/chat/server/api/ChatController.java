package com.iridiumbyte.poc.chat.server.api;

import com.iridiumbyte.poc.chat.api.server.ChannelId;
import com.iridiumbyte.poc.chat.server.ChatServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("/chat/channels")
public class ChatController {

	private final ChatServer chatServer;

	public ChatController(ChatServer chatServer) {
		this.chatServer = chatServer;
	}

	@GET
	public Collection<ChannelId> getAvailableChannels() {
		return chatServer.listPublicChannels();
	}

}
