package com.iridiumbyte.poc.chat.client.socket;

import com.iridiumbyte.poc.chat.api.MessageDto;

public interface MessageHandler {

	void onMessage(MessageDto msg);

}
