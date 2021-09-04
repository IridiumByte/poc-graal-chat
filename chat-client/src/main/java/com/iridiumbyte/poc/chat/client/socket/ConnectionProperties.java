package com.iridiumbyte.poc.chat.client.socket;

public class ConnectionProperties {

	public final String url;
	public final String secret;

	public ConnectionProperties(String url, String secret) {
		this.url = url;
		this.secret = secret;
	}

}
