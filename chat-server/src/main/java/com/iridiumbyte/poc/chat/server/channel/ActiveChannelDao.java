package com.iridiumbyte.poc.chat.server.channel;

import java.util.Optional;

public interface ActiveChannelDao {

	Channel save(Channel channel);

	Optional<Channel> findById(Channel.Id id);


}
