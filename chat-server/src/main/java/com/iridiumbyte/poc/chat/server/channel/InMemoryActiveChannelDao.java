package com.iridiumbyte.poc.chat.server.channel;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryActiveChannelDao implements ActiveChannelDao{

	private Map<Channel.Id, Channel> channels = new ConcurrentHashMap<>();

	@Override
	public Channel save(Channel channel) {
		channels.put(channel.getId(), channel);
		return channel;
	}

	@Override
	public Optional<Channel> findById(Channel.Id id) {
		return Optional.ofNullable(channels.get(id));
	}

}
