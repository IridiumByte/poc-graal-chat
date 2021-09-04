package com.iridiumbyte.poc.chat.server.api;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServerHandshakeConfigurator extends ServerEndpointConfig.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		String authHeader = request.getHeaders().get("X-Auth").get(0);
		sec.getUserProperties().put("X-Auth", authHeader);
		super.modifyHandshake(sec, request, response);
	}

}
