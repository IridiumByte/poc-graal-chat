package com.iridiumbyte.poc.chat.client;

public class Runner {

	public static void main(String[] args) {
		ChatClient client = new ChatClient("ws://localhost:8080/chat/room1/bane", "bane");
	}

}
