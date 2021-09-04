package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.api.client.ChannelType;
import com.iridiumbyte.poc.chat.api.server.ChannelId;

public class ChannelFactory {

	public static Channel createNew(ChannelId channelId) {
		if (channelId.channelType == ChannelType.ROOM)
			return new RoomChannel(channelId);
		else
			throw new RuntimeException("not ready yet");
	}

}
