package com.mercantil.andina.pizzeria.util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateTimeJacksonDeserializer extends StdDeserializer<LocalDateTime>
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected LocalDateTimeJacksonDeserializer() {
	        super(LocalDateTime.class);
	    }

	 @Override
	 public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
	        return LocalDateTime.parse(parser.readValueAs(String.class));
	 }
}
