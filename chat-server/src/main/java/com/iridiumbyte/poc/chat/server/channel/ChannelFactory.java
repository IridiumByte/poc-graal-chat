package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.api.ChannelType;

public class ChannelFactory {

	public static Channel createNew(Channel.Id channelId) {
		if (channelId.channelType == ChannelType.ROOM)
			return new RoomChannel(channelId);
		else
			throw new RuntimeException("not ready yet");
	}

}
