package com.iridiumbyte.poc.chat.client.socket;

import com.iridiumbyte.poc.chat.api.server.ServerMessage;

public interface MessageHandler {

	void onMessage(ServerMessage msg);

}
