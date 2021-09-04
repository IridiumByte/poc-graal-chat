package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.api.server.ChannelId;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryActiveChannelDao implements ActiveChannelDao{

	private Map<ChannelId, Channel> channels = new ConcurrentHashMap<>();

	@Override
	public Collection<Channel> findAll() {
		return channels.values();
	}

	@Override
	public Optional<Channel> findById(ChannelId channelId) {
		return Optional.ofNullable(channels.get(channelId));
	}

	@Override
	public Channel save(Channel channel) {
		channels.put(channel.getId(), channel);
		return channel;
	}

}
