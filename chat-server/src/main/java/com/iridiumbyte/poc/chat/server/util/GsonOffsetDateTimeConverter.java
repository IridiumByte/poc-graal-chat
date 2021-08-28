package com.iridiumbyte.poc.chat.server.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

public class GsonOffsetDateTimeConverter implements JsonSerializer<OffsetDateTime> {

	@Override
	public JsonElement serialize(OffsetDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
		return new JsonPrimitive(offsetDateTime.format(ISO_OFFSET_DATE_TIME));
	}

}
