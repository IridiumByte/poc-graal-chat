package com.iridiumbyte.poc.chat.server.channel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iridiumbyte.poc.chat.api.ChannelType;
import com.iridiumbyte.poc.chat.server.message.Message;
import com.iridiumbyte.poc.chat.server.user.ChatUser;

import java.util.Objects;

public interface Channel {

	Id getId();
	void join(ChatUser chatUser);
	void sendPublicMessage(Message message);
	void disconnect(ChatUser chatUser);

	class Id {

		public final String channelName;
		public final ChannelType channelType;

		@JsonCreator
		public Id(@JsonProperty("channelName") String channelName, @JsonProperty("channelType") ChannelType channelType) {
			this.channelName = channelName;
			this.channelType = channelType;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Id id = (Id) o;
			return channelName.equals(id.channelName) && channelType == id.channelType;
		}

		@Override
		public int hashCode() {
			return Objects.hash(channelName, channelType);
		}

	}

}
