package com.iridiumbyte.poc.chat.client.model;

import com.iridiumbyte.poc.chat.api.client.ChannelType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatRoom {

	private final ChannelType type;
	private final String name;
	private final List<String> messages = new ArrayList<>();
	private boolean joined = false;

	public ChatRoom(ChannelType type, String name) {
		this.type = type;
		this.name = name;
	}

	public ChannelType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public List<String> getMessages() {
		return messages;
	}

	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChatRoom chatRoom = (ChatRoom) o;
		return type == chatRoom.type && Objects.equals(name, chatRoom.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, name);
	}

	@Override
	public String toString() {
		return "ChatRoom{" +
				"type=" + type +
				", name='" + name + '\'' +
				", messages=" + messages +
				'}';
	}

}
