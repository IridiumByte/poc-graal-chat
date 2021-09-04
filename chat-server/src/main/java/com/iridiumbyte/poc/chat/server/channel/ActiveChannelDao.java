package com.iridiumbyte.poc.chat.server.channel;

import com.iridiumbyte.poc.chat.api.server.ChannelId;

import java.util.Collection;
import java.util.Optional;

public interface ActiveChannelDao {

	Collection<Channel> findAll();

	Optional<Channel> findById(ChannelId channelId);

	Channel save(Channel channel);


}
