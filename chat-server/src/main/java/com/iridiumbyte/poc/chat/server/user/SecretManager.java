package com.iridiumbyte.poc.chat.server.user;

import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

import java.util.Map;
import java.util.Optional;

public class SecretManager {

	private final Map<String, Username> secretToUser;

	public SecretManager(Map<String, Username> secretToUser) {
		this.secretToUser = secretToUser;
	}

	public Username authenticate(String secret) {
		return Optional.ofNullable(secretToUser.get(secret))
				.orElseThrow(() -> new RuntimeException("Unauthenticated user"));
	}

}
