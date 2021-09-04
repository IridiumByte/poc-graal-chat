package com.iridiumbyte.poc.chat.server.channel;

import java.util.Collection;
import java.util.Optional;

public interface ActiveChannelDao {

	Collection<Channel> findAll();

	Optional<Channel> findById(Channel.Id id);

	Channel save(Channel channel);


}
