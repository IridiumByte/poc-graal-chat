package com.iridiumbyte.poc.chat.client.socket;

import com.iridiumbyte.poc.chat.api.client.ClientMessage;

public interface MessageHandler {

	void onMessage(ClientMessage msg);

}
