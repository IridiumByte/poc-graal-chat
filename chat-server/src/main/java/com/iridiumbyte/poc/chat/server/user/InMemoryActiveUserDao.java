package com.iridiumbyte.poc.chat.server.user;

import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryActiveUserDao implements ActiveUserDao {

	private final Map<Username, ChatUser> users = new ConcurrentHashMap<>();

	@Override
	public ChatUser save(ChatUser chatUser) {
		users.put(chatUser.getUsername(), chatUser);
		return chatUser;
	}

	@Override
	public ChatUser getByName(Username username) {
		return Optional.ofNullable(users.get(username))
				.orElseThrow();
	}

	@Override
	public void delete(ChatUser chatUser) {
		users.remove(chatUser.getUsername());
	}

}
