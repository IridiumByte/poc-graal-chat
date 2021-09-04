package com.iridiumbyte.poc.chat.api.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iridiumbyte.poc.chat.api.client.ChannelType;

import java.util.Objects;

public class ChannelId {

	public final String channelName;
	public final ChannelType channelType;

	@JsonCreator
	public ChannelId(@JsonProperty("channelName") String channelName, @JsonProperty("channelType") ChannelType channelType) {
		this.channelName = channelName;
		this.channelType = channelType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChannelId channelId = (ChannelId) o;
		return channelName.equals(channelId.channelName) && channelType == channelId.channelType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(channelName, channelType);
	}

}