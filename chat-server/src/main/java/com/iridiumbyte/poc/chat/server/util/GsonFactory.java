package com.iridiumbyte.poc.chat.server.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.OffsetDateTime;

public class GsonFactory {

	public static Gson defaultGson() {
		return new GsonBuilder()
				.registerTypeAdapter(OffsetDateTime.class, new GsonOffsetDateTimeConverter())
				.create();
	}

}
